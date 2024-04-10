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
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException

@Mod.EventBusSubscriber(modid = GTNN.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
class GTNNRegistries {
    companion object {
        lateinit var MATERIAL_REGISTRY: MaterialRegistry

        @JvmStatic
        val REGISTRATE = GTRegistrate.create(GTNN.MODID)
        fun registerMachine(@Suppress("UNUSED_PARAMETER") event: GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition>) {
            GTNNMachines.init()
        }

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

    @SubscribeEvent
    fun registerMaterialRegistryEvent(@Suppress("UNUSED_PARAMETER") event: MaterialRegistryEvent) {
        MATERIAL_REGISTRY = GTCEuAPI.materialManager.createRegistry(GTNN.MODID)
    }

    @SubscribeEvent
    fun registerMaterials(@Suppress("UNUSED_PARAMETER") event: MaterialEvent) {
        GTNNMaterials.init()
    }
}
