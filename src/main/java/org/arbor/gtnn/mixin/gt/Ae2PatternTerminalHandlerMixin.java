package org.arbor.gtnn.mixin.gt;

import com.gregtechceu.gtceu.integration.emi.recipe.Ae2PatternTerminalHandler;
import dev.emi.emi.api.recipe.EmiRecipe;
import org.arbor.gtnn.GTNN;
import org.arbor.gtnn.emi.recipe.NGTEmiRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Deprecated(forRemoval = true, since = "1.1.0")
@Mixin(Ae2PatternTerminalHandler.class)
public class Ae2PatternTerminalHandlerMixin {
    // I AM AN IDIOT
    @Inject(method = "supportsRecipe", at = @At("HEAD"), cancellable = true, remap = false)
    private void supportsRecipe(EmiRecipe recipe, CallbackInfoReturnable<Boolean> cir) {
        if (GTNN.getClientConfig().enableRemakeGTMEMI) {
            cir.setReturnValue(recipe instanceof NGTEmiRecipe);
        }
    }
}
