package dev.arbor.gtnn.data;

import com.tterrag.registrate.providers.ProviderType;
import dev.arbor.gtnn.api.registry.CNLangProvider;
import dev.arbor.gtnn.data.lang.LangHandler;

import static dev.arbor.gtnn.GTNNRegistries.getREGISTRATE;

public class GTNNDataGen {
    public static final ProviderType<CNLangProvider> CN_LANG = ProviderType.register("cn_lang", (p, e) -> new CNLangProvider(p, e.getGenerator().getPackOutput()));
    public static void init() {
        getREGISTRATE().addDataGenerator(ProviderType.LANG, LangHandler::enInitialize);
        getREGISTRATE().addDataGenerator(CN_LANG, LangHandler::cnInitialize);
        getREGISTRATE().addDataGenerator(ProviderType.BLOCK_TAGS, GTNNTags::initBlock);
    }

}
