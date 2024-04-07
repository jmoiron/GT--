package org.arbor.gtnn.api.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface RecipeReplacer {
    default void setGTRecipeOutputs(Map<RecipeCapability<?>, List<Content>> outputs, ItemStack item, SizedIngredient sizedIngredient) {
        List<Content> contents = outputs.get(ItemRecipeCapability.CAP);
        for (Content content : contents) {
            if (content.content instanceof Ingredient ingredient) {
                if (Arrays.stream(ingredient.getItems()).anyMatch(itemStack -> itemStack.getItem() == item.getItem())) {
                    content.content = sizedIngredient;
                }
            }
        }
    }
}
