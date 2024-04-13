package dev.arbor.gtnn.api.recipe

import com.google.gson.JsonObject
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic
import com.gregtechceu.gtceu.api.recipe.GTRecipe
import com.gregtechceu.gtceu.api.recipe.RecipeCondition
import dev.arbor.gtnn.block.PlantCasingBlock
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.util.GsonHelper


class PlantCasingCondition(var plantCasing: PlantCasingBlock?) : RecipeCondition() {
    companion object{
        val INSTANCE: PlantCasingCondition = PlantCasingCondition()
    }

    constructor(): this(null)

    override fun getType(): String {
        return "chemical_plant_casing"
    }

    override fun getTooltips(): Component {
        return Component.translatable(
            "gtnn.recipe.condition.plant_casing.tooltip",
            plantCasing!!.getTier() + 1,
            plantCasing!!.getName()
        )
    }

    override fun test(recipe: GTRecipe, recipeLogic: RecipeLogic): Boolean {
        val machine = recipeLogic.getMachine()
        return machine is IMultiController && this.plantCasing != null
    }

    override fun createTemplate(): RecipeCondition {
        return PlantCasingCondition()
    }

    override fun serialize(): JsonObject {
        val value = super.serialize()
        value.addProperty("plantCasing", plantCasing!!.getName())
        return value
    }

    override fun deserialize(config: JsonObject): RecipeCondition {
        super.deserialize(config)
        this.plantCasing = PlantCasingBlock.getByName(
            GsonHelper.getAsString(config, "plantCasing", "plantCasing")
        )
        return this
    }

    override fun toNetwork(buf: FriendlyByteBuf) {
        super.toNetwork(buf)
        buf.writeUtf(plantCasing!!.getName())
    }

    override fun fromNetwork(buf: FriendlyByteBuf): RecipeCondition {
        super.fromNetwork(buf)
        this.plantCasing = PlantCasingBlock.getByNameOrDefault(buf.readUtf())
        return this
    }
}
