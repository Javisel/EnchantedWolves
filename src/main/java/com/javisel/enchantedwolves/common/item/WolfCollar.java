package com.javisel.enchantedwolves.common.item;

import com.google.common.collect.Multimap;
import com.javisel.enchantedwolves.Config;
import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.registration.EnchantmentRegistration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerEvent;


import static com.javisel.enchantedwolves.EnchantedWolves.*;

public class WolfCollar extends Item {


    private String name;

    public WolfCollar(String name, ToolMaterial material) {
        setRegistryName(EnchantedWolves.MODID, name);
        this.name = name;
        setUnlocalizedName(name);
        setMaxDamage(material.getMaxUses());
        setMaxStackSize(1);
        setCreativeTab(enchantedWolvesItemGroup);
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return true;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
        return entity instanceof EntityWolf;
    }


    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {


        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(EntityEquipmentSlot.HEAD,stack);



        if (slot == EntityEquipmentSlot.HEAD) {

            if (stack.isItemEnchanted()) {

                if (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.TRAUMA, stack) > 0) {
                    multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Collar modifier", EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.TRAUMA, stack) * .5, 0).setSaved(true));
                }
                if (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.SWIFTNESS, stack) > 0) {

                    multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(EnchantedWolves.MOVEMENT_SPEED_MODDIFIER, "Collar modifier", EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.SWIFTNESS, stack) * .1, 1).setSaved(true));
                }
                if (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.BUFFNESS, stack) > 0) {

                    multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIER, "Collar modifier", EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.BUFFNESS, stack) * 4, 0).setSaved(true));
                }
                if (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.TOUGHNESS, stack) > 0) {

                    multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(ARMOR_TOUGHNESS_MODIFIER, "Collar modifier", EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.TOUGHNESS, stack) * 2, 0).setSaved(true));

                }

            }
        }

        return multimap;


    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     *
     * @param stack
     * @param playerIn
     * @param target
     * @param hand
     */


    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {

        if (target instanceof EntityWolf && !target.getEntityWorld().isRemote) {
            EntityWolf pupper = (EntityWolf) target;

            if (pupper.isTamed() && pupper.getOwner() == playerIn) {

                if (pupper.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {

                    if (stack.hasDisplayName()) {

                        pupper.setCustomNameTag(stack.getDisplayName());
                    }

                    pupper.setItemStackToSlot(EntityEquipmentSlot.HEAD, stack);
                    if (!playerIn.isCreative()) {
                        playerIn.setHeldItem(hand, ItemStack.EMPTY);
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
