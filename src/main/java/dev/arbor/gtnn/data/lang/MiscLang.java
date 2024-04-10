package dev.arbor.gtnn.data.lang;

public class MiscLang {
    public static void init() {
        blocks();
        items();
        config();
        LangHandler.tsl("gtnn.recipe.eu", "耗能功率：%,d EU/t", "Usage: %,d EU/t");
        LangHandler.tsl("gtnn.emi.tooltip.1", "最低：%s", "Min: %s");
        LangHandler.tsl("gtnn.emi.tooltip.2", "左键以增加超频等级", "Left click to raise the OC");
        LangHandler.tsl("gtnn.emi.tooltip.3", "右键以降低超频等级", "Right click to lower the OC");
        LangHandler.tsl("gtnn.emi.tooltip.4", "中键以重置超频等级", "Middle click to reset the OC");
    }

    private static void config() {
        LangHandler.tsl("config.gtnn.option.Client", "客户端", "Client");
        LangHandler.tsl("config.gtnn.option.Server", "服务器", "Server");
        LangHandler.tsl("config.gtnn.option.enableHarderNaquadahLine", "开启更难的硅岩处理", "Enable Harder Naquadah Process Line");
        LangHandler.tsl("config.gtnn.option.enableHarderPlatinumLine", "开启更难的铂处理", "Enable Harder Platinum Process Line");
        LangHandler.tsl("config.gtnn.option.extraHeartRenderer", "启用血条渲染", "Enable extra Heart renderer");
        LangHandler.tsl("config.gtnn.option.banCreateFanBlasting", "禁用机械动力风扇熔炼", "Disable Create Fan Blasting");
        LangHandler.tsl("config.gtnn.option.makesEMIBetter", "使EMI更好用", "Makes EMI Better");
        LangHandler.tsl("config.gtnn.option.killToast", "禁用弹窗", "Disable toast");
        LangHandler.tsl("config.gtnn.option.addChatAnimation", "启用聊天动画", "Enable Chat Animation");
        LangHandler.tsl("config.gtnn.option.enableRemakeGTMEMI", "启用重制GTM-EMI支持", "Enable Remake GTM-EMI support");
    }

    private static void blocks() {
        LangHandler.tsl("block.gtnn.clean_machine_casing", "洁净机械方块");
        LangHandler.tsl("block.gtnn.mar_casing", "立场约束机械方块");
        LangHandler.tsl("block.gtnn.radiation_proof_machine_casing", "防辐射机械方块");
        LangHandler.tsl("block.gtnn.high_speed_pipe_block", "高速管道方块");
    }

    private static void items() {
        LangHandler.tsl("item.gtnn.heavy_ingot_t1.tooltip", "§7用于制作T1重型合金板", "§7Used for making Heavy Alloy Plate T1");
        LangHandler.tsl("item.gtnn.heavy_ingot_t2.tooltip", "§7用于制作T2重型合金板", "§7Used for making Heavy Alloy Plate T2");
        LangHandler.tsl("item.gtnn.heavy_ingot_t3.tooltip", "§7用于制作T3重型合金板", "§7Used for making Heavy Alloy Plate T3");
        LangHandler.tsl("item.gtnn.heavy_ingot_t4.tooltip", "§7用于制作T4重型合金板", "§7Used for making Heavy Alloy Plate T4");
        LangHandler.tsl("item.gtnn.heavy_plate_t1.tooltip", "§71阶", "§7T1");
        LangHandler.tsl("item.gtnn.heavy_plate_t2.tooltip", "§72阶", "§7T2");
        LangHandler.tsl("item.gtnn.heavy_plate_t3.tooltip", "§73阶", "§7T3");
        LangHandler.tsl("item.gtnn.heavy_plate_t4.tooltip", "§74阶", "§7T4");
        LangHandler.tsl("item.gtnn.chip_t1.tooltip", "§7§o用于制作1阶火箭", "§7Used for making Rocket T1");
        LangHandler.tsl("item.gtnn.chip_t2.tooltip", "§7§o用于制作2阶火箭", "§7Used for making Rocket T2");
        LangHandler.tsl("item.gtnn.chip_t3.tooltip", "§7§o用于制作3阶火箭", "§7Used for making Rocket T3");
        LangHandler.tsl("item.gtnn.chip_t4.tooltip", "§7§o用于制作4阶火箭", "§7Used for making Rocket T4");
        LangHandler.tsl("item.gtnn.encapsulated_plutonium", "封装钚");
        LangHandler.tsl("item.gtnn.encapsulated_thorium", "封装钍");
        LangHandler.tsl("item.gtnn.encapsulated_uranium", "封装铀");
        LangHandler.tsl("item.gtnn.enriched_plutonium", "浓缩钚");
        LangHandler.tsl("item.gtnn.enriched_plutonium_nugget", "浓缩钚粒");
        LangHandler.tsl("item.gtnn.enriched_thorium", "浓缩钍");
        LangHandler.tsl("item.gtnn.enriched_thorium_nugget", "浓缩钍粒");
        LangHandler.tsl("item.gtnn.enriched_uranium", "浓缩铀");
        LangHandler.tsl("item.gtnn.enriched_uranium_nugget", "浓缩铀粒");
        LangHandler.tsl("item.gtnn.heavy_ingot_t1", "T1重型锭");
        LangHandler.tsl("item.gtnn.heavy_ingot_t2", "T2重型锭");
        LangHandler.tsl("item.gtnn.heavy_ingot_t3", "T3重型锭");
        LangHandler.tsl("item.gtnn.heavy_ingot_t4", "T4重型锭");
        LangHandler.tsl("item.gtnn.heavy_plate_t1", "T1重型合金板");
        LangHandler.tsl("item.gtnn.heavy_plate_t2", "T2重型合金板");
        LangHandler.tsl("item.gtnn.heavy_plate_t3", "T3重型合金板");
        LangHandler.tsl("item.gtnn.heavy_plate_t4", "T4重型合金板");
        LangHandler.tsl("item.gtnn.inverter", "逆变器");
        LangHandler.tsl("item.gtnn.neutron_source", "中子源");
        LangHandler.tsl("item.gtnn.plate_radiation_protection", "防辐射板");
        LangHandler.tsl("item.gtnn.quark_core", "夸克核心");
        LangHandler.tsl("item.gtnn.radioactive_waste", "放射性废料");
        LangHandler.tsl("item.gtnn.t1_chip", "T1芯片");
        LangHandler.tsl("item.gtnn.t2_chip", "T2芯片");
        LangHandler.tsl("item.gtnn.t3_chip", "T3芯片");
        LangHandler.tsl("item.gtnn.t4_chip", "T4芯片");
        LangHandler.tsl("item.gtnn.computer_circuit", "计算机芯片");
        LangHandler.tsl("item.gtnn.computer_advanced_circuit", "高级计算机芯片");
    }
}
