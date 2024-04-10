package dev.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.item.RendererBlockItem;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import dev.arbor.gtnn.GTNN;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static dev.arbor.gtnn.GTNNRegistries.getREGISTRATE;

@SuppressWarnings("unused")
public class GTNNBlocks {
    public static final BlockEntry<Block> ITNT = getREGISTRATE().block("itnt", Block::new)
            .initialProperties(() -> Blocks.TNT)
            .lang("ITNT")
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), prov.models().getExistingFile(GTNN.id("block/itnt"))))
            .item(RendererBlockItem::new)
            .model(NonNullBiConsumer.noop())
            .build()
            .register();

    static {
        getREGISTRATE().creativeModeTab(() -> GTNNCreativeModeTabs.MAIN_TAB);
    }

    public static void init() {
    }
}
