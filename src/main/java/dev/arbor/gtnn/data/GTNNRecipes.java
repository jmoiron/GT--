package dev.arbor.gtnn.data;

import dev.arbor.gtnn.GTNN;
import dev.arbor.gtnn.GTNNIntegration;
import dev.arbor.gtnn.api.recipe.NeutronActivatorCondition;
import dev.arbor.gtnn.api.recipe.PlantCasingCondition;
import dev.arbor.gtnn.block.PlantCasingBlock;
import dev.arbor.gtnn.config.ConfigHandler;
import dev.arbor.gtnn.data.recipes.*;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public class GTNNRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        DefaultRecipes.init(provider);
        NaquadahReactor.init(provider);
        RocketFuel.init(provider);
        BrineChain.init(provider);
        if (GTNN.INSTANCE.getServerConfig().enableHarderPlatinumLine) PlatinumLine.init(provider);
        if (GTNN.INSTANCE.getServerConfig().enableHarderNaquadahLine) NaquadahLine.init(provider);
        if (GTNNIntegration.INSTANCE.isAdAstraLoaded()) AdAstraRecipes.init(provider);
    }

    public static int dur(double seconds) {
        return (int) (seconds * 20d);
    }


    public static NeutronActivatorCondition setNA(int max, int min){
        return new NeutronActivatorCondition(max, min);
    }
    public static PlantCasingCondition setPlantCasing(int tier) {
        return new PlantCasingCondition(PlantCasingBlock.getByTier(tier - 1));
    }

    public static PlantCasingCondition setPlantCasing(PlantCasingBlock plantCasing) {
        return new PlantCasingCondition(plantCasing);
    }

    public static PlantCasingCondition setPlantCasing(String name) {
        PlantCasingBlock plantCasing = PlantCasingBlock.getByName(name);
        return new PlantCasingCondition(plantCasing);
    }
}
