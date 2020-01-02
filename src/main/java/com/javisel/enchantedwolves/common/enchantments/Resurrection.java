package com.javisel.enchantedwolves.common.enchantments;

import com.javisel.enchantedwolves.Config;
import com.javisel.enchantedwolves.EnchantedWolves;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class Resurrection extends EnchantmentBase {

    public Resurrection() {
        super("resurrection", Rarity.RARE, EnchantedWolves.COLLAR, new EquipmentSlotType[]{EquipmentSlotType.HEAD}, Config.resurrectionConfig.maxEnchantLevel.get());
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }

    public int getMinEnchantability(int p_77321_1_) {
        return p_77321_1_ * 25;
    }

    public int getMaxEnchantability(int p_223551_1_) {
        return this.getMinEnchantability(p_223551_1_) + 50;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack p_canApplyAtEnchantingTable_1_) {
        return false;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}
