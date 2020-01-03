package com.javisel.enchantedwolves.common.registration;

import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.entity.EWWolf;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(EnchantedWolves.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegistration {

    public static EntityType<?> NEW_WOLF = EntityType.Builder.create(EWWolf::new, EntityClassification.CREATURE).size(0.6F, 0.85F).build("minecraft:wolf").setRegistryName("minecraft:wolf");


    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll
                (
                         //NEW_WOLF
                );


    }


}
