package dev.arbor.gtnn;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GTNN.MODID)
public class GTNNForge {
    public GTNNForge(){
        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        var register = Mod.EventBusSubscriber.Bus.MOD.bus().get();
        var forgeEventBus = MinecraftForge.EVENT_BUS;
        GTNN.INSTANCE.init();
        GTNN.genericListener(eventBus);
        GTNN.register(register);
        GTNN.register(forgeEventBus);
    }
}
