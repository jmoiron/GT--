package dev.arbor.gtnn.client

import dev.arbor.gtnn.GTNN
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod.EventBusSubscriber(modid = GTNN.MODID, value = [Dist.CLIENT], bus = Mod.EventBusSubscriber.Bus.MOD)
object ClientEvents {
    @SubscribeEvent
    fun fmlCommonSetupEvent(@Suppress("UNUSED_PARAMETER") event: FMLCommonSetupEvent) {
        MOD_BUS.register(ExtraHeartRenderHandler())
    }
}
