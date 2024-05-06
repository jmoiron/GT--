package dev.arbor.gtnn

import com.gregtechceu.gtceu.api.machine.MachineDefinition
import com.gregtechceu.gtceu.utils.FormattingUtil
import dev.arbor.gtnn.config.ConfigHandler
import dev.arbor.gtnn.init.CommonProxy
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.eventbus.api.IEventBus
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object GTNN {
    const val MODID = "gtnn"

    val LOGGER: Logger by lazy { LogManager.getLogger(MODID) }

    fun init() {
        CommonProxy.init()
    }

    fun getClientConfig(): ConfigHandler.ClientConfigs {
        return ConfigHandler.INSTANCE.Client
    }

    fun getServerConfig(): ConfigHandler.ServerConfigs {
        return ConfigHandler.INSTANCE.Server
    }

    fun id(path: String): ResourceLocation {
        return ResourceLocation(MODID, FormattingUtil.toLowerCaseUnder(path))
    }

    @JvmStatic
    fun genericListener(modBus: IEventBus) {
        modBus.addGenericListener(MachineDefinition::class.java, GTNNRegistries::registerMachine)
    }

    @JvmStatic
    fun register(modBus: IEventBus) {
        modBus.addListener(GTNNRegistries::registerMaterials)
        modBus.addListener(GTNNRegistries::registerMaterialRegistryEvent)
    }
}

