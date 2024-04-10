package dev.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import dev.arbor.gtnn.GTNNIntegration;
import dev.arbor.gtnn.data.tags.AdAstraTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class GTNNTags {
    public static void initBlock(RegistrateTagsProvider<Block> provider) {
        if (GTNNIntegration.INSTANCE.isAdAstraLoaded()) {
            AdAstraTag.init(provider);
        }
    }
    public static final TagKey<Block> AD_ASTRA_STONES = TagUtil.createBlockTag("ad_astra_stones");
}
