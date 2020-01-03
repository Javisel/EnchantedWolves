package com.javisel.enchantedwolves.common.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WolfCapabilityProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IWolfCapabilities.class)
    public static Capability<IWolfCapabilities> WOLF_DATA = null;

    private LazyOptional<IWolfCapabilities> instance = LazyOptional.of(WOLF_DATA::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == WOLF_DATA ? instance.cast() : LazyOptional.empty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return (LazyOptional<T>) instance;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) WOLF_DATA.getStorage().writeNBT(WOLF_DATA, this.instance.orElseThrow(NullPointerException::new), null);

    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        WOLF_DATA.getStorage().readNBT(WOLF_DATA, this.instance.orElseThrow(NullPointerException::new), null, nbt);

    }
}

