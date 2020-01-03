package com.javisel.enchantedwolves.common.registration;

import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.capabilities.WolfCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistration {


    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {

        if (event.getObject() instanceof WolfEntity) {
            event.addCapability(new ResourceLocation(EnchantedWolves.MODID, "wolfdata"), new WolfCapabilityProvider());
        }


    }


}