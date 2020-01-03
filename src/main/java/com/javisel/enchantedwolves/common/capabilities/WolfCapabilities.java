package com.javisel.enchantedwolves.common.capabilities;

import net.minecraft.nbt.CompoundNBT;

public class WolfCapabilities implements IWolfCapabilities {

    private float maxHealth = -1;
    private  float currentHealth = -1;
    @Override
    public float getMaxHealth() {
        return maxHealth;
    }

    @Override
    public float getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void setCurrentHealth(float amount) {
        currentHealth=amount;
    }

    @Override
    public void setMaxHealth(float amount) {
        maxHealth=amount;
    }

    @Override
    public CompoundNBT saveNBTData() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putFloat("CURRENTHEALTH",currentHealth);
        compoundNBT.putFloat("MAXHEALTH",maxHealth);

        return null;
    }

    @Override
    public void setNBTData(CompoundNBT nbtData) {
            currentHealth=nbtData.getFloat("CURRENTHEALTH");
            maxHealth=nbtData.getFloat("MAXHEALTH");
    }
}
