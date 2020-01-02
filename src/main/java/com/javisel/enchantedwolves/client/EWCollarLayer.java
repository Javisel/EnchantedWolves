package com.javisel.enchantedwolves.client;

import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.item.WolfCollar;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
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
    public void render(WolfEntity wolfEntity, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {


        if (wolfEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof WolfCollar) {

            WolfCollar wolfCollar = (WolfCollar) wolfEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
            this.bindTexture(wolfCollar.collarLocation());
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            if (wolfEntity.isInvisible()) {
                GlStateManager.depthMask(false);
            } else {
                GlStateManager.depthMask(true);
            }

            int i = 61680;
            int j = i % 65536;
            int k = i / 65536;
            GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
          //  GlStateManager.color4f(1, 1, 1, 1.0F);
            GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
            gamerenderer.setupFogColor(true);
            this.getEntityModel().render(wolfEntity, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);

            gamerenderer.setupFogColor(false);
            i = wolfEntity.getBrightnessForRender();
            j = i % 65536;
            k = i / 65536;
            GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
            this.func_215334_a(wolfEntity);
            GlStateManager.depthMask(true);
            GlStateManager.disableBlend();

            ///ENCHANT

            if (wolfEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).hasEffect()) {
                this.bindTexture(ENCHANTED);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
                if (wolfEntity.isInvisible()) {
                    GlStateManager.depthMask(false);
                } else {
                    GlStateManager.depthMask(true);
                }


                GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
             //   GlStateManager.color4f(1, 1, 1, 1.0F);

                gamerenderer.setupFogColor(true);
                this.getEntityModel().render(wolfEntity, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);

                gamerenderer.setupFogColor(false);
                i = wolfEntity.getBrightnessForRender();
                j = i % 65536;
                k = i / 65536;
                GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
                this.func_215334_a(wolfEntity);
                GlStateManager.depthMask(true);
                GlStateManager.disableBlend();


            }



        }
    }









    @Override
    public boolean shouldCombineTextures() {
        return false;
    }


}