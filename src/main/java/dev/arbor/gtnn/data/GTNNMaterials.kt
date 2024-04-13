package dev.arbor.gtnn.data

import com.gregtechceu.gtceu.GTCEu
import com.gregtechceu.gtceu.api.data.chemical.material.Material
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet
import com.gregtechceu.gtceu.api.data.chemical.material.properties.DustProperty
import com.gregtechceu.gtceu.api.data.chemical.material.properties.FluidProperty
import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey
import com.gregtechceu.gtceu.api.fluids.FluidBuilder
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys
import dev.arbor.gtnn.GTNN.getServerConfig
import dev.arbor.gtnn.GTNNIntegration.isAdAstraLoaded
import dev.arbor.gtnn.GTNNIntegration.isBotaniaLoaded
import dev.arbor.gtnn.GTNNIntegration.isCreateLoaded
import dev.arbor.gtnn.api.recipe.GTNNBuilder
import dev.arbor.gtnn.data.materials.*
import dev.arbor.gtnn.data.recipes.BrineChain

object GTNNMaterials {
    lateinit var AndesiteAlloy: Material
    lateinit var Desh: Material
    lateinit var Ostrum: Material
    lateinit var Calorite: Material
    lateinit var SpaceNeutronium: Material
    lateinit var Infinity: Material
    lateinit var InfinityCatalyst: Material
    lateinit var RP1: Material
    lateinit var RP1RocketFuel: Material
    lateinit var Kerosene: Material
    lateinit var DenseHydrazineMixedFuel: Material
    lateinit var Hydrazine: Material
    lateinit var HydrogenPeroxide: Material
    lateinit var EthylAnthraQuinone: Material
    lateinit var EthylAnthraHydroQuinone: Material
    lateinit var Anthracene: Material
    lateinit var MethylhydrazineNitrateRocketFuel: Material
    lateinit var MethylHydrazine: Material
    lateinit var UDMHRocketFuel: Material
    lateinit var UDMH: Material
    lateinit var OrangeMetalCatalyst: Material
    lateinit var PhthalicAnhydride: Material
    lateinit var VanadiumPentoxide: Material
    lateinit var BlackMatter: Material
    lateinit var Cerrobase140: Material
    lateinit var PotassiumPyrosulfate: Material
    lateinit var SodiumSulfate: Material
    lateinit var ZincSulfate: Material
    lateinit var Wollastonite: Material
    lateinit var ArcaneCrystal: Material
    lateinit var ManaSteel: Material
    lateinit var TerraSteel: Material
    lateinit var Elementium: Material
    lateinit var RefinedRadiance: Material
    lateinit var ShadowSteel: Material
    lateinit var PlatinumSalt: Material
    lateinit var RefinedPlatinumSalt: Material
    lateinit var PalladiumSalt: Material
    lateinit var RhodiumNitrate: Material
    lateinit var RoughlyRhodiumMetal: Material
    lateinit var PalladiumMetal: Material
    lateinit var MetalSludge: Material
    lateinit var PlatinumSlag: Material
    lateinit var ReprecipitatedRhodium: Material
    lateinit var SodiumNitrate: Material
    lateinit var RhodiumSalt: Material
    lateinit var RhodiumFilterCake: Material
    lateinit var PlatinumMetal: Material
    lateinit var Kaolinite: Material
    lateinit var Dolomite: Material
    lateinit var SodiumRutheniate: Material
    lateinit var IridiumDioxide: Material
    lateinit var ConcentratedPlatinum: Material
    lateinit var PalladiumRichAmmonia: Material
    lateinit var RutheniumTetroxideLQ: Material
    lateinit var SodiumFormate: Material
    lateinit var FormicAcid: Material
    lateinit var RhodiumSulfateGas: Material
    lateinit var AcidicIridium: Material
    lateinit var RutheniumTetroxideHot: Material
    lateinit var NaquadahOxideMixture: Material
    lateinit var EnrichedNaquadahOxideMixture: Material
    lateinit var NaquadriaOxideMixture: Material
    lateinit var HexafluorideEnrichedNaquadahSolution: Material
    lateinit var XenonHexafluoroEnrichedNaquadate: Material
    lateinit var PalladiumOnCarbon: Material
    lateinit var GoldTrifluoride: Material
    lateinit var EnrichedNaquadahResidueSolution: Material
    lateinit var XenoauricFluoroantimonicAcid: Material
    lateinit var GoldChloride: Material
    lateinit var BromineTrifluoride: Material
    lateinit var HexafluorideNaquadriaSolution: Material
    lateinit var RadonDifluoride: Material
    lateinit var RadonNaquadriaOctafluoride: Material
    lateinit var NaquadriaResidueSolution: Material
    lateinit var CaesiumFluoride: Material
    lateinit var XenonTrioxide: Material
    lateinit var CaesiumXenontrioxideFluoride: Material
    lateinit var NaquadriaCaesiumXenonnonfluoride: Material
    lateinit var RadonTrioxide: Material
    lateinit var NaquadriaCaesiumfluoride: Material
    lateinit var NitrosoniumOctafluoroxenate: Material
    lateinit var NitrylFluoride: Material
    lateinit var AcidicNaquadriaCaesiumfluoride: Material
    lateinit var GraphiteUraniumMixture: Material
    lateinit var UraniumCarbideThoriumMixture: Material
    lateinit var PlutoniumOxideUraniumMixture: Material
    lateinit var Thorium232: Material
    lateinit var ThoriumBasedLiquidFuelExcited: Material
    lateinit var ThoriumBasedLiquidFuelDepleted: Material
    lateinit var ThoriumBasedLiquidFuel: Material
    lateinit var UraniumBasedLiquidFuelExcited: Material
    lateinit var UraniumBasedLiquidFuelDepleted: Material
    lateinit var UraniumBasedLiquidFuel: Material
    lateinit var PlutoniumBasedLiquidFuelExcited: Material
    lateinit var PlutoniumBasedLiquidFuelDepleted: Material
    lateinit var PlutoniumBasedLiquidFuel: Material
    lateinit var RadiationProtection: Material
    lateinit var NaquadahBasedLiquidFuel: Material
    lateinit var NaquadahBasedLiquidFuelExcited: Material
    lateinit var NaquadahBasedLiquidFuelDepleted: Material
    lateinit var IodizedBrine: Material
    lateinit var IodineBrineMixture: Material
    lateinit var BrominatedBrine: Material
    lateinit var IodineSlurry: Material
    lateinit var AcidicBrominatedBrine: Material
    lateinit var BromineSulfateSolution: Material
    lateinit var OverheatedBromineSulfateSolution: Material
    lateinit var WetBromine: Material
    lateinit var DebrominatedWater: Material
    lateinit var NeutroniumMixture: Material

    fun addDust(material: Material) {
        material.setProperty(PropertyKey.DUST, DustProperty())
    }

    fun addFluid(material: Material) {
        material.setProperty(PropertyKey.FLUID, FluidProperty())
        material.getProperty(PropertyKey.FLUID).storage.enqueueRegistration(FluidStorageKeys.LIQUID, FluidBuilder())
    }

    fun addGas(material: Material) {
        material.setProperty(PropertyKey.FLUID, FluidProperty())
        material.getProperty(PropertyKey.FLUID).storage.enqueueRegistration(FluidStorageKeys.GAS, FluidBuilder())
    }

    fun addOre(material: Material) {
        material.setProperty(PropertyKey.ORE, OreProperty())
    }

    fun builder(id: String?): GTNNBuilder {
        return GTNNBuilder(GTCEu.id(id))
    }

    fun init() {
        AdjustGTMaterials.init()
        FirstMaterials.init()
        SecondMaterials.init()
        BrineChain.init()
        if (getServerConfig().enableHarderPlatinumLine) PlatinumLineMaterials.init()
        if (getServerConfig().enableHarderNaquadahLine) NaquadahMaterials.init()
        if (isAdAstraLoaded()) AdAstraMaterials.init()
        if (isBotaniaLoaded()) BotaniaMaterials.init()
        if (isCreateLoaded()) CreateMaterials.init()
    }


    object MaterialIcons {
        var InfinityIcon = MaterialIconSet("infinity", MaterialIconSet.SHINY)
    }
}
