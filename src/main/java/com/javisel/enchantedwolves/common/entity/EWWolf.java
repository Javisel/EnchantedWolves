package com.javisel.enchantedwolves.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EWWolf extends WolfEntity {
    public EWWolf(EntityType<? extends WolfEntity> p_i50240_1_, World p_i50240_2_) {
        super(p_i50240_1_, p_i50240_2_);
    }

    @Override
    public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {
        super.setItemStackToSlot(slotIn, stack);
        if (this.getHealth()>this.getMaxHealth()) {
            this.setHealth(this.getMaxHealth());
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */



    @Override
    public void livingTick() {
        super.livingTick();

       // this.getArmorInventoryList().forEach(e -> e.inventoryTick(world, this, 0, false));

    }
}
