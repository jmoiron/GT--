package dev.arbor.gtnn.mixin.emi;

import dev.arbor.gtnn.GTNN;
import dev.emi.emi.api.recipe.EmiPlayerInventory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(value = EmiPlayerInventory.class, remap = false)
public class EmiPlayerInventoryMixin {
    @Shadow
    public Map<EmiStack, EmiStack> inventory;

    @Unique
    private static int gtnn$getDamageAmount(EmiStack emiStack, boolean damage) {
        ItemStack itemStack = emiStack.getItemStack();
        if (!itemStack.isEmpty()) {
            ItemStack newItemStack = itemStack.getItem().getDefaultInstance();
            ItemStack remainder = ForgeHooks.getCraftingRemainingItem(newItemStack);
            int damage0 = newItemStack.getDamageValue();
            int damage1 = remainder.getDamageValue();
            int dDamage = damage1 - damage0;
            if (!remainder.isEmpty() && remainder.is(newItemStack.getItem()) && damage0 != damage1) {
                return damage ? dDamage : (itemStack.getMaxDamage() - itemStack.getDamageValue()) / (dDamage);
            }
        }
        return 0;
    }

    @Unique
    private static int gtnn$getDamagePerCraft(EmiStack emiStack) {
        return gtnn$getDamageAmount(emiStack, true);
    }

    @Unique
    private static int gtnn$getDamageAvailable(EmiStack emiStack) {
        return gtnn$getDamageAmount(emiStack, false);
    }

    @Inject(
            method = "addStack(Ldev/emi/emi/api/stack/EmiStack;)V", at = @At("HEAD"), cancellable = true
    )
    private void addStack(EmiStack stack, CallbackInfo ci) {
        if (GTNN.INSTANCE.getServerConfig().makesEMIBetter) {
            if (!stack.isEmpty()) {
                if (!inventory.containsKey(stack)) {
                    inventory.put(stack, stack.setAmount(gtnn$getDamageAvailable(stack)));
                } else {
                    EmiStack stack1 = inventory.get(stack);
                    stack1.setAmount(stack1.getAmount() + gtnn$getDamageAvailable(stack));
                }
            }
            ci.cancel();
        }
    }

    @Redirect(
            method = "canCraft(Ldev/emi/emi/api/recipe/EmiRecipe;J)Z",
            at = @At(value = "INVOKE", target = "Ldev/emi/emi/api/stack/EmiStack;getAmount()J", ordinal = 0)
    )
    private long getDamageAmount(EmiStack emiStack) {
        int damage = 0;
        if (GTNN.INSTANCE.getServerConfig().makesEMIBetter) {
            damage = gtnn$getDamagePerCraft(emiStack);
        }
        return damage != 0 ? damage : emiStack.getAmount();
    }
}
