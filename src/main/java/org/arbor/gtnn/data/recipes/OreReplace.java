package org.arbor.gtnn.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.arbor.gtnn.api.recipe.RecipeReplacer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static org.arbor.gtnn.data.GTNNMaterials.*;

public final class OreReplace implements RecipeReplacer {
    private static final String[] ores;
    private static final OreReplace INSTANCE = new OreReplace();

    static{
        ores = new String[]{
                "dirty_dust_to_dust", "refined_ore_to_dust", "pure_dust_to_dust","crushed_ore_to_refined_ore",
                "crushed_ore_to_impure_dust", "crushed_ore_to_purified_ore", "crushed_ore_to_dust", "ore_to_raw_ore",
                "purified_ore_to_refined_ore"
        };
    }

    public static void init(Recipe<?> recipe) {
        replace(recipe, Platinum, PlatinumMetal);
        replace(recipe, Palladium, PalladiumMetal);
        replace(recipe, Naquadah, NaquadahOxideMixture);
        replace(recipe, NaquadahEnriched, EnrichedNaquadahOxideMixture);
        replace(recipe, Naquadria, NaquadriaOxideMixture);
        replace(recipe, Neutronium, NeutroniumMixture);
    }

    private static void replace(Recipe<?> recipe, Material material, Material replacedMaterial){
        for (String string : ores){
            if (recipe instanceof GTRecipe gtRecipe && gtRecipe.id.getPath().contains(string) && gtRecipe.id.getPath().contains(material.getName())) {
                INSTANCE.setGTRecipeOutputs(gtRecipe.outputs, dust(material), SizedIngredient.create(dust(replacedMaterial)));
            }
        }
    }

    private static ItemStack dust(Material material) {
        return ChemicalHelper.get(TagPrefix.dust, material, 1);
    }
}
