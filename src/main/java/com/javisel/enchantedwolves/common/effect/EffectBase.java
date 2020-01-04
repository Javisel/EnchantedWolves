package com.javisel.enchantedwolves.common.effect;

import com.javisel.enchantedwolves.EnchantedWolves;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;

public class EffectBase extends Effect {


    protected EffectBase(String name, EffectType type, int colour) {
        super(type,colour);
        setRegistryName(EnchantedWolves.MODID, name);
    }
}
