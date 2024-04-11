package dev.arbor.gtnn

import com.gregtechceu.gtceu.api.GTCEuAPI
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry
import com.gregtechceu.gtceu.api.machine.MachineDefinition
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate
import dev.arbor.gtnn.data.GTNNMachines
import dev.arbor.gtnn.data.GTNNMaterials
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.FilePackResources
import net.minecraft.server.packs.PackResources
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException

object GTNNRegistries {
    private lateinit var MATERIAL_REGISTRY: MaterialRegistry

    @JvmField
    val REGISTRATE = GTRegistrate.create(GTNN.MODID)

    @JvmStatic
    fun registerMachine(@Suppress("UNUSED_PARAMETER") event: GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition>) {
        GTNNMachines.init()
        GTNN.LOGGER.info("register GTNN Machines")
    }

    @JvmStatic
    fun registerMaterialRegistryEvent(@Suppress("UNUSED_PARAMETER") event: MaterialRegistryEvent) {
        MATERIAL_REGISTRY = GTCEuAPI.materialManager.createRegistry(GTNN.MODID)
        GTNN.LOGGER.info("register GTNN Materials")
    }

    @JvmStatic
    fun registerMaterials(@Suppress("UNUSED_PARAMETER") event: MaterialEvent) {
        GTNNMaterials.init()
    }

    @JvmStatic
    fun getAllPackResources(): List<PackResources> {
        val packResources = ArrayList<PackResources>()
        if (GTNNIntegration.isAdAstraLoaded()) {
            val inputStream = GTNNRegistries::class.java.getResourceAsStream("/data/gtnn/ad_astra.zip")!!
            try {
                val tempFile = File.createTempFile("temp", ".tmp")
                FileUtils.copyInputStreamToFile(inputStream, tempFile)
                inputStream.close()
                packResources.add(FilePackResources(tempFile.getName(), tempFile, false))
            } catch (e: IOException) {
                GTNN.LOGGER.error("ad_astra.zip wrong!", e)
            }
        }
        return packResources
    }
}
