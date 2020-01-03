package com.javisel.enchantedwolves;

import com.javisel.enchantedwolves.client.EWCollarLayer;
import com.javisel.enchantedwolves.common.capabilities.IWolfCapabilities;
import com.javisel.enchantedwolves.common.capabilities.WolfCapabilities;
import com.javisel.enchantedwolves.common.capabilities.WolfCapabilityStorage;
import com.javisel.enchantedwolves.common.item.WolfCollar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("enchantedwolves")
public class EnchantedWolves {
    public static final UUID MAX_HEALTH_MODDIFIER = UUID.fromString("c46e576c-675e-4c02-a7f3-41f4b51681d2");
    public static final UUID MOVEMENT_SPEED_MODDIFIER = UUID.fromString("09432089-1a0d-49d2-be39-39a552153dd5");
    public static final UUID ARMOR_MODIFIER = UUID.fromString("cd2836f6-ec49-49be-96fe-a043fab5be84");
    public static final String THUNDERMAW = "THUNDERMAW";
    public static final String MODID = "enchantedwolves";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static EnchantedWolvesItemGroup enchantedWolvesItemGroup = new EnchantedWolvesItemGroup();
    public static EnchantmentType COLLAR = EnchantmentType.create("collar", (item) -> (item instanceof WolfCollar));

    public EnchantedWolves() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigSetup.SERVER, "enchantedwolves.toml");

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        ConfigSetup.loadConfig(ConfigSetup.SERVER, FMLPaths.CONFIGDIR.get().resolve("enchantedwolves.toml"));

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        });


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void removeEnchantment(Enchantment enchantment, ItemStack stack) {

        if (EnchantmentHelper.getEnchantmentLevel(enchantment, stack) >= 1) {
            ResourceLocation resourcelocation = Registry.ENCHANTMENT.getKey(enchantment);
            CompoundNBT nbt = stack.getTag().copy();
            ListNBT listnbt = nbt.getList("Enchantments", 10);
            ListNBT newenchantments = new ListNBT();
            for (int i = 0; i < listnbt.size(); ++i) {
                CompoundNBT compoundnbt = listnbt.getCompound(i);
                ResourceLocation resourcelocation1 = ResourceLocation.tryCreate(compoundnbt.getString("id"));
                if (resourcelocation1 != null && !resourcelocation1.equals(resourcelocation)) {

                    newenchantments.add(compoundnbt);

                }
            }
            nbt.put("Enchantments", newenchantments);
            stack.setTag(nbt);

        }


    }

    public static void decreaseEnchantmentLevel(Enchantment enchantment, ItemStack stack, int decrement) {


        if (EnchantmentHelper.getEnchantmentLevel(enchantment, stack) >= 1) {
            ResourceLocation resourcelocation = Registry.ENCHANTMENT.getKey(enchantment);
            ListNBT listnbt = stack.getEnchantmentTagList();

            int newlevel = -1;
            boolean remove = false;
            for (int i = 0; i < listnbt.size(); ++i) {
                CompoundNBT compoundnbt = listnbt.getCompound(i);
                ResourceLocation resourcelocation1 = ResourceLocation.tryCreate(compoundnbt.getString("id"));
                if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
                    newlevel = compoundnbt.getInt("lvl") - decrement;
                    if (newlevel >= 1) {
                        compoundnbt.putInt("lvl", newlevel);
                    } else {

                        remove = true;
                    }
                }
            }

            if (remove) {
                removeEnchantment(enchantment, stack);
            }


        }


    }

    private void setup(final FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(IWolfCapabilities.class, new WolfCapabilityStorage(), WolfCapabilities::new);



    }

        @OnlyIn(Dist.CLIENT)
    private void doClientStuff(final FMLClientSetupEvent event) {

        EntityRendererManager entityRendererManager = Minecraft.getInstance().getRenderManager();
        WolfRenderer wolfRenderer = (WolfRenderer) entityRendererManager.renderers.get(EntityType.WOLF);
        wolfRenderer.addLayer((new EWCollarLayer(wolfRenderer)));
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
    }

    private void processIMC(final InterModProcessEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts


    }


}
