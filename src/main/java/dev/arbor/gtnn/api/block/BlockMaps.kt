package dev.arbor.gtnn.api.block

import com.gregtechceu.gtceu.GTCEu
import com.gregtechceu.gtceu.common.data.GTBlocks.*
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import java.util.function.Supplier

object BlockMaps {
    val ALL_CP_CASINGS: Object2ObjectOpenHashMap<ITierType, Supplier<Block>> = Object2ObjectOpenHashMap()
    val ALL_CP_TUBES: Object2ObjectOpenHashMap<ITierType, Supplier<Block>> = Object2ObjectOpenHashMap()
    @JvmField
    val ALL_MACHINE_CASINGS: Object2ObjectOpenHashMap<ITierType, Supplier<Block>> = Object2ObjectOpenHashMap()

    fun initBlocks() {
        //  ALL_CP_TUBES Init
        simpleTierTypeAdd(ALL_CP_TUBES, CASING_BRONZE_PIPE, 1)
        simpleTierTypeAdd(ALL_CP_TUBES, CASING_STEEL_PIPE, 2)
        simpleTierTypeAdd(ALL_CP_TUBES, CASING_TITANIUM_PIPE, 3)
        simpleTierTypeAdd(ALL_CP_TUBES, CASING_TUNGSTENSTEEL_PIPE, 4)


        //  ALL_CP_CASINGS Init
        cpTierTypeAdd(
            CASING_BRONZE_BRICKS,
            1,
            GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks")
        )
        cpTierTypeAdd(
            CASING_STEEL_SOLID, 2, GTCEu.id("block/casings/solid/machine_casing_solid_steel")
        )
        cpTierTypeAdd(
            CASING_ALUMINIUM_FROSTPROOF, 3, GTCEu.id("block/casings/solid/machine_casing_frost_proof")
        )
        cpTierTypeAdd(
            CASING_STAINLESS_CLEAN,
            4,
            GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel")
        )
        cpTierTypeAdd(
            CASING_TITANIUM_STABLE, 5, GTCEu.id("block/casings/solid/machine_casing_stable_titanium")
        )
        cpTierTypeAdd(
            CASING_TUNGSTENSTEEL_ROBUST,
            6,
            GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel")
        )
    }

    private fun simpleTierTypeAdd(
        map: MutableMap<ITierType, Supplier<Block>>, blockSupplier: Supplier<Block>, tier: Int
    ) {
        map[WrappedTierType(blockSupplier, tier)] = blockSupplier
    }

    private fun cpTierTypeAdd(
        blockSupplier: Supplier<Block>, tier: Int, location: ResourceLocation
    ) {
        ALL_CP_CASINGS[IChemicalPlantCasing.CPCasingType(
            blockSupplier.get().descriptionId, tier, location
        )] = blockSupplier
    }
}