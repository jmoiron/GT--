package dev.arbor.gtnn.api.machine.feature

import com.gregtechceu.gtceu.api.machine.MetaMachine
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController
import com.gregtechceu.gtceu.api.machine.trait.MachineTrait
import com.gregtechceu.gtceu.api.pattern.MultiblockState
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder
import net.minecraft.network.chat.Component

abstract class MultiBlockTrait(machine: MetaMachine?) : MachineTrait(machine) {
    open fun onStructureFormed(state: MultiblockState?) {
        /**/
    }

    open fun onStructureInvalid() {
        /**/
    }

    open fun addDisplayText(textList: List<Component?>) {
        /**/
    }

    protected val multiBlock: IMultiController?
        //////////////////////////////////////
        get() {
            if (machine is IMultiController) {
                return machine as IMultiController
            }
            return null
        }

    override fun getFieldHolder(): ManagedFieldHolder {
        return MANAGED_FIELD_HOLDER
    }

    companion object {
        @JvmStatic
        protected val MANAGED_FIELD_HOLDER: ManagedFieldHolder = ManagedFieldHolder(MultiBlockTrait::class.java)
    }
}