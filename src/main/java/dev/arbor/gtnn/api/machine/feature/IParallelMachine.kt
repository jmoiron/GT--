package dev.arbor.gtnn.api.machine.feature

import com.gregtechceu.gtceu.api.machine.MetaMachine
import com.gregtechceu.gtceu.api.machine.feature.IMachineFeature
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine
import com.gregtechceu.gtceu.api.pattern.MultiblockState
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder
import net.minecraft.util.Mth
import java.util.function.Function
import kotlin.math.max

interface IParallelMachine : IMachineFeature {
    val maxParallel: Int
    var parallelNumber: Int

    class ParallelStats(machine: MetaMachine?, private val parallelCalculator: Function<IParallelMachine, Int>) :
        MultiBlockTrait(machine), IParallelMachine {
        override val maxParallel: Int
            get() {
                if (multiBlock is IParallelMachine) {
                    return parallelCalculator.apply(multiBlock as IParallelMachine)
                }
                return 1
            }

        @Persisted
        override var parallelNumber: Int = 0
            get() {
                return max(1.0, field.toDouble()).toInt()
            }
            set(value) {
                val multiBlock = multiBlock
                if (multiBlock == null || !multiBlock.isFormed) return

                if (multiBlock is WorkableMultiblockMachine) {
                    field = Mth.clamp(value, 1, maxParallel)
                    multiBlock.getRecipeLogic().markLastRecipeDirty()
                }
            }

        override fun onStructureFormed(state: MultiblockState?) {
            if (parallelNumber == 0) parallelNumber = maxParallel
        }

        override fun onStructureInvalid() {
            parallelNumber = 0
        }

        override fun getFieldHolder(): ManagedFieldHolder {
            return MANAGED_FIELD_HOLDER
        }

        companion object {
            val MANAGED_FIELD_HOLDER: ManagedFieldHolder =
                ManagedFieldHolder(ParallelStats::class.java, MultiBlockTrait.MANAGED_FIELD_HOLDER)
        }
    }
}