package dev.arbor.gtnn.api.recipe

import com.google.gson.JsonObject
import com.gregtechceu.gtceu.api.machine.MetaMachine
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic
import com.gregtechceu.gtceu.api.recipe.GTRecipe
import com.gregtechceu.gtceu.api.recipe.RecipeCondition
import dev.arbor.gtnn.api.machine.multiblock.NeutronActivatorMachine.Companion.checkNeutronActivatorCondition
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.util.GsonHelper

class NeutronActivatorCondition(max: Int, min: Int) : RecipeCondition() {
    companion object {
        val INSTANCE: NeutronActivatorCondition = NeutronActivatorCondition()
    }
    var evRange: Int = 0

    constructor() : this(0, 0)

    init {
        this.evRange = max * 10000 + min
    }

    override fun getType(): String {
        return "neutron_activator_condition"
    }

    override fun getTooltips(): Component {
        val max = evRange % 10000
        val min = evRange / 10000
        return Component.translatable("gtnn.recipe.condition.neutron_activator_condition_tooltip", max, min)
    }

    override fun test(gtRecipe: GTRecipe, recipeLogic: RecipeLogic): Boolean {
        return checkNeutronActivatorCondition((recipeLogic.machine as MetaMachine), gtRecipe)
    }

    override fun createTemplate(): RecipeCondition {
        return NeutronActivatorCondition()
    }

    override fun serialize(): JsonObject {
        val value = super.serialize()
        value.addProperty("evRange", this.evRange)
        return value
    }

    override fun deserialize(config: JsonObject): RecipeCondition {
        super.deserialize(config)
        this.evRange = GsonHelper.getAsInt(config, "evRange", 0)
        return this
    }

    override fun toNetwork(buf: FriendlyByteBuf) {
        super.toNetwork(buf)
        buf.writeInt(this.evRange)
    }

    override fun fromNetwork(buf: FriendlyByteBuf): RecipeCondition {
        super.fromNetwork(buf)
        this.evRange = buf.readInt()
        return this
    }
}
