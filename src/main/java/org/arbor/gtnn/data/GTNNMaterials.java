package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.FluidProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import org.arbor.gtnn.GTNNIntegration;
import org.arbor.gtnn.config.ConfigHandler;
import org.arbor.gtnn.data.materials.*;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.SHINY;

public class GTNNMaterials {
    // MaterialIconSet
    public static MaterialIconSet InfinityIcon = new MaterialIconSet("infinity", SHINY);
    // region first degree mats
    public static Material AndesiteAlloy;
    public static Material Desh;
    public static Material Ostrum;
    public static Material Calorite;
    public static Material SpaceNeutronium;
    public static Material Infinity;
    public static Material InfinityCatalyst;
    // Chemical Plant
    public static Material RP1;
    public static Material RP1RocketFuel;
    public static Material Kerosene;
    public static Material DenseHydrazineMixedFuel;
    public static Material Hydrazine;
    public static Material HydrogenPeroxide;
    public static Material EthylAnthraQuinone;
    public static Material EthylAnthraHydroQuinone;
    public static Material Anthracene;
    public static Material MethylhydrazineNitrateRocketFuel;
    public static Material MethylHydrazine;
    public static Material UDMHRocketFuel;
    public static Material UDMH;
    public static Material OrangeMetalCatalyst;
    public static Material PhthalicAnhydride;
    public static Material VanadiumPentoxide;
    public static Material BlackMatter;
    public static Material Cerrobase140;
    public static Material SodiumPyrosulfate;
    public static Material SodiumSulfate;
    public static Material ZincSulfate;
    public static Material Wollastonite;
    public static Material ArcaneCrystal;
    // Ingot
    public static Material ManaSteel;
    public static Material TerraSteel;
    public static Material Elementium;
    public static Material RefinedRadiance;
    public static Material ShadowSteel;
    // Platinum
    public static Material PlatinumSalt;
    public static Material RefinedPlatinumSalt;
    public static Material PalladiumSalt;
    public static Material RhodiumNitrate;
    public static Material RoughlyRhodiumMetal;
    public static Material PalladiumMetal;
    public static Material MetalSludge;
    public static Material PlatinumSlag;
    public static Material ReprecipitatedRhodium;
    public static Material SodiumNitrate;
    public static Material RhodiumSalt;
    public static Material RhodiumFilterCake;
    public static Material PlatinumMetal;
    public static Material Kaolinite;
    public static Material Dolomite;
    public static Material SodiumRutheniate;
    public static Material IridiumDioxide;
    public static Material ConcentratedPlatinum;
    public static Material PalladiumRichAmmonia;
    public static Material RutheniumTetroxideLQ;
    public static Material SodiumFormate;
    public static Material FormicAcid;
    public static Material RhodiumSulfateGas;
    public static Material AcidicIridium;
    public static Material RutheniumTetroxideHot;
    // Naquadah
    public static Material NaquadahOxideMixture;
    public static Material EnrichedNaquadahOxideMixture;
    public static Material NaquadriaOxideMixture;
    public static Material HexafluorideEnrichedNaquadahSolution;
    public static Material XenonHexafluoroEnrichedNaquadate;
    public static Material PalladiumOnCarbon;
    public static Material GoldTrifluoride;
    public static Material EnrichedNaquadahResidueSolution;
    public static Material XenoauricFluoroantimonicAcid;
    public static Material GoldChloride;
    public static Material BromineTrifluoride;
    public static Material HexafluorideNaquadriaSolution;
    public static Material RadonDifluoride;
    public static Material RadonNaquadriaOctafluoride;
    public static Material NaquadriaResidueSolution;
    public static Material CaesiumFluoride;
    public static Material XenonTrioxide;
    public static Material CaesiumXenontrioxideFluoride;
    public static Material NaquadriaCaesiumXenonnonfluoride;
    public static Material RadonTrioxide;
    public static Material NaquadriaCaesiumfluoride;
    public static Material NitrosoniumOctafluoroxenate;
    public static Material NitrylFluoride;
    public static Material AcidicNaquadriaCaesiumfluoride;

    public static void init() {
        FirstMaterials.init();
        SecondMaterials.init();
        if (ConfigHandler.INSTANCE.Server.enableHarderPlatinumLine) PlatinumLineMaterials.init();
        if (ConfigHandler.INSTANCE.Server.enableHarderNaquadahLine) NaquadahMaterials.init();
        if (GTNNIntegration.isAdAstraLoaded()) AdAstraMaterials.init();
        if (GTNNIntegration.isBotaniaLoaded()) BotaniaMaterials.init();
        if (GTNNIntegration.isCreateLoaded()) CreateMaterials.init();
        if (GTNNIntegration.isForbiddenArcanusLoaded()) ForbiddenArcanusMaterials.init();
    }

    public static void addFluid(Material material){
        material.setProperty(PropertyKey.FLUID, new FluidProperty());
        material.getProperty(PropertyKey.FLUID).getStorage().enqueueRegistration(FluidStorageKeys.LIQUID, new FluidBuilder());
    }

    public static void addOre(Material material){
        material.setProperty(PropertyKey.ORE, new OreProperty());
    }

    public static Material.Builder Builder(String id) {
        return new Material.Builder(GTCEu.id(id));
    }
}
