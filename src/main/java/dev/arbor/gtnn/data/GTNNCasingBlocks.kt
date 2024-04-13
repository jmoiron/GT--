package dev.arbor.gtnn.data

import com.gregtechceu.gtceu.api.block.RendererBlock
import com.gregtechceu.gtceu.api.item.RendererBlockItem
import com.gregtechceu.gtceu.api.item.tool.GTToolType
import com.gregtechceu.gtceu.client.renderer.block.TextureOverrideRenderer
import com.lowdragmc.lowdraglib.Platform
import com.lowdragmc.lowdraglib.client.renderer.IRenderer
import com.tterrag.registrate.providers.DataGenContext
import com.tterrag.registrate.providers.RegistrateBlockstateProvider
import com.tterrag.registrate.providers.RegistrateItemModelProvider
import com.tterrag.registrate.util.entry.BlockEntry
import com.tterrag.registrate.util.nullness.NonNullBiConsumer
import com.tterrag.registrate.util.nullness.NonNullSupplier
import dev.arbor.gtnn.GTNN.id
import dev.arbor.gtnn.GTNNRegistries.REGISTRATE
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import java.util.function.BiFunction

object GTNNCasingBlocks {

    val PROCESS_MACHINE_CASING: BlockEntry<Block> = createCasingBlock("clean_machine_casing",
        "Clean Machine Casing",
        { properties: BlockBehaviour.Properties?, renderer: IRenderer? ->
            RendererBlock(
                properties, renderer
            )
        },
        id("block/casings/solid/process_machine_casing"),
        { Blocks.IRON_BLOCK })

    val RADIATION_PROOF_MACHINE_CASING: BlockEntry<Block> = createCasingBlock("radiation_proof_machine_casing",
        "Radiation Proof Machine Casing",
        { properties: BlockBehaviour.Properties?, renderer: IRenderer? ->
            RendererBlock(
                properties, renderer
            )
        },
        id("block/casings/solid/radiation_proof_machine_casing"),
        { Blocks.IRON_BLOCK })

    val MAR_CASING: BlockEntry<Block> = createCasingBlock("mar_casing",
        "Field Restriction Casing",
        { properties: BlockBehaviour.Properties?, renderer: IRenderer? ->
            RendererBlock(
                properties, renderer
            )
        },
        id("block/casings/solid/mar_casing"),
        { Blocks.IRON_BLOCK })

    private fun createCasingBlock(
        name: String,
        displayName: String,
        blockSupplier: BiFunction<BlockBehaviour.Properties, IRenderer?, out RendererBlock>,
        texture: ResourceLocation,
        properties: NonNullSupplier<out Block>
    ): BlockEntry<Block> {
        return REGISTRATE.block<Block>(
            name
        ) { p: BlockBehaviour.Properties ->
            blockSupplier.apply(
                p, if (Platform.isClient()) TextureOverrideRenderer(
                    ResourceLocation("block/cube_all"), mapOf("all" to texture)
                ) else null
            )
        }.initialProperties(properties).lang(displayName)
            .blockstate(NonNullBiConsumer.noop<DataGenContext<Block, Block>, RegistrateBlockstateProvider>())
            .tag(GTToolType.WRENCH.harvestTags[0], BlockTags.MINEABLE_WITH_PICKAXE)
            .item { block: Block?, prop: Item.Properties? ->
                RendererBlockItem(
                    block, prop
                )
            }.model(NonNullBiConsumer.noop<DataGenContext<Item, RendererBlockItem>, RegistrateItemModelProvider>())
            .build().register()
    }

    fun init() {
        REGISTRATE.creativeModeTab { GTNNCreativeModeTabs.MAIN_TAB }
    }
}
