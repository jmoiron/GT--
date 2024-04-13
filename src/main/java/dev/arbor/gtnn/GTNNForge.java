package dev.arbor.gtnn;

import dev.arbor.gtnn.api.block.MachineCasingType;
import dev.arbor.gtnn.api.block.PipeType;
import dev.arbor.gtnn.api.block.PlantCasingType;
import dev.arbor.gtnn.api.machine.multiblock.ChemicalPlantMachine;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GTNN.MODID)
public class GTNNForge {
    public GTNNForge(){
        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        var register = Mod.EventBusSubscriber.Bus.MOD.bus().get();
        GTNN.INSTANCE.init();
        GTNN.genericListener(eventBus);
        GTNN.register(register);
    }

    // No idea why kotlin not support this
    public static void checkChemicalPlantMachine(ChemicalPlantMachine chemicalPlant) {
        // Retrieve the multiblock state
        var multiblockState = chemicalPlant.getMultiblockState();
        var matchContext = multiblockState.getMatchContext();
        // Get and store type objects to avoid repeated retrieval
        MachineCasingType machineCasingType = matchContext.get("MachineCasing") instanceof MachineCasingType ? (MachineCasingType) matchContext.get("MachineCasing") : null;
        PipeType pipeType = matchContext.get("Pipe") instanceof PipeType ? (PipeType) matchContext.get("Pipe") : null;
        PlantCasingType plantCasingType = matchContext.get("PlantCasing") instanceof PlantCasingType ? (PlantCasingType) matchContext.get("PlantCasing") : null;
        // Set type variables
        chemicalPlant.setMachineCasingType(machineCasingType);
        chemicalPlant.setPipeType(pipeType);
        chemicalPlant.setPlantCasingType(plantCasingType);
    }
}
