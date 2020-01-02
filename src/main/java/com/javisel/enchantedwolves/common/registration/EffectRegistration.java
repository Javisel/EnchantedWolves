package com.javisel.enchantedwolves.common.registration;

import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.effect.Disarmed;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(EnchantedWolves.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EffectRegistration {

    public static Effect DISARMED = null;

    @SubscribeEvent
    public static void registerEffect(final RegistryEvent.Register<Effect> event) {

        event.getRegistry().registerAll(

                DISARMED = new Disarmed()
        );


    }


}
