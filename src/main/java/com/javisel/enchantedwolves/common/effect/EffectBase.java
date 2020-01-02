package com.javisel.enchantedwolves.common.effect;

import com.javisel.enchantedwolves.EnchantedWolves;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class EffectBase extends Effect {


    protected EffectBase(String name, EffectType p_i50391_1_, int colour) {
        super(p_i50391_1_, colour);
        setRegistryName(EnchantedWolves.MODID, name);
    }
}
