package com.javisel.enchantedwolves;

import com.javisel.enchantedwolves.common.capabilities.IWolfCapabilities;
import com.javisel.enchantedwolves.common.capabilities.WolfCapabilityProvider;
import com.javisel.enchantedwolves.common.item.WolfCollar;
import com.javisel.enchantedwolves.common.registration.EnchantmentRegistration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.javisel.enchantedwolves.EnchantedWolves.decreaseEnchantmentLevel;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class EventHandler {








    @SubscribeEvent
    public void resurrection(LivingHurtEvent e) {

        if (e.getEntity() instanceof LivingEntity) {

            if (e.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof WolfCollar && EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.RESURRECTION, e.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD)) > 0) {

                if (e.getEntityLiving().getHealth() - e.getAmount() <= 0) {
                    decreaseEnchantmentLevel(EnchantmentRegistration.RESURRECTION, e.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD), 1);

                    e.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD).damageItem(1, e.getEntityLiving(), (p_213341_0_) -> {
                        p_213341_0_.sendBreakAnimation(EquipmentSlotType.HEAD);
                    });
                    e.setCanceled(true);

                    e.getEntityLiving().heal( (e.getEntityLiving().getMaxHealth()));

                    if (e.getEntityLiving().getType() == EntityType.WOLF) {
                        WolfEntity pupper = (WolfEntity) e.getEntityLiving();

                        if (pupper.isTamed()) {
                            pupper.world.playMovingSound(null, pupper, SoundEvents.ENTITY_WOLF_HOWL, SoundCategory.NEUTRAL, 5, 5);
                            pupper.world.playMovingSound(null, pupper, SoundEvents.ITEM_TOTEM_USE, SoundCategory.NEUTRAL, 5, 5);
                            pupper.setPositionAndUpdate(pupper.getOwner().getPosition().getX(), pupper.getOwner().getPosition().getY(), pupper.getOwner().getPosition().getZ());

                        }


                    }

                }


            }


        }


    }


    @SubscribeEvent
    public void doggyDamage(LivingDamageEvent e) {


        if (e.getEntityLiving().getType() == EntityType.WOLF) {

            WolfEntity pupper = (WolfEntity) e.getEntityLiving();


            if (pupper.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof WolfCollar) {
                ItemStack collar = pupper.getItemStackFromSlot(EquipmentSlotType.HEAD);


                float reduction = 0F;


                if (e.getSource() == DamageSource.FALL) {

                    reduction += (float) (e.getAmount() * (0.2 * EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.CUSHION, collar)));


                }

                if (e.getSource().isFireDamage()) {

                    reduction += (float) (e.getAmount() * (0.4 * EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.HELLHOUND, collar)));


                }
                float total = e.getAmount() - reduction;
                if (total < 0) total = 0;
                e.setAmount(total);


            }


        }


    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void leftClickCollarOff(LivingAttackEvent e) {



        if (e.getEntityLiving().getType() == EntityType.WOLF && !e.getEntityLiving().getEntityWorld().isRemote) {


            WolfEntity pupper = (WolfEntity) e.getEntityLiving();



            if (pupper.isTamed() && pupper.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof WolfCollar && e.getSource().getTrueSource() instanceof PlayerEntity) {

                e.setCanceled(true);
                PlayerEntity owner = (PlayerEntity) e.getSource().getTrueSource();


                if (pupper.getOwner() == owner) {
                    if (owner.isCrouching() && owner.getHeldItemMainhand().isEmpty()) {
                        owner.setHeldItem(Hand.MAIN_HAND, pupper.getItemStackFromSlot(EquipmentSlotType.HEAD));
                        pupper.setItemStackToSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                    }


                }

            }


        }


    }



    @SubscribeEvent
    public void magicCollars(LivingDamageEvent e) {

        if (e.getSource().getTrueSource() instanceof WolfEntity) {

            WolfEntity pupper = (WolfEntity) e.getSource().getTrueSource();

            if (pupper.isTamed()) {
                ItemStack collar = pupper.getItemStackFromSlot(EquipmentSlotType.HEAD);
                collar.damageItem(1, pupper, (p_213341_0_) -> {
                    p_213341_0_.sendBreakAnimation(EquipmentSlotType.HEAD);
                });

                if (collar.getItem() instanceof WolfCollar) {

                    e.getEntityLiving().setFire((int) (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.HELLHOUND, collar) *40));

                    if (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.LEECH_FANGS, collar) > 0) {
                        pupper.heal((float) (e.getAmount() * (0.25 * EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.LEECH_FANGS, collar))));



                    }


                    if (EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.FROSTBITE, collar) > 0) {


                        e.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS,60, -1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.FROSTBITE, collar)));


                    }


                }


            }

        }

    }


}
