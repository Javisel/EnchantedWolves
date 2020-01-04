package com.javisel.enchantedwolves;

@net.minecraftforge.common.config.Config(modid = EnchantedWolves.MODID,name = "enchanted_wolves_config",type= net.minecraftforge.common.config.Config.Type.INSTANCE)
public class Config {

    public static enchantmentData buffnessConfig = new enchantmentData(5);
    public static enchantmentData cushionConfig = new enchantmentData(4);
    public static enchantmentData frostbiteConfig = new enchantmentData(3);
    public static enchantmentData hellhoundConfig = new enchantmentData(2);
    public static enchantmentData leechFangsConfig = new enchantmentData(3);
    public static enchantmentData resurrectionConfig = new enchantmentData(3);
    public static enchantmentData swiftnessConfig = new enchantmentData(3);
    public static enchantmentData toughnessConfig = new enchantmentData(5);
    public static enchantmentData traumaConfig = new enchantmentData(5);




    public static class enchantmentData {

        @net.minecraftforge.common.config.Config.RangeInt(min = 1, max = 100)
        public int maximumEnchantmentLevel;

        public enchantmentData(int d) {
            maximumEnchantmentLevel=d;
        }


    }


}
