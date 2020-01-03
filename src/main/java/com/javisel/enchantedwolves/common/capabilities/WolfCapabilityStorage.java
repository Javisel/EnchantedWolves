package com.javisel.enchantedwolves.common.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class WolfCapabilityStorage implements Capability.IStorage<IWolfCapabilities> {


    @Nullable
    @Override
    public INBT writeNBT(Capability<IWolfCapabilities> capability, IWolfCapabilities instance, Direction side) {
        return instance.saveNBTData();
    }

    @Override
    public void readNBT(Capability<IWolfCapabilities> capability, IWolfCapabilities instance, Direction side, INBT nbt) {

        instance.setNBTData((CompoundNBT) nbt);
    }
}
