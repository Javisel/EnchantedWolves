package com.javisel.enchantedwolves.common.registration;

import com.javisel.enchantedwolves.Config;
import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.enchantments.EnchantmentBase;
import com.javisel.enchantedwolves.common.enchantments.Resurrection;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(EnchantedWolves.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EnchantmentRegistration {

    public static EnchantmentBase BUFFNESS = null;
    public static EnchantmentBase CUSHION = null;
    public static EnchantmentBase FROSTBITE = null;
    public static EnchantmentBase HELLHOUND = null;
    public static EnchantmentBase LEECH_FANGS = null;
    public static EnchantmentBase RESURRECTION = null;
    public static EnchantmentBase SWIFTNESS = null;
    public static EnchantmentBase TOUGHNESS = null;
    public static EnchantmentBase TRAUMA = null;

    @SubscribeEvent
    public static void registerEnchantments(final RegistryEvent.Register<Enchantment> event) {

        event.getRegistry().registerAll(
                BUFFNESS = new EnchantmentBase("buffness", Enchantment.Rarity.COMMON, EnchantedWolves.COLLAR, new EquipmentSlotType[]{EquipmentSlotType.HEAD}, Config.buffnessConfig.maxEnchantLevel.get()),

                TRAUMA = new EnchantmentBase("trauma", Enchantment.Rarity.COMMON, EnchantedWolves.COLLAR, new EquipmentSlotType[]{EquipmentSlotType.HEAD}, Config.traumaConfig.maxEnchantLevel.get()),
                SWIFTNESS = new EnchantmentBase("swiftness", Enchantment.Rarity.UNCOMMON, EnchantedWolves.COLLAR, new EquipmentSlotType[]{EquipmentSlotType.HEAD}, Config.swiftnessConfig.maxEnchantLevel.get()),
                LEECH_FANGS = new EnchantmentBase("leech_fangs", Enchantment.Rarity.RARE, EnchantedWolves.COLLAR, new EquipmentSlotType[]{EquipmentSlotType.HEAD}, Config.leechFangsConfig.maxEnchantLevel.get()),
                FROSTBITE = new EnchantmentBase("frostbite", Enchantment.Rarity.UNCOMMON, EnchantedWolves.COLLAR, new EquipmentSlotType[]{EquipmentSlotType.HEAD}, Config.frostbiteConfig.maxEnchantLevel.get()),
                HELLHOUND = new EnchantmentBase("hellhound", Enchantment.Rarity.UNCOMMON, EnchantedWolves.COLLAR, new EquipmentSlotType[]{EquipmentSlotType.HEAD}, Config.hellhoundConfig.maxEnchantLevel.get()),
                TOUGHNESS = new EnchantmentBase("toughness", Enchantment.Rarity.UNCOMMON, EnchantedWolves.COLLAR, new EquipmentSlotType[]{EquipmentSlotType.HEAD}, Config.toughnessConfig.maxEnchantLevel.get()),
                CUSHION = new EnchantmentBase("cushion", Enchantment.Rarity.UNCOMMON, EnchantedWolves.COLLAR, new EquipmentSlotType[]{EquipmentSlotType.HEAD}, Config.cushionConfig.maxEnchantLevel.get()),
                RESURRECTION = new Resurrection()

        );


    }


}
