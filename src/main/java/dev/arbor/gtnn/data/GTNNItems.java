package dev.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import dev.arbor.gtnn.GTNNIntegration;
import dev.arbor.gtnn.GTNNRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.loading.FMLLoader;
import org.jetbrains.annotations.NotNull;

import static dev.arbor.gtnn.GTNNRegistries.getREGISTRATE;

public class GTNNItems {
    public static final ItemEntry<Item> RADIOACTIVE_WASTE = createItem("radioactive_waste", Item::new)
            .lang("Radioactive Waste")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T1 = createItem("heavy_ingot_t1", ComponentItem::create)
            .lang("Heavy Alloy Ingot T1")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_ingot_t1.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T2 = createItem("heavy_ingot_t2", ComponentItem::create)
            .lang("Heavy Alloy Ingot T2")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_ingot_t2.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T3 = createItem("heavy_ingot_t3", ComponentItem::create)
            .lang("Heavy Alloy Ingot T3")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_ingot_t3.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T4 = createItem("heavy_ingot_t4", ComponentItem::create)
            .lang("Heavy Alloy Ingot T4")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_ingot_t4.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T1 = createItem("heavy_plate_t1", ComponentItem::create)
            .lang("Heavy Alloy Plate T1")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_plate_t1.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T2 = createItem("heavy_plate_t2", ComponentItem::create)
            .lang("Heavy Alloy Plate T2")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_plate_t2.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T3 = createItem("heavy_plate_t3", ComponentItem::create)
            .lang("Heavy Alloy Plate T3")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_plate_t3.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T4 = createItem("heavy_plate_t4", ComponentItem::create)
            .lang("Heavy Alloy Plate T4")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_plate_t4.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> CHIP_T1 = createItem("t1_chip", ComponentItem::create)
            .lang("Chip T1")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.chip_t1.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> CHIP_T2 = createItem("t2_chip", ComponentItem::create)
            .lang("Chip T2")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.chip_t2.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> CHIP_T3 = createItem("t3_chip", ComponentItem::create)
            .lang("Chip T3")
            .properties(p -> p.rarity(Rarity.EPIC))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.chip_t3.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> CHIP_T4 = createItem("t4_chip", ComponentItem::create)
            .lang("Chip T4")
            .properties(p -> p.rarity(Rarity.EPIC))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.chip_t4.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> INVERTER = createItem("inverter", ComponentItem::create)
            .lang("Inverter")
            .register();
    public static ItemEntry<ComponentItem> EncapsulatedUranium = createItem("encapsulated_uranium", ComponentItem::create)
            .lang("Encapsulated Uranium")
            .register();
    public static ItemEntry<ComponentItem> EnrichedUraniumNugget = createItem("enriched_uranium_nugget", ComponentItem::create)
            .lang("Enriched Uranium Nugget")
            .register();
    public static ItemEntry<ComponentItem> EnrichedUranium = createItem("enriched_uranium", ComponentItem::create)
            .lang("Enriched Uranium")
            .register();
    public static ItemEntry<ComponentItem> EncapsulatedThorium = createItem("encapsulated_thorium", ComponentItem::create)
            .lang("Encapsulated Thorium")
            .register();
    public static ItemEntry<ComponentItem> EnrichedThoriumNugget = createItem("enriched_thorium_nugget", ComponentItem::create)
            .lang("Enriched Thorium Nugget")
            .register();
    public static ItemEntry<ComponentItem> EnrichedThorium = createItem("enriched_thorium", ComponentItem::create)
            .lang("Enriched Thorium")
            .register();
    public static ItemEntry<ComponentItem> EncapsulatedPlutonium = createItem("encapsulated_plutonium", ComponentItem::create)
            .lang("Encapsulated Plutonium")
            .register();
    public static ItemEntry<ComponentItem> EnrichedPlutoniumNugget = createItem("enriched_plutonium_nugget", ComponentItem::create)
            .lang("Enriched Plutonium Nugget")
            .register();
    public static ItemEntry<ComponentItem> EnrichedPlutonium = createItem("enriched_plutonium", ComponentItem::create)
            .lang("Enriched Plutonium")
            .register();
    public static ItemEntry<ComponentItem> NeutronSource = createItem("neutron_source", ComponentItem::create)
            .lang("Neutron Source")
            .register();
    public static ItemEntry<ComponentItem> QuarkCore = createItem("quark_core", ComponentItem::create)
            .lang("Quark Core")
            .register();
    public static ItemEntry<ComponentItem> PlateRadiationProtection = createItem("plate_radiation_protection", ComponentItem::create)
            .lang("Radiation Protection Plate")
            .register();

    static {
        getREGISTRATE().creativeModeTab(() -> GTNNCreativeModeTabs.MAIN_TAB);
    }

    public static ItemEntry<ComponentItem> COMPUTER;
    public static ItemEntry<ComponentItem> COMPUTER_ADVANCED;

    private static NonNullConsumer<? super ComponentItem> attach(TooltipBehavior components) {
        return (item) -> item.attachComponents(components);
    }

    public static void init() {
        if (!GTNNIntegration.INSTANCE.isCCTweakedLoaded() || !FMLLoader.isProduction()) {
            COMPUTER = createItem("computer_circuit", ComponentItem::create)
                    .lang("Computer Chip")
                    .properties(p -> p.rarity(Rarity.UNCOMMON))
                    .register();
            COMPUTER_ADVANCED = createItem("computer_advanced_circuit", ComponentItem::create)
                    .lang("Advanced Computer Chip")
                    .properties(p -> p.rarity(Rarity.RARE))
                    .register();
        }
    }

    private static <T extends Item> @NotNull ItemBuilder<T, Registrate> createItem(String name, NonNullFunction<Item.Properties, T> factory) {
        return GTNNRegistries.getREGISTRATE().item(name, factory);
    }
}
