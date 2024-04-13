package dev.arbor.gtnn.api.machine.multiblock.part

import com.gregtechceu.gtceu.api.GTValues
import com.gregtechceu.gtceu.api.capability.recipe.IO
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer
import com.gregtechceu.gtceu.common.machine.multiblock.part.EnergyHatchPartMachine
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder
import net.minecraft.MethodsReturnNonnullByDefault
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.BlockHitResult
import java.util.concurrent.ThreadLocalRandom
import javax.annotation.ParametersAreNonnullByDefault
import kotlin.math.abs

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class NeutronAcceleratorMachine(
    holder: IMachineBlockEntity,
    tier: Int,
    vararg args: Any
) : EnergyHatchPartMachine(holder, tier, IO.IN, 1, args) {
    //////////////////////////////////////
    //*****     Initialization    ******//
    //////////////////////////////////////
    override fun createEnergyContainer(vararg args: Any): NotifiableEnergyContainer {
        val container = NotifiableEnergyContainer.receiverContainer(
            this,
            GTValues.V[tier] * 72L * amperage,
            GTValues.V[tier],
            amperage.toLong()
        )
        container.setSideInputCondition { it == this.getFrontFacing() && this.isWorkingEnabled }
        container.setCapabilityValidator { it == null || it == this.getFrontFacing() }
        return container
    }

    fun consumeEnergy(): Long {
        return if (this.isWorkingEnabled && this.energyContainer.energyStored > 0) {
            abs(this.energyContainer.changeEnergy(-getMaxEUConsume())) * (10 + ThreadLocalRandom.current().nextInt(11))
        } else {
            0L
        }
    }

    //////////////////////////////////////
    //**********     GUI     ***********//
    //////////////////////////////////////

    override fun shouldOpenUI(player: Player, hand: InteractionHand, hit: BlockHitResult): Boolean {
        return true
    }

    //////////////////////////////////////
    //**********     Data     **********//
    //////////////////////////////////////

    private fun getMaxEUConsume(): Long {
        return Math.round(GTValues.V[tier] * 0.8)
    }

    override fun getFieldHolder(): ManagedFieldHolder {
        return MANAGED_FIELD_HOLDER
    }

    companion object {
        private val MANAGED_FIELD_HOLDER = ManagedFieldHolder(NeutronAcceleratorMachine::class.java,
            EnergyHatchPartMachine.MANAGED_FIELD_HOLDER
        )
    }
}
