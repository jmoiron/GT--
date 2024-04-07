package org.arbor.gtnn.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import org.arbor.gtnn.emi.recipe.NGTEmiRecipeHandler;

@Deprecated(forRemoval = true, since = "1.1.0")
@EmiEntrypoint
public class GTNNEMIPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        registry.addRecipeHandler(null, new NGTEmiRecipeHandler());
    }
}
