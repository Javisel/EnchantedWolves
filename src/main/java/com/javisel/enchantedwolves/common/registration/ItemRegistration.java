package com.javisel.enchantedwolves.common.registration;


import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.item.WolfCollar;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(EnchantedWolves.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistration {

    public static WolfCollar leather_collar = null;
    public static WolfCollar chain_collar = null;
    public static WolfCollar golden_collar = null;
    public static WolfCollar diamond_collar = null;

    @SubscribeEvent
    public static void registerItem(final RegistryEvent.Register<Item> event) {

        event.getRegistry().registerAll(
                leather_collar = new WolfCollar("leather_collar", ItemTier.WOOD),
                chain_collar = new WolfCollar("chain_collar", ItemTier.IRON),
                golden_collar = new WolfCollar("golden_collar", ItemTier.GOLD),
                diamond_collar = new WolfCollar("diamond_collar", ItemTier.DIAMOND)
        );


    }


}