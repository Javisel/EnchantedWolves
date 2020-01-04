package com.javisel.enchantedwolves.proxy;

import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.client.EWCollarLayer;
import com.javisel.enchantedwolves.common.registration.ItemRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {


    @SubscribeEvent
    public static void RegisterItemModels(final ModelRegistryEvent event) {

        registerItemRenderer(ItemRegistration.chain_collar, 0);
        registerItemRenderer(ItemRegistration.diamond_collar, 0);
        registerItemRenderer(ItemRegistration.golden_collar, 0);
        registerItemRenderer(ItemRegistration.leather_collar, 0);



    }


    private static void registerItemRenderer(Item item, int meta) {


        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(EnchantedWolves.MODID + ":" + item.getRegistryName().getResourcePath(), "inventory"));

    }

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        RenderWolf renderWolf = (RenderWolf) renderManager.entityRenderMap.get(EntityWolf.class);

        EWCollarLayer collarLayer =  new EWCollarLayer(renderWolf);

        renderWolf.addLayer(collarLayer);

    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}

