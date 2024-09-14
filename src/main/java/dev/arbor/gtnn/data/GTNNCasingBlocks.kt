package dev.arbor.gtnn.data

import com.gregtechceu.gtceu.api.item.tool.GTToolType
import com.gregtechceu.gtceu.common.data.GTModels
import com.tterrag.registrate.util.entry.BlockEntry
import com.tterrag.registrate.util.nullness.NonNullFunction
import com.tterrag.registrate.util.nullness.NonNullSupplier
import dev.arbor.gtnn.GTNN.id
import dev.arbor.gtnn.GTNNRegistries.REGISTRATE
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import java.util.function.Supplier

object GTNNCasingBlocks {

    val PROCESS_MACHINE_CASING: BlockEntry<Block> =
        createCasingBlock("clean_machine_casing", id("block/casings/solid/process_machine_casing"))

    val RADIATION_PROOF_MACHINE_CASING: BlockEntry<Block> =
        createCasingBlock("radiation_proof_machine_casing", id("block/casings/solid/radiation_proof_machine_casing"))

    val MAR_CASING: BlockEntry<Block> =
        createCasingBlock("field_restriction_casing", id("block/casings/solid/mar_casing"))

    private fun createCasingBlock(name: String, texture: ResourceLocation): BlockEntry<Block> {
        return createCasingBlock(name,
            { properties: BlockBehaviour.Properties -> Block(properties) },
            texture,
            { Blocks.IRON_BLOCK },
            { Supplier { RenderType.cutoutMipped() } })
    }

    @Suppress("DEPRECATION", "removal")
    private fun createCasingBlock(
        name: String,
        blockSupplier: NonNullFunction<BlockBehaviour.Properties, Block>,
        texture: ResourceLocation,
        properties: NonNullSupplier<out Block>,
        type: Supplier<Supplier<RenderType>>
    ): BlockEntry<Block> {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties { p: BlockBehaviour.Properties -> p.isValidSpawn { _, _, _, _ -> false } }
                .addLayer(type).blockstate(GTModels.cubeAllModel(name, texture))
                .tag(GTToolType.WRENCH.harvestTags[0], BlockTags.MINEABLE_WITH_PICKAXE)
                .item { block, itemProperties -> BlockItem(block, itemProperties) }
                .build().register()
    }

    fun init() {
        REGISTRATE.creativeModeTab { GTNNCreativeModeTabs.MAIN_TAB }
    }
}
