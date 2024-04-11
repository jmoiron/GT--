package dev.arbor.gtnn;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GTNN.MODID)
public class GTNNForge {
    public GTNNForge(){
        GTNN.INSTANCE.init();
        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        var register = Mod.EventBusSubscriber.Bus.MOD.bus().get();
        GTNN.genericListener(eventBus);
        GTNN.register(register);
    }
}
