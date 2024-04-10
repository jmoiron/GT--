package dev.arbor.gtnn.data.misc;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import dev.arbor.gtnn.data.GTNNTagPrefix;
import earth.terrarium.adastra.common.registry.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class AdAstraAddon {

    private static ResourceLocation getBlock(String name) {
        return new ResourceLocation("ad_astra", name);
    }

    public static void init() {
        GTNNTagPrefix.oreMoonStone = TagPrefix.oreTagPrefix("moon_stone", BlockTags.MINEABLE_WITH_PICKAXE)
                .langValue("MoonStone %s Ore")
                .registerOre(() -> ModBlocks.MOON_STONE.get().defaultBlockState(), null,
                        BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        getBlock("block/moon_stone"));
        GTNNTagPrefix.oreVenusStone = TagPrefix.oreTagPrefix("venus_stone", BlockTags.MINEABLE_WITH_PICKAXE)
                .langValue("VenusStone %s Ore")
                .registerOre(() -> ModBlocks.VENUS_STONE.get().defaultBlockState(), null,
                        BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        getBlock("block/venus_stone"));
        GTNNTagPrefix.oreMarsStone = TagPrefix.oreTagPrefix("mars_stone", BlockTags.MINEABLE_WITH_PICKAXE)
                .langValue("MarsStone %s Ore")
                .registerOre(() -> ModBlocks.MARS_STONE.get().defaultBlockState(), null,
                        BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        getBlock("block/mars_stone"));
        GTNNTagPrefix.oreMercuryStone = TagPrefix.oreTagPrefix("mercury_stone", BlockTags.MINEABLE_WITH_PICKAXE)
                .langValue("MercuryStone %s Ore")
                .registerOre(() -> ModBlocks.MERCURY_STONE.get().defaultBlockState(), null,
                        BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        getBlock("block/mercury_stone"));
        GTNNTagPrefix.oreGlacioStone = TagPrefix.oreTagPrefix("glacio_stone", BlockTags.MINEABLE_WITH_PICKAXE)
                .langValue("GlacioStone %s Ore")
                .registerOre(() -> ModBlocks.GLACIO_STONE.get().defaultBlockState(), null,
                        BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        getBlock("block/glacio_stone"));
        GTNNTagPrefix.oreBlackStone = TagPrefix.oreTagPrefix("blackstone", BlockTags.MINEABLE_WITH_PICKAXE)
                .langValue("BlackStone %s Ore")
                .registerOre(Blocks.BLACKSTONE::defaultBlockState, null,
                        BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        new ResourceLocation("block/blackstone"));
        GTNNTagPrefix.oreSoulSoil = TagPrefix.oreTagPrefix("soul_soil", BlockTags.MINEABLE_WITH_SHOVEL)
                .langValue("SoulSoil %s Ore")
                .registerOre(Blocks.SOUL_SOIL::defaultBlockState, null,
                        BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        new ResourceLocation("block/soul_soil"));
    }
}
