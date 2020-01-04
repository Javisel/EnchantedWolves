package com.javisel.enchantedwolves.common.item;

import com.google.common.collect.Multimap;
import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.registration.EnchantmentRegistration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;

import static com.javisel.enchantedwolves.EnchantedWolves.*;

public class WolfCollar extends TieredItem {


    private String name;

    public WolfCollar(String name,ItemTier tier) {
        super(tier, new Properties().group(enchantedWolvesItemGroup));
        setRegistryName(EnchantedWolves.MODID, name);
        this.name = name;

    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return true;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    /**
     * Determines if the specific ItemStack can be placed in the specified armor
     * slot, for the entity.
     *
     * @param stack     The ItemStack
     * @param armorType Armor slot to be verified.
     * @param entity    The entity trying to equip the armor
     * @return True if the given ItemStack can be inserted in the slot
     */
    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
        return entity instanceof WolfEntity;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {


        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(EquipmentSlotType.HEAD,stack);



        if (slot == EquipmentSlotType.HEAD) {

            if (stack.isEnchanted()) {

                if (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.TRAUMA, stack) > 0) {
                    multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Collar modifier", EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.TRAUMA, stack) * .5, AttributeModifier.Operation.ADDITION).setSaved(true));
                }
                if (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.SWIFTNESS, stack) > 0) {

                    multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(EnchantedWolves.MOVEMENT_SPEED_MODDIFIER, "Collar modifier", EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.SWIFTNESS, stack) * .1, AttributeModifier.Operation.MULTIPLY_BASE).setSaved(true));
                }
                if (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.BUFFNESS, stack) > 0) {

                    multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIER, "Collar modifier", EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.BUFFNESS, stack) * 4, AttributeModifier.Operation.ADDITION).setSaved(true));
                }
                if (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.TOUGHNESS, stack) > 0) {

                    multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(ARMOR_TOUGHNESS_MODIFIER, "Collar modifier", EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.TOUGHNESS, stack) * 2, AttributeModifier.Operation.ADDITION).setSaved(true));

                }

            }
        }

        return multimap;


    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {

        if (entity.getType() == EntityType.WOLF && !entity.getEntityWorld().isRemote) {
            WolfEntity pupper = (WolfEntity) entity;

            if (pupper.isTamed() && pupper.getOwner() == player) {

                if (pupper.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty()) {

                    if (stack.hasDisplayName()) {

                        pupper.setCustomName(stack.getDisplayName());
                    }

                    pupper.setItemStackToSlot(EquipmentSlotType.HEAD, stack);
                    if (!player.isCreative()) {
                        player.setHeldItem(hand, ItemStack.EMPTY);
                    }



                }


            }


            return true;
        } else {
            return false;
        }
    }

    public ResourceLocation collarLocation() {
        return new ResourceLocation(EnchantedWolves.MODID, "textures/entity/collar/" + name + ".png");


    }


}
