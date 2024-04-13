package dev.arbor.gtnn.data.materials

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*
import com.gregtechceu.gtceu.common.data.GTMaterials.*
import dev.arbor.gtnn.data.GTNNMaterials

object AdAstraMaterials {
    fun init() {
        GTNNMaterials.addOre(Neutronium)
        GTNNMaterials.addOre(Perlite)
        GTNNMaterials.addOre(Uvarovite)
        GTNNMaterials.addOre(Andradite)
        GTNNMaterials.addOre(Arsenic)
        GTNNMaterials.addOre(Bismuth)
        GTNNMaterials.addOre(Antimony)
        GTNNMaterials.addOre(Uranium235)
        GTNNMaterials.addOre(Uranium238)
        GTNNMaterials.addOre(Plutonium241)
        GTNNMaterials.addOre(Gallium)
        GTNNMaterials.addOre(Niobium)
        GTNNMaterials.addOre(Vanadium)
        GTNNMaterials.addOre(Osmium)
        GTNNMaterials.addOre(Iridium)
        GTNNMaterials.addOre(Titanium)
        GTNNMaterials.addOre(Manganese)
        GTNNMaterials.addOre(Rutile)
        GTNNMaterials.addOre(Tungsten)
        GTNNMaterials.addOre(Chromium)
        GTNNMaterials.addOre(NaquadahEnriched)
        GTNNMaterials.addOre(Naquadria)
        GTNNMaterials.Desh = GTNNMaterials.builder("desh")
            .ingot().fluid().ore()
            .color(0xF2A057).secondaryColor(0x2E2F04).iconSet(METALLIC)
            .appendFlags(
                EXT2_METAL,
                GENERATE_ROTOR,
                GENERATE_DENSE,
                GENERATE_SMALL_GEAR
            )
            .buildAndRegister()
        GTNNMaterials.Ostrum = GTNNMaterials.builder("ostrum")
            .ingot().fluid().ore()
            .color(0xE5939B).secondaryColor(0x2F0425).iconSet(METALLIC)
            .appendFlags(
                EXT2_METAL,
                GENERATE_ROTOR,
                GENERATE_DENSE,
                GENERATE_SMALL_GEAR
            )
            .buildAndRegister()
        GTNNMaterials.Calorite = GTNNMaterials.builder("calorite")
            .ingot().fluid().ore()
            .color(0xE65757).secondaryColor(0x2F0506).iconSet(METALLIC)
            .appendFlags(
                EXT2_METAL,
                GENERATE_ROTOR,
                GENERATE_DENSE,
                GENERATE_SMALL_GEAR
            )
            .buildAndRegister()
    }
}
