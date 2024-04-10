package dev.arbor.gtnn

import com.gregtechceu.gtceu.api.addon.GTAddon
import com.gregtechceu.gtceu.api.addon.IGTAddon
import com.gregtechceu.gtceu.api.addon.events.KJSRecipeKeyEvent
import com.gregtechceu.gtceu.api.addon.events.MaterialCasingCollectionEvent
import com.gregtechceu.gtceu.api.registry.GTRegistries
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate
import dev.arbor.gtnn.api.recipe.NeutronActivatorCondition
import dev.arbor.gtnn.api.recipe.PlantCasingCondition
import dev.arbor.gtnn.data.*
import dev.arbor.gtnn.data.recipes.AdAstraRecipes
import dev.arbor.gtnn.data.recipes.DefaultRecipes
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.resources.ResourceLocation
import java.util.function.Consumer

@GTAddon
class GTNNAddon : IGTAddon {
    override fun getRegistrate(): GTRegistrate {
        return GTNNRegistries.REGISTRATE
    }

    override fun initializeAddon() {
        GTNN.LOGGER.info("GTNN Loaded!")
    }

    override fun addonModId(): String {
        return GTNN.MODID
    }

    override fun registerTagPrefixes() {
        GTNNTagPrefix.init()
    }

    override fun registerElements() {
        GTNNElement.init()
    }

    override fun addRecipes(provider: Consumer<FinishedRecipe>?) {
        GTNNRecipes.init(provider)
    }

    override fun removeRecipes(consumer: Consumer<ResourceLocation>?) {
        DefaultRecipes.Misc.removeRecipes(consumer)
        if (GTNNIntegration.isAdAstraLoaded()) AdAstraRecipes.remove(consumer)
    }

    override fun registerOreVeins() {
        GTNNOres.init()
    }

    override fun registerRecipeConditions() {
        GTRegistries.RECIPE_CONDITIONS.register(PlantCasingCondition.INSTANCE.type, PlantCasingCondition::class.java)
        GTRegistries.RECIPE_CONDITIONS.register(NeutronActivatorCondition.INSTANCE.type, NeutronActivatorCondition::class.java)
    }

    override fun registerWorldgenLayers() {
        if (GTNNIntegration.isAdAstraLoaded()) {
            GTNNWorld.GTNNWorldGenLayers.init()
        }
    }

    override fun collectMaterialCasings(event: MaterialCasingCollectionEvent?) {
        GTNNCasingBlocks.init()
    }

    override fun registerRecipeKeys(event: KJSRecipeKeyEvent?) {
        super.registerRecipeKeys(event)
    }

}
