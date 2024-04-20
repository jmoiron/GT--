package dev.arbor.gtnn.api.recipe

import com.gregtechceu.gtceu.api.GTValues
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper
import com.gregtechceu.gtceu.api.data.chemical.material.Material
import com.gregtechceu.gtceu.api.data.tag.TagPrefix
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition
import com.gregtechceu.gtceu.api.registry.GTRegistries.ORE_VEINS
import com.mojang.datafixers.util.Either
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState

object BedrockOreHelper {

    private val ores: List<Pair<Set<ResourceKey<Level>>, Pair<Int, Pair<List<Int>, List<ItemStack>>>>>
    val ORES_WEIGHTED: List<Pair<Set<ResourceKey<Level>>, Pair<Int, ItemStack>>>
    val ALLOW_ITEM: List<Item>

    init {
        ores = ORE_VEINS.map {
            it.dimensionFilter() to (it.weight() to (getChance(it) to getContainedOresAndBlocks(it)))
        }
        ORES_WEIGHTED = ores.flatMap {
            val (dimensions, pair) = it
            val (weight, pair2) = pair
            val (chances, ores) = pair2
            chances.zip(ores) { chance, ore ->
                Pair(dimensions, weight * chance to ore)
            }
        }
        ALLOW_ITEM = ORES_WEIGHTED.map { it.second.second.item }
    }

    private fun getChance(oreDefinition: GTOreDefinition): List<Int> {
        return oreDefinition.veinGenerator().allChances
    }

    private fun getContainedOresAndBlocks(oreDefinition: GTOreDefinition): List<ItemStack> {
        return oreDefinition.veinGenerator().allEntries.map { entry: Map.Entry<Either<BlockState, Material>, Int> ->
            entry.key.map({
                ItemStack.EMPTY
            }, { material: Material ->
                ChemicalHelper.get(TagPrefix.rawOre, material)
            })
        }.toList()
    }

    fun <T> getRandomItem(randomList: List<Pair<Int, T>>, size: Int): Int {
        if (randomList.isEmpty()) return -1
        val baseOffsets = IntArray(size)
        var currentIndex = 0
        for (i in 0 until size) {
            val entry = randomList[i]
            require(entry.first > 0) { "Invalid weight: " + entry.first }
            currentIndex += entry.first
            baseOffsets[i] = currentIndex
        }
        val randomValue: Int = GTValues.RNG.nextInt(currentIndex)
        for (i in 0 until size) {
            if (randomValue < baseOffsets[i]) return i
        }
        throw IllegalArgumentException("Invalid weight")
    }

    fun getRigMultiplier(tier: Int): Int {
        if (tier == GTValues.MV) return 8
        if (tier == GTValues.HV) return 32
        if (tier == GTValues.EV) return 64
        return 1
    }
}