package com.javisel.enchantedwolves.client;

import com.javisel.enchantedwolves.EnchantedWolves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ClientEvents {


    @SubscribeEvent
    public void addLayers(ModelRegistryEvent e) {

        EnchantedWolves.LOGGER.log(Level.DEBUG,"Model Registry");
        EntityRendererManager entityRendererManager = Minecraft.getInstance().getRenderManager();
        WolfRenderer wolfRenderer = (WolfRenderer) entityRendererManager.renderers.get(EntityType.WOLF);


        wolfRenderer.addLayer((new EWCollarLayer(wolfRenderer)));


    }




}
