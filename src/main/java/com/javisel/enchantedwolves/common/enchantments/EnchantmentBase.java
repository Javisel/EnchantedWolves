package com.javisel.enchantedwolves.common.enchantments;

import com.javisel.enchantedwolves.EnchantedWolves;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentBase extends Enchantment {


    int maxLevel;
    Enchantment[] incompatibles;


    public EnchantmentBase(String name, Rarity rarity, EnchantmentType type, EquipmentSlotType[] equipmentSlotTypes, int maxLevelIn) {
        super(rarity, type, equipmentSlotTypes);
        setRegistryName(EnchantedWolves.MODID, name);
        maxLevel = maxLevelIn;

    }

    public EnchantmentBase(String name, Rarity p_i46731_1_, EnchantmentType p_i46731_2_, EquipmentSlotType[] p_i46731_3_, int maxLevelIn, Enchantment... incompatible) {
        super(p_i46731_1_, p_i46731_2_, p_i46731_3_);
        setRegistryName(EnchantedWolves.MODID, name);
        maxLevel = maxLevelIn;
        incompatibles = incompatible;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * Is this enchantment allowed to be enchanted on books via Enchantment Table
     *
     * @return false to disable the vanilla feature
     */
    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    protected boolean canApplyTogether(Enchantment p_77326_1_) {


        if (incompatibles != null) {
            for (Enchantment enchantment : incompatibles) {

                if (p_77326_1_ == enchantment) {
                    return false;
                }

            }

        }


        return super.canApplyTogether(p_77326_1_);
    }
}
