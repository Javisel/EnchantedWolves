package com.javisel.enchantedwolves.client;

import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.item.WolfCollar;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EWCollarLayer extends LayerRenderer<WolfEntity, WolfModel<WolfEntity>> {

    private ResourceLocation ENCHANTED = new ResourceLocation(EnchantedWolves.MODID, "textures/entity/collar/enchanted_collar.png");


    public EWCollarLayer(IEntityRenderer<WolfEntity, WolfModel<WolfEntity>> p_i50926_1_) {
        super(p_i50926_1_);
    }

    @Override
    public void func_225628_a_(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, WolfEntity pupper, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {

        if (pupper.isTamed() && !pupper.isInvisible()) {

            if (pupper.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof WolfCollar) {
                WolfCollar collar = (WolfCollar) pupper.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();

                func_229141_a_(this.getEntityModel(), collar.collarLocation(), p_225628_1_, p_225628_2_, p_225628_3_, pupper, 1, 1, 1);
                if (pupper.getItemStackFromSlot(EquipmentSlotType.HEAD).hasEffect()) {


                    func_229141_a_(this.getEntityModel(), ENCHANTED, p_225628_1_, p_225628_2_, p_225628_3_, pupper, 1, 1, 1);

                }


            }
        }
    }
}