package dev.arbor.gtnn.mixin.gt;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.BedrockOreMinerMachine;
import dev.arbor.gtnn.api.recipe.BedrockOreMinerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedrockOreMinerMachine.class)
public class BedrockOreMinerMachineMixin {
    @Inject(method = "createRecipeLogic", at = @At("HEAD"), cancellable = true, remap = false)
    private void createRecipeLogic(Object[] args, CallbackInfoReturnable<RecipeLogic> cir) {
        cir.setReturnValue(new BedrockOreMinerLogic<>((BedrockOreMinerMachine) (Object) this));
    }
}
