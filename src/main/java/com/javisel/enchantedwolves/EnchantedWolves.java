package com.javisel.enchantedwolves;

import com.javisel.enchantedwolves.common.item.WolfCollar;
import com.javisel.enchantedwolves.proxy.CommonProxy;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(modid = EnchantedWolves.MODID, name = EnchantedWolves.NAME, version = EnchantedWolves.VERSION )
public class EnchantedWolves {
    public static final UUID MOVEMENT_SPEED_MODDIFIER = UUID.fromString("09432089-1a0d-49d2-be39-39a552153dd5");
    public static final UUID ARMOR_MODIFIER = UUID.fromString("cd2836f6-ec49-49be-96fe-a043fab5be84");
    public static final UUID ARMOR_TOUGHNESS_MODIFIER = UUID.fromString("339acf3d-7d17-42d5-9aa8-972e09fe6590");

    public static final String MODID = "enchantedwolves";
    public static final String NAME = "Enchanted Wolves";
    public static final String VERSION = "1.0";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static EnchantedWolvesItemGroup enchantedWolvesItemGroup = new EnchantedWolvesItemGroup();
    public static EnumEnchantmentType COLLAR = EnumHelper.addEnchantmentType("collar", (item) -> (item instanceof WolfCollar));

    @SidedProxy(clientSide = "com.javisel.enchantedwolves.proxy.ClientProxy", serverSide = "com.javisel.enchantedwolves.proxy.ServerProxy")

    public static CommonProxy proxy;
    private static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);

        logger = event.getModLog();


    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

        ConfigManager.sync(MODID, Config.Type.INSTANCE);




    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

        proxy.postInit(event);

    }
    public static void removeEnchantment(Enchantment enchantment, ItemStack stack) {

        //TODO Enchantment Removal


    }

    public static void decreaseEnchantmentLevel(Enchantment enchantment, ItemStack stack, int decrement) {


        //TODO Enchantment Reduction


    }





}
