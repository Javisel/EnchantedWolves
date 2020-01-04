package com.javisel.enchantedwolves.common.enchantments;

import com.javisel.enchantedwolves.Config;
import com.javisel.enchantedwolves.EnchantedWolves;
import net.minecraft.inventory.EquipmentSlotType;

public class Resurrection extends EnchantmentBase {

    public Resurrection() {
        super("resurrection", Rarity.RARE, EnchantedWolves.COLLAR, new EquipmentSlotType[]{EquipmentSlotType.HEAD}, Config.resurrectionConfig.maxEnchantLevel.get());
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }




    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}
