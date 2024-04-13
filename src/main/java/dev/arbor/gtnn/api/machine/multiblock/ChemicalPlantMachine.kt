package dev.arbor.gtnn.api.machine.multiblock

import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity
import com.gregtechceu.gtceu.api.machine.MetaMachine
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine
import com.gregtechceu.gtceu.api.recipe.GTRecipe
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic
import com.gregtechceu.gtceu.api.recipe.RecipeHelper
import com.gregtechceu.gtceu.api.recipe.content.Content
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted
import com.lowdragmc.lowdraglib.syncdata.annotation.RequireRerender
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder
import dev.arbor.gtnn.GTNNForge
import dev.arbor.gtnn.api.block.MachineCasingType
import dev.arbor.gtnn.api.block.PipeType
import dev.arbor.gtnn.api.block.PlantCasingType
import dev.arbor.gtnn.api.machine.feature.IGTPPMachine
import dev.arbor.gtnn.api.recipe.PlantCasingCondition
import net.minecraft.MethodsReturnNonnullByDefault
import javax.annotation.ParametersAreNonnullByDefault

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class ChemicalPlantMachine(holder: IMachineBlockEntity) : CoilWorkableElectricMultiblockMachine(holder), IGTPPMachine {
    @Persisted
    @DescSynced
    @RequireRerender
    private var machineTier = 0

    var machineCasingType: MachineCasingType? = null

    var pipeType: PipeType? = null

    var plantCasingType: PlantCasingType? = null

    //////////////////////////////////////
    //***    Multiblock LifeCycle    ***//
    //////////////////////////////////////

    override fun onStructureFormed() {
        scheduleRenderUpdate()
        super.onStructureFormed()
        // class dev.arbor.gtnn.block.MachineCasingBlock$MachineCasing cannot be cast to class java.lang.Void
        GTNNForge.checkChemicalPlantMachine(this)
        // Get the plant casing tier
        this.machineTier = getPlantCasingTier()
    }

    override fun getTier(): Int {
        return this.machineTier
    }

    override fun scheduleRenderUpdate() {
        scheduleRenderUpdate(this)
    }

    fun getMachineCasingTier(): Int {
        return this.machineCasingType?.getMachineCasingTier() ?: 0

    }

    fun getPipeTier(): Int {
        return this.pipeType?.getPipeTier() ?: 0
    }

    fun getPlantCasingTier(): Int {
        return this.plantCasingType?.getTier() ?: 0
    }

    //////////////////////////////////////
    //******       NBT SAVE      *******//
    //////////////////////////////////////

    override fun getFieldHolder(): ManagedFieldHolder {
        return MANAGED_FIELD_HOLDER
    }

    //////////////////////////////////////
    //******     RECIPE LOGIC    *******//
    //////////////////////////////////////

    companion object{
        private val MANAGED_FIELD_HOLDER =
            ManagedFieldHolder(ChemicalPlantMachine::class.java, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER)
        fun chemicalPlantRecipe(machine: MetaMachine, recipe: GTRecipe): GTRecipe? {
            if (machine is ChemicalPlantMachine) {
                if (RecipeHelper.getRecipeEUtTier(recipe) > machine.getMachineCasingTier() + 1) {
                    return null
                }
                val plantCasingCondition = recipe.conditions[0]
                if (plantCasingCondition is PlantCasingCondition) {
                    if ((plantCasingCondition.plantCasing?.getTier() ?: 0) > machine.getPlantCasingTier()) {
                        return null
                    }
                }

                val maxParallel = 1 + 2 * machine.getPipeTier()
                val parallelLimit = maxParallel.coerceAtMost(machine.getMaxVoltage().toInt())
                val result = GTRecipeModifiers.accurateParallel(machine, recipe, parallelLimit, false)
                val recipe2 = if (result.a == recipe) result.a.copy() else result.a
                val parallelValue = result.b
                recipe.duration = 1.coerceAtLeast(256 * parallelValue / maxParallel)
                recipe.tickInputs[EURecipeCapability.CAP] = listOf(Content(parallelValue.toLong(), 1.0f, 0.0f, null, null))

                return RecipeHelper.applyOverclock(OverclockingLogic filter@{ _, recipeEUt, maxVoltage, duration, amountOC ->
                    val runOverclockingLogic = OverclockingLogic.NON_PERFECT_OVERCLOCK.logic.runOverclockingLogic(
                        recipe2,
                        recipeEUt,
                        maxVoltage,
                        duration,
                        amountOC
                    )
                    if (machine.coilTier > 0) {
                        val eu = runOverclockingLogic.firstLong() * (1 - machine.coilTier * 0.5)
                        runOverclockingLogic.first(1.0.coerceAtLeast(eu).toLong())
                    }
                    return@filter runOverclockingLogic
                }, recipe, machine.getMaxVoltage())
            }
            throw RuntimeException("Machine is not a ChemicalPlant")
        }
    }
}
