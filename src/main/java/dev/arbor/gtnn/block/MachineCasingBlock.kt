package dev.arbor.gtnn.block

import com.gregtechceu.gtceu.common.data.GTBlocks.*
import com.tterrag.registrate.util.entry.BlockEntry
import dev.arbor.gtnn.api.block.ITier
import dev.arbor.gtnn.api.block.MachineCasingType
import net.minecraft.world.level.block.Block
import dev.arbor.gtnn.block.BlockTier.*


class MachineCasingBlock(properties: Properties) : Block(properties) {

    enum class MachineCasing(
        val tier: ITier,
        private val machineCasingBlock: BlockEntry<Block>,
        val energyHatchLevel: String
    ) : MachineCasingType {
        LV(TIER0, MACHINE_CASING_LV, "§7LV§r"),
        MV(TIER1, MACHINE_CASING_MV, "§bMV§r"),
        HV(TIER2, MACHINE_CASING_HV, "§6HV§r"),
        EV(TIER3, MACHINE_CASING_EV, "§5EV§r"),
        IV(TIER4, MACHINE_CASING_IV, "§1IV§r"),
        LuV(TIER5, MACHINE_CASING_LuV, "§dLuV§r"),
        ZPM(TIER6, MACHINE_CASING_ZPM, "§cZPM§r"),
        UV(TIER7, MACHINE_CASING_UV, "§3UV§r");

        fun machineCasingType(): MachineCasingType {
            return this
        }

        override fun getMachineCasingTier(): Int {
            return tier.tier()
        }

        override fun getMachineCasing(): BlockEntry<Block> {
            return machineCasingBlock
        }

        fun getMachineCasing(tier: Int): BlockEntry<Block> {
            return MachineCasing.values()[tier].machineCasingBlock
        }
    }
}
