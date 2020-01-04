package com.javisel.enchantedwolves;

import com.javisel.enchantedwolves.common.registration.ItemRegistration;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class EnchantedWolvesItemGroup extends ItemGroup {
    public EnchantedWolvesItemGroup() {
        super(EnchantedWolves.MODID);
    }


    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemRegistration.diamond_collar);
    }


}
