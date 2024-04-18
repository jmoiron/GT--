package dev.arbor.gtnn.api.recipe

import com.gregtechceu.gtceu.api.GTValues
import com.gregtechceu.gtceu.api.capability.recipe.IO
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper
import com.gregtechceu.gtceu.api.data.chemical.material.Material
import com.gregtechceu.gtceu.api.data.tag.TagPrefix
import com.gregtechceu.gtceu.api.data.worldgen.bedrockore.BedrockOreVeinSavedData
import com.gregtechceu.gtceu.api.data.worldgen.bedrockore.OreVeinWorldEntry
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic
import com.gregtechceu.gtceu.api.recipe.GTRecipe
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier
import com.gregtechceu.gtceu.common.machine.multiblock.electric.BedrockOreMinerMachine
import com.gregtechceu.gtceu.config.ConfigHolder
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder
import com.gregtechceu.gtceu.utils.GTUtil
import dev.arbor.gtnn.api.machine.multiblock.StoneBedrockOreMinerMachine
import dev.arbor.gtnn.api.tool.StringTools.nn
import dev.arbor.gtnn.data.GTNNRecipeTypes
import net.minecraft.core.SectionPos
import net.minecraft.server.level.ServerLevel
import kotlin.math.max

open class StoneBedrockOreMinerLogic(machine: StoneBedrockOreMinerMachine?) : RecipeLogic(machine) {
    private val maxProgress: Int = 20

    private var veinMaterials: List<Map.Entry<Int, Material>>? = null

    override fun getMachine(): StoneBedrockOreMinerMachine {
        return super.getMachine() as StoneBedrockOreMinerMachine
    }

    override fun findAndHandleRecipe() {
        val level = getMachine().level
        if (level is ServerLevel) {
            lastRecipe = null
            val data = BedrockOreVeinSavedData.getOrCreate(level)
            if (veinMaterials == null) {
                this.veinMaterials = data.getOreInChunk(getChunkX(), getChunkZ())
                if (this.veinMaterials == null) {
                    if (subscription != null) {
                        subscription.unsubscribe()
                        subscription = null
                    }
                    return
                }
            }
            val match = getOreMinerRecipe()
            if (match != null) {
                val copied = match.copy(ContentModifier(match.duration.toDouble(), 0.0))
                if (match.matchRecipe(this.machine).isSuccess && copied.matchTickRecipe(this.machine).isSuccess) {
                    setupRecipe(match)
                }
            }
        }
    }

    private fun getOreMinerRecipe(): GTRecipe? {
        val level = getMachine().level
        if (level is ServerLevel && veinMaterials != null) {
            val material = veinMaterials!![GTUtil.getRandomItem(veinMaterials, veinMaterials!!.size)].value
            var stack = ChemicalHelper.get(
                TagPrefix.get(ConfigHolder.INSTANCE.machines.bedrockOreDropTagPrefix),
                material,
                1
            )
            if (stack.isEmpty) stack = ChemicalHelper.get(
                TagPrefix.crushed,
                material,
                1
            ) // backup 1: crushed; if raw ore doesn't exist

            if (stack.isEmpty) stack = ChemicalHelper.get(
                TagPrefix.gem,
                material,
                1
            ) // backup 2: gem; if crushed ore doesn't exist

            if (stack.isEmpty) stack = ChemicalHelper.get(
                TagPrefix.ore,
                material,
                1
            ) // backup 3: just fallback to normal ore...

            val recipe =
                GTRecipeBuilder.of("stone_bedrock_ores".nn(), GTNNRecipeTypes.STONE_BEDROCK_ORE_MACHINE_RECIPES)
                    .duration(maxProgress)
                    //.EUt(GTValues.VA[getMachine().energyTier].toLong())
                    .outputItems(stack)
                    .buildRawRecipe()

            if (recipe.matchRecipe(getMachine()).isSuccess && recipe.matchTickRecipe(getMachine()).isSuccess) {
                return recipe
            }
        }
        return null
    }

    private fun getOreToProduce(entry: OreVeinWorldEntry): Int {
        val definition = entry.definition
        if (definition != null) {
            val depletedYield = definition.depletedYield()
            val regularYield = entry.oreYield
            val remainingOperations = entry.operationsRemaining

            var produced = max(
                depletedYield.toDouble(),
                (regularYield * remainingOperations / BedrockOreVeinSavedData.MAXIMUM_VEIN_OPERATIONS).toDouble()
            )
                .toInt()
            produced *= BedrockOreMinerMachine.getRigMultiplier(getMachine().tier)

            return produced
        }
        return 0
    }

    override fun onRecipeFinish() {
        machine.afterWorking()
        if (lastRecipe != null) {
            lastRecipe!!.postWorking(this.machine)
            lastRecipe!!.handleRecipeIO(IO.OUT, this.machine)
        }
        depleteVein()
        // try it again
        val match = getOreMinerRecipe()
        if (match != null) {
            val copied = match.copy(ContentModifier(match.duration.toDouble(), 0.0))
            if (match.matchRecipe(this.machine).isSuccess && copied.matchTickRecipe(this.machine).isSuccess) {
                setupRecipe(match)
                return
            }
        }
        status = Status.IDLE
        progress = 0
        duration = 0
    }

    private fun depleteVein() {
        val level = getMachine().level
        if (level is ServerLevel) {
            val chance = BedrockOreMinerMachine.getDepletionChance(getMachine().tier)
            val data = BedrockOreVeinSavedData.getOrCreate(level)
            // chance to deplete based on the rig
            if (chance == 1 || GTValues.RNG.nextInt(chance) == 0) {
                data.depleteVein(getChunkX(), getChunkZ(), 0, false)
            }
        }
    }

    private fun getChunkX(): Int {
        return SectionPos.blockToSectionCoord(getMachine().pos.x)
    }

    private fun getChunkZ(): Int {
        return SectionPos.blockToSectionCoord(getMachine().pos.z)
    }
}