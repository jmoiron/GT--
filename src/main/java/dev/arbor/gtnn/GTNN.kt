package dev.arbor.gtnn

import com.gregtechceu.gtceu.api.machine.MachineDefinition
import com.gregtechceu.gtceu.utils.FormattingUtil
import dev.arbor.gtnn.config.ConfigHandler
import dev.arbor.gtnn.init.CommonProxy
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(GTNN.MODID)
object GTNN {
    const val MODID = "gtnn"

    @JvmStatic
    val LOGGER: Logger = LogManager.getLogger(MODID)

    init {
        MOD_BUS.register(this)
        MOD_BUS.addGenericListener(MachineDefinition::class.java, GTNNRegistries::registerMachine)
        CommonProxy.init()
    }

    @JvmStatic
    fun getClientConfig(): ConfigHandler.ClientConfigs {
        return ConfigHandler.INSTANCE.Client
    }

    @JvmStatic
    fun getServerConfig(): ConfigHandler.ServerConfigs {
        return ConfigHandler.INSTANCE.Server
    }

    @JvmStatic
    fun id(path: String): ResourceLocation {
        return ResourceLocation(MODID, FormattingUtil.toLowerCaseUnder(path))
    }
}

