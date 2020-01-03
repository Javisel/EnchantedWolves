package com.javisel.enchantedwolves.common.capabilities;

import net.minecraft.nbt.CompoundNBT;

public interface IWolfCapabilities {

    public float getMaxHealth();
    public float getCurrentHealth();
    public void setCurrentHealth(float amount);
    public void setMaxHealth(float amount);
    public CompoundNBT saveNBTData();
    void setNBTData(CompoundNBT nbtData);


}
