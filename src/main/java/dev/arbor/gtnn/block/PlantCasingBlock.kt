package dev.arbor.gtnn.block

import com.gregtechceu.gtceu.common.data.GTBlocks
import com.tterrag.registrate.util.entry.BlockEntry
import dev.arbor.gtnn.api.block.ITier
import dev.arbor.gtnn.api.block.PlantCasingType
import dev.arbor.gtnn.block.BlockTier.*
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block

enum class PlantCasingBlock(
    private val tier: ITier,
    private val blockName: String,
    private val plantCasing: BlockEntry<Block>
) : PlantCasingType {
    BRONZE(TIER0, "Bronze", GTBlocks.CASING_BRONZE_BRICKS),
    STEEL(TIER1, "Steel", GTBlocks.CASING_STEEL_SOLID),
    ALUMINIUM(TIER2, "Aluminium", GTBlocks.CASING_ALUMINIUM_FROSTPROOF),
    STAINLESS(TIER3, "Stainless", GTBlocks.CASING_STAINLESS_CLEAN),
    TITANIUM(TIER4, "Titanium", GTBlocks.CASING_TITANIUM_STABLE),
    TUNGSTENSTEEL(TIER5, "Tungstensteel", GTBlocks.CASING_TUNGSTENSTEEL_ROBUST),
    CHROME(TIER6, "Chrome", GTBlocks.CASING_TUNGSTENSTEEL_ROBUST),
    IRIDIUM(TIER7, "Iridium", GTBlocks.CASING_TUNGSTENSTEEL_ROBUST);

    internal object PlantCasing {
        val All_PlantCasingBlock: MutableMap<String?, PlantCasingBlock> = Object2ObjectOpenHashMap()
        val All_PlantCasing_Tier: MutableMap<Int, PlantCasingBlock> = Object2ObjectOpenHashMap()
    }

    companion object {
        fun getByTier(tier: Int): PlantCasingBlock {
            return PlantCasing.All_PlantCasing_Tier[tier] ?: BRONZE
        }

        fun getByName(name: String?): PlantCasingBlock {
            return getByNameOrDefault(name)
        }

        fun getByNameOrDefault(name: String?): PlantCasingBlock {
            return PlantCasing.All_PlantCasingBlock[name] ?: return BRONZE
        }
    }

    init {
        PlantCasing.All_PlantCasingBlock[name] = this
        PlantCasing.All_PlantCasing_Tier[tier.tier()] = this
    }

    fun plantCasingType(): PlantCasingType {
        return this
    }

    override fun getName(): String {
        return blockName
    }

    override fun getTier(): Int {
        return tier.tier()
    }

    override fun getPlantCasing(tier: Int): BlockEntry<Block> {
        return getByTier(tier).getPlantCasing()
    }

    override fun getPlantCasing(): BlockEntry<Block> {
        return plantCasing
    }

    override fun getResourceLocation(): ResourceLocation {
        return plantCasing.id
    }
}
