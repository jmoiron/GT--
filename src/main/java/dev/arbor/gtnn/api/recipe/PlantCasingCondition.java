package dev.arbor.gtnn.api.recipe;

import com.google.gson.JsonObject;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import dev.arbor.gtnn.api.machine.multiblock.ChemicalPlantMachine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@Getter
@NoArgsConstructor
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class PlantCasingCondition extends RecipeCondition {
    public static final int BRONZE = 1;
    public static final int STEEL = 2;
    public static final int ALUMINIUM = 3;
    public static final int STAINLESS_STEEL = 4;
    public static final int TITANIUM = 5;
    public static final int TUNGSTEN_STEEL = 6;

    public static Map<Integer, String> CASING_TIERS = Map.of(
            BRONZE, "gtnn.recipe.condition.plant_casing.tier.bronze",
            STEEL, "gtnn.recipe.condition.plant_casing.tier.steel",
            ALUMINIUM, "gtnn.recipe.condition.plant_casing.tier.aluminium",
            STAINLESS_STEEL, "gtnn.recipe.condition.plant_casing.tier.stainless_steel",
            TITANIUM, "gtnn.recipe.condition.plant_casing.tier.titanium",
            TUNGSTEN_STEEL, "gtnn.recipe.condition.plant_casing.tier.tungsten_steel"
    );

    public static final PlantCasingCondition INSTANCE = new PlantCasingCondition();

    private int tier = 0;

    public PlantCasingCondition(int tier) {
        this.tier = Mth.clamp(tier, 1, 6);
    }

    @Override
    public String getType() {
        return "chemical_plant_casing_condition";
    }

    @Override
    public Component getTooltips() {
        return Component.translatable(
                "gtnn.recipe.condition.plant_casing.tooltip",
                tier, Component.translatable(CASING_TIERS.get(tier))
        );
    }

    @Override
    public boolean test(@NotNull GTRecipe gtRecipe, @NotNull RecipeLogic recipeLogic) {
        if (recipeLogic.machine instanceof ChemicalPlantMachine chemicalPlantMachine) {
            return chemicalPlantMachine.getCasingTier() >= tier;
        }
        return false;
    }

    @Override
    public RecipeCondition createTemplate() {
        return new PlantCasingCondition();
    }

    @Override
    public JsonObject serialize() {
        JsonObject value = super.serialize();
        value.addProperty("CPTier", tier);
        return value;
    }

    @Override
    public RecipeCondition deserialize(JsonObject config) {
        super.deserialize(config);
        this.tier = GsonHelper.getAsInt(config, "CPTier", 0);
        return this;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf) {
        super.toNetwork(buf);
        buf.writeInt(tier);
    }

    @Override
    public RecipeCondition fromNetwork(FriendlyByteBuf buf) {
        super.fromNetwork(buf);
        this.tier = buf.readInt();
        return this;
    }
}
