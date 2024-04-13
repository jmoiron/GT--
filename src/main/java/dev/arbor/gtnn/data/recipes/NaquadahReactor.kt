package dev.arbor.gtnn.data.recipes

import com.gregtechceu.gtceu.api.GTValues
import com.gregtechceu.gtceu.api.data.tag.TagPrefix
import com.gregtechceu.gtceu.common.data.GTMaterials
import dev.arbor.gtnn.data.GTNNRecipeTypes
import net.minecraft.data.recipes.FinishedRecipe
import java.util.function.Consumer

object NaquadahReactor {
    fun init(consumer: Consumer<FinishedRecipe>) {
        GTNNRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_I")
            .inputItems(TagPrefix.bolt, GTMaterials.NaquadahEnriched)
            .outputItems(TagPrefix.bolt, GTMaterials.Naquadah)
            .duration((50000000L / GTValues.V[GTValues.EV]).toInt())
            .EUt(-GTValues.V[GTValues.EV])
            .save(consumer)
        GTNNRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_II")
            .inputItems(TagPrefix.rod, GTMaterials.NaquadahEnriched)
            .outputItems(TagPrefix.rod, GTMaterials.Naquadah)
            .duration((250000000L / GTValues.V[GTValues.IV]).toInt())
            .EUt(-GTValues.V[GTValues.IV])
            .save(consumer)
        GTNNRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_III")
            .inputItems(TagPrefix.rodLong, GTMaterials.NaquadahEnriched)
            .outputItems(TagPrefix.rodLong, GTMaterials.Naquadah)
            .duration((500000000L / GTValues.V[GTValues.LuV]).toInt())
            .EUt(-GTValues.V[GTValues.LuV])
            .save(consumer)
        GTNNRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_IV")
            .inputItems(TagPrefix.bolt, GTMaterials.Naquadria)
            .outputItems(TagPrefix.bolt, GTMaterials.Naquadah)
            .duration((250000000L / GTValues.V[GTValues.ZPM]).toInt())
            .EUt(-GTValues.V[GTValues.ZPM])
            .save(consumer)
        GTNNRecipeTypes.NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_V")
            .inputItems(TagPrefix.rod, GTMaterials.Naquadria)
            .outputItems(TagPrefix.rod, GTMaterials.Naquadah)
            .duration((1000000000L / GTValues.V[GTValues.UV]).toInt())
            .EUt(-GTValues.V[GTValues.UV])
            .save(consumer)
    }
}
