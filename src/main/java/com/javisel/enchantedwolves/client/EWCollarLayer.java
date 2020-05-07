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
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, WolfEntity pupper, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (pupper.isTamed() && !pupper.isInvisible()) {

            if (pupper.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof WolfCollar) {
                WolfCollar collar = (WolfCollar) pupper.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();

                renderCutoutModel(this.getEntityModel(), collar.collarLocation(), matrixStackIn, bufferIn, packedLightIn, pupper, 1, 1, 1);
                if (pupper.getItemStackFromSlot(EquipmentSlotType.HEAD).hasEffect()) {


                    renderCutoutModel(this.getEntityModel(), ENCHANTED, matrixStackIn, bufferIn, packedLightIn, pupper, 1, 1, 1);

                }


            }
        }
    }


}