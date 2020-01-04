package com.javisel.enchantedwolves;

import com.javisel.enchantedwolves.common.registration.ItemRegistration;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class EnchantedWolvesItemGroup extends CreativeTabs {
    public EnchantedWolvesItemGroup() {
        super(EnchantedWolves.MODID);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(ItemRegistration.diamond_collar);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemRegistration.diamond_collar);
    }
}
