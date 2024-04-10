package dev.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import dev.arbor.gtnn.GTNNIntegration;
import dev.arbor.gtnn.data.misc.AdAstraAddon;

public class GTNNTagPrefix {
    public static TagPrefix oreMoonStone;
    public static TagPrefix oreVenusStone;
    public static TagPrefix oreMarsStone;
    public static TagPrefix oreMercuryStone;
    public static TagPrefix oreGlacioStone;
    public static TagPrefix oreBlackStone;
    public static TagPrefix oreSoulSoil;

    public static void init() {
        if (GTNNIntegration.INSTANCE.isAdAstraLoaded()) AdAstraAddon.init();
    }
}
