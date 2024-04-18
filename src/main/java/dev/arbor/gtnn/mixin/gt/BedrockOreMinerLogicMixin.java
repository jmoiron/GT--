package dev.arbor.gtnn.mixin.gt;

import com.gregtechceu.gtceu.api.data.worldgen.bedrockore.OreVeinWorldEntry;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.BedrockOreMinerMachine;
import com.gregtechceu.gtceu.common.machine.trait.BedrockOreMinerLogic;
import dev.arbor.gtnn.GTNN;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedrockOreMinerLogic.class)
public abstract class BedrockOreMinerLogicMixin {
    @Shadow(remap = false)
    public abstract BedrockOreMinerMachine getMachine();

    @Inject(method = "getOreToProduce", at = @At("RETURN"), cancellable = true, remap = false)
    private void getOreToProduce(OreVeinWorldEntry entry, CallbackInfoReturnable<Integer> cir) {
        if (GTNN.INSTANCE.getServerConfig().skyblock) {
            var returnValue = cir.getReturnValue();
            cir.setReturnValue((int) Math.max(returnValue * 0.02, 1));
        }
    }
}
