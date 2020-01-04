package com.javisel.enchantedwolves;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

    public static enchantmentData buffnessConfig = new enchantmentData();
    public static enchantmentData cushionConfig = new enchantmentData();
    public static enchantmentData frostbiteConfig = new enchantmentData();
    public static hellhoundData hellhoundConfig = new hellhoundData();
    public static enchantmentData leechFangsConfig = new enchantmentData();
    public static enchantmentData resurrectionConfig = new enchantmentData();
    public static enchantmentData swiftnessConfig = new enchantmentData();
    public static enchantmentData toughnessConfig = new enchantmentData();
    public static enchantmentData traumaConfig = new enchantmentData();


    public static void init(ForgeConfigSpec.Builder server) {

        server.push("Enchantment Configurations");
        server.push("Buffness");
        buffnessConfig.maxEnchantLevel = server
                .comment("The Max Enchant Level for Buffness.")
                .defineInRange("max_buffness_enchantment_level", 5, 1, 10);

        server.pop();
        server.push("Cushion");

        cushionConfig.maxEnchantLevel = server.comment("The Max Enchant Level for Cushion").defineInRange("max_cushion_enchant_level", 4, 1, 100);
        server.pop();

        server.push("Frostbite");
        frostbiteConfig.maxEnchantLevel = server.comment("The Max Enchant Level for Frostbite").defineInRange("max_frostbite_enchant_level", 3, 1, 100);
        server.pop();

        server.push("Hellhound");
        hellhoundConfig.maxEnchantLevel = server.comment("The Max Enchant Level for Hellhound").defineInRange("max_hellhound_enchant_level", 2, 1, 100);
        server.pop();
        server.push("Leech Fangs");
        leechFangsConfig.maxEnchantLevel = server.comment("The Max Enchant Level for Leech Fangs").defineInRange("max_leechfang_enchant_level", 3, 1, 100);
        server.pop();

        server.push("Resurrection");
        resurrectionConfig.maxEnchantLevel = server.comment("The Max Enchant Level for Resurrection").defineInRange("max_resurrection_enchant_level", 3, 1, 100);
        server.pop();

        server.push("Swiftness");
        swiftnessConfig.maxEnchantLevel = server.comment("The Max Enchant Level for Swiftness").defineInRange("max_swiftness_enchant_level", 3, 1, 100);
        server.pop();

        server.push("Toughness");
        toughnessConfig.maxEnchantLevel = server.comment("The Max Enchant Level for Toughness").defineInRange("max_toughness_enchant_level", 5, 1, 100);
        server.pop();


        server.push("Trauma");
        traumaConfig.maxEnchantLevel = server
                .comment("The Max Enchant Level for Trauma.")
                .defineInRange("max_trauma_enchantment_level", 5, 1, 10);

        server.pop(2);


    }

    public static class enchantmentData {

        public ForgeConfigSpec.IntValue maxEnchantLevel;


    }

    public static class hellhoundData extends enchantmentData {



    }


}