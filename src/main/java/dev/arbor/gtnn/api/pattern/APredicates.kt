package dev.arbor.gtnn.api.pattern

import com.gregtechceu.gtceu.api.GTValues.*
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility
import com.gregtechceu.gtceu.api.pattern.Predicates
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate
import com.gregtechceu.gtceu.api.pattern.error.PatternStringError
import com.lowdragmc.lowdraglib.utils.BlockInfo
import dev.arbor.gtnn.api.block.BlockMaps
import net.minecraft.network.chat.Component


@SuppressWarnings("unused")
object APredicates {
    fun plantCasings(): TraceabilityPredicate {
        return TraceabilityPredicate({
            val blockState = it.getBlockState()
            for (entry in BlockMaps.ALL_CP_CASINGS) {
                if (blockState.`is`(entry.value.get())) {
                    val stats = entry.key
                    val currentPlantCasing = it.matchContext.getOrPut("PlantCasing", stats)
                    if (!currentPlantCasing.equals(stats)) {
                        it.setError(PatternStringError("gtnn.multiblock.pattern.error.plant_casings"))
                        return@TraceabilityPredicate false
                    }
                    return@TraceabilityPredicate true
                }
            }
            return@TraceabilityPredicate false
        }, {
            BlockMaps.ALL_CP_CASINGS.values
                .map { BlockInfo.fromBlockState(it.get().defaultBlockState()) }
                .toTypedArray()
        }).addTooltips(Component.translatable("gtnn.multiblock.pattern.error.plant_casings"))
    }

    fun pipeBlock(): TraceabilityPredicate {
        return TraceabilityPredicate({
            val blockState = it.getBlockState()
            for (entry in BlockMaps.ALL_CP_TUBES) {
                if (blockState.`is`(entry.value.get())) {
                    val stats = entry.key
                    val currentPipeBlock = it.matchContext.getOrPut("Pipe", stats)
                    if (!currentPipeBlock.equals(stats)) {
                        it.setError(PatternStringError("gtnn.multiblock.pattern.error.pipe"))
                        return@TraceabilityPredicate false
                    }
                    return@TraceabilityPredicate true
                }
            }
            return@TraceabilityPredicate false
        }, {
            BlockMaps.ALL_CP_TUBES
                .map { BlockInfo.fromBlockState(it.value.get().defaultBlockState()) }
                .toTypedArray()
        }).addTooltips(Component.translatable("gtnn.multiblock.pattern.error.pipe"))
    }

    fun machineCasing(): TraceabilityPredicate {
        return TraceabilityPredicate({
            val blockState = it.getBlockState()
            for (entry in BlockMaps.ALL_MACHINE_CASINGS) {
                if (blockState.`is`(entry.value.get())) {
                    val stats = entry.key
                    val currentMachineCasing = it.matchContext.getOrPut("MachineCasing", stats)
                    if (!currentMachineCasing.equals(stats)) {
                        it.setError(PatternStringError("gtnn.multiblock.pattern.error.machine_casing"))
                        return@TraceabilityPredicate false
                    }
                    return@TraceabilityPredicate true
                }
            }
            return@TraceabilityPredicate false
        }, {
            BlockMaps.ALL_MACHINE_CASINGS
                .map { BlockInfo.fromBlockState(it.value.get().defaultBlockState()) }
                .toTypedArray()
        }).addTooltips(Component.translatable("gtnn.multiblock.pattern.error.machine_casing"))
    }

    fun ability(ability: PartAbility): TraceabilityPredicate {
        val tiers = listOf(ULV, LV, MV, HV, EV, IV, LuV, ZPM, UV).filter { it <= 1 }.toIntArray()
        return Predicates.blocks(*(if (tiers.isEmpty()) ability.allBlocks else ability.getBlocks(*tiers)).toTypedArray())
    }
}
