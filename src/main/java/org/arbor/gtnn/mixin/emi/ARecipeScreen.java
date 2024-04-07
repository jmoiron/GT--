package org.arbor.gtnn.mixin.emi;

import dev.emi.emi.screen.RecipeScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Deprecated(forRemoval = true, since = "1.1.0")

@Mixin(value = RecipeScreen.class, remap = false)
public interface ARecipeScreen {
    @Accessor
    int getTabPage();
    @Accessor
    int getTab();
    @Accessor
    int getPage();
}
