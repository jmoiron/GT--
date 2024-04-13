package dev.arbor.gtnn.data

import com.gregtechceu.gtceu.api.item.RendererBlockItem
import com.tterrag.registrate.providers.DataGenContext
import com.tterrag.registrate.providers.RegistrateBlockstateProvider
import com.tterrag.registrate.providers.RegistrateItemModelProvider
import com.tterrag.registrate.util.entry.BlockEntry
import com.tterrag.registrate.util.nullness.NonNullBiConsumer
import dev.arbor.gtnn.GTNN.id
import dev.arbor.gtnn.GTNNRegistries.REGISTRATE
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour

object GTNNBlocks {
    val ITNT: BlockEntry<Block> = REGISTRATE.block<Block>(
        "itnt"
    ) { properties: BlockBehaviour.Properties -> Block(properties) }.initialProperties { Blocks.TNT }.lang("ITNT")
        .blockstate { ctx: DataGenContext<Block?, Block?>, prov: RegistrateBlockstateProvider ->
            prov.simpleBlock(
                ctx.entry, prov.models().getExistingFile(id("block/itnt"))
            )
        }.item { block: Block?, properties: Item.Properties? ->
            RendererBlockItem(
                block, properties
            )
        }.model(NonNullBiConsumer.noop<DataGenContext<Item, RendererBlockItem>, RegistrateItemModelProvider>()).build()
        .register()

    fun init() {
        REGISTRATE.creativeModeTab { GTNNCreativeModeTabs.MAIN_TAB }
    }
}
