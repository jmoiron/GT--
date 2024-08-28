package dev.arbor.gtnn.api.machine.feature

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.state.BlockState

interface IGTPPMachine {
    fun getTier(): Int
    fun locationGetter(): ResourceLocation
    fun getAppearance(): BlockState
}
