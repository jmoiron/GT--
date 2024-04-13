package dev.arbor.gtnn.data

import com.gregtechceu.gtceu.api.data.tag.TagPrefix
import dev.arbor.gtnn.GTNNIntegration.isAdAstraLoaded
import dev.arbor.gtnn.data.misc.AdAstraAddon

object GTNNTagPrefix {
    var oreMoonStone: TagPrefix? = null
    var oreVenusStone: TagPrefix? = null
    var oreMarsStone: TagPrefix? = null
    var oreMercuryStone: TagPrefix? = null
    var oreGlacioStone: TagPrefix? = null
    var oreBlackStone: TagPrefix? = null
    var oreSoulSoil: TagPrefix? = null

    fun init() {
        if (isAdAstraLoaded()) AdAstraAddon.init()
    }
}
