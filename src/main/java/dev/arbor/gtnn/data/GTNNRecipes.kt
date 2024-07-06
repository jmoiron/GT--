package dev.arbor.gtnn.data

import dev.arbor.gtnn.GTNN.getServerConfig
import dev.arbor.gtnn.GTNNIntegration.isAdAstraLoaded
import dev.arbor.gtnn.api.recipe.NeutronActivatorCondition
import dev.arbor.gtnn.api.recipe.PlantCasingCondition
import dev.arbor.gtnn.block.PlantCasingBlock
import dev.arbor.gtnn.block.PlantCasingBlock.Companion.getByTier
import dev.arbor.gtnn.data.recipes.*
import net.minecraft.data.recipes.FinishedRecipe
import java.util.function.Consumer

object GTNNRecipes {
    fun init(provider: Consumer<FinishedRecipe>) {
        DefaultRecipes.init(provider)
        NaquadahReactor.init(provider)
        RocketFuel.init(provider)
        BrineChain.init(provider)
        if (getServerConfig().enableHarderPlatinumLine) PlatinumLine.init(provider)
        if (getServerConfig().enableHarderNaquadahLine) NaquadahLine.init(provider)
        if (isAdAstraLoaded()) AdAstraRecipes.init(provider)
    }

    fun dur(seconds: Double): Int {
        return (seconds * 20.0).toInt()
    }

    fun setNA(max: Int, min: Int): NeutronActivatorCondition {
        return NeutronActivatorCondition(max, min)
    }

    fun setPlantCasing(tier: Int): PlantCasingCondition {
        return PlantCasingCondition(getByTier(tier - 1))
    }

    fun setPlantCasing(plantCasing: PlantCasingBlock): PlantCasingCondition {
        return PlantCasingCondition(plantCasing)
    }
}
