package dev.arbor.gtnn.api.machine.multiblock

import com.gregtechceu.gtceu.api.capability.IControllable
import com.gregtechceu.gtceu.api.capability.recipe.IO
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity
import com.gregtechceu.gtceu.api.machine.TickableSubscription
import com.gregtechceu.gtceu.api.machine.WorkableTieredMachine
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic
import com.gregtechceu.gtceu.common.data.GTMachines
import com.lowdragmc.lowdraglib.side.item.ItemTransferHelper
import com.lowdragmc.lowdraglib.syncdata.ISubscription
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder
import dev.arbor.gtnn.api.recipe.StoneBedrockOreMinerLogic
import net.minecraft.core.BlockPos
import net.minecraft.server.TickTask
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block

class StoneBedrockOreMinerMachine(holder: IMachineBlockEntity) :
    WorkableTieredMachine(holder, 0, GTMachines.defaultTankSizeFunction),
    IControllable, IFancyUIMachine {
    private var autoOutputSubs: TickableSubscription? = null
    private var exportItemSubs: ISubscription? = null

    override fun createEnergyContainer(vararg args: Any?): NotifiableEnergyContainer {
        return NotifiableEnergyContainer.emitterContainer(this, 0, 0, 0)
    }

    //////////////////////////////////////
    //*****     Initialization     *****//
    //////////////////////////////////////
    override fun getFieldHolder(): ManagedFieldHolder {
        return MANAGED_FIELD_HOLDER
    }

    override fun createImportItemHandler(vararg args: Any?): NotifiableItemStackHandler {
        return NotifiableItemStackHandler(this, 0, IO.NONE)
    }

    override fun createExportItemHandler(vararg args: Any?): NotifiableItemStackHandler {
        return NotifiableItemStackHandler(this, 6, IO.OUT, IO.BOTH)
    }

    override fun onDrops(drops: MutableList<ItemStack>, entity: Player) {
        clearInventory(drops, exportItems.storage)
    }

    //////////////////////////////////////
    //*******     Auto Output    *******//
    //////////////////////////////////////
    override fun isRemote(): Boolean {
        return super<WorkableTieredMachine>.isRemote()
    }

    override fun createRecipeLogic(vararg args: Any?): RecipeLogic {
        return StoneBedrockOreMinerLogic(this)
    }

    override fun onNeighborChanged(block: Block, fromPos: BlockPos, isMoving: Boolean) {
        super.onNeighborChanged(block, fromPos, isMoving)
        updateAutoOutputSubscription()
    }

    override fun onLoad() {
        super.onLoad()
        if (!isRemote) {
            if (level is ServerLevel) {
                level.server?.tell(TickTask(0) { this.updateAutoOutputSubscription() })
            }
            exportItemSubs = exportItems.addChangedListener { this.updateAutoOutputSubscription() }
        }
    }

    override fun onUnload() {
        super.onUnload()
        if (exportItemSubs != null) {
            exportItemSubs!!.unsubscribe()
            exportItemSubs = null
        }
    }

    //////////////////////////////////////
    //**********     LOGIC    **********//
    //////////////////////////////////////

    private fun updateAutoOutputSubscription() {
        val outputFacingItems = frontFacing
        if (!exportItems.isEmpty && ItemTransferHelper.getItemTransfer(
                level,
                pos.relative(outputFacingItems),
                outputFacingItems.opposite
            ) != null
        ) {
            autoOutputSubs = subscribeServerTick(autoOutputSubs) { this.autoOutput() }
        } else if (autoOutputSubs != null) {
            autoOutputSubs!!.unsubscribe()
            autoOutputSubs = null
        }
    }

    private fun autoOutput() {
        if (offsetTimer % 5 == 0L) {
            exportItems.exportToNearby(frontFacing)
        }
        updateAutoOutputSubscription()
    }

    companion object {
        private val MANAGED_FIELD_HOLDER: ManagedFieldHolder =
            ManagedFieldHolder(StoneBedrockOreMinerMachine::class.java, WorkableTieredMachine.MANAGED_FIELD_HOLDER)
    }
}