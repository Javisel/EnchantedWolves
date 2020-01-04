package com.javisel.enchantedwolves.common.registration;

import com.javisel.enchantedwolves.Config;
import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.enchantments.EnchantmentBase;
import com.javisel.enchantedwolves.common.enchantments.Resurrection;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


@GameRegistry.ObjectHolder(EnchantedWolves.MODID)
@Mod.EventBusSubscriber(modid = EnchantedWolves.MODID)

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
                BUFFNESS = new EnchantmentBase("buffness", Enchantment.Rarity.COMMON, EnchantedWolves.COLLAR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD}, Config.buffnessConfig.maximumEnchantmentLevel),

                TRAUMA = new EnchantmentBase("trauma", Enchantment.Rarity.COMMON, EnchantedWolves.COLLAR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD}, Config.traumaConfig.maximumEnchantmentLevel),
                SWIFTNESS = new EnchantmentBase("swiftness", Enchantment.Rarity.UNCOMMON, EnchantedWolves.COLLAR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD}, Config.swiftnessConfig.maximumEnchantmentLevel),
                LEECH_FANGS = new EnchantmentBase("leech_fangs", Enchantment.Rarity.RARE, EnchantedWolves.COLLAR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD}, Config.leechFangsConfig.maximumEnchantmentLevel),
                FROSTBITE = new EnchantmentBase("frostbite", Enchantment.Rarity.UNCOMMON, EnchantedWolves.COLLAR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD}, Config.frostbiteConfig.maximumEnchantmentLevel),
                HELLHOUND = new EnchantmentBase("hellhound", Enchantment.Rarity.UNCOMMON, EnchantedWolves.COLLAR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD}, Config.hellhoundConfig.maximumEnchantmentLevel),
                TOUGHNESS = new EnchantmentBase("toughness", Enchantment.Rarity.UNCOMMON, EnchantedWolves.COLLAR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD}, Config.toughnessConfig.maximumEnchantmentLevel),
                CUSHION = new EnchantmentBase("cushion", Enchantment.Rarity.UNCOMMON, EnchantedWolves.COLLAR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD}, Config.cushionConfig.maximumEnchantmentLevel),
                RESURRECTION = new Resurrection()

        );


    }


}
