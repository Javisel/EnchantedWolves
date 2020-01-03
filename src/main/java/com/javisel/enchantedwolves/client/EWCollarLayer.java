package com.javisel.enchantedwolves.client;

import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.item.WolfCollar;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class EWCollarLayer extends LayerRenderer<WolfEntity, WolfModel<WolfEntity>> {

    private ResourceLocation ENCHANTED = new ResourceLocation(EnchantedWolves.MODID, "textures/entity/collar/enchanted_collar.png");
    protected static final ResourceLocation ENCHANTED_ITEM_GLINT_RES = new ResourceLocation("textures/misc/enchanted_item_glint.png");


    public EWCollarLayer(IEntityRenderer<WolfEntity, WolfModel<WolfEntity>> p_i50926_1_) {
        super(p_i50926_1_);
    }

    @Override
    public void render(WolfEntity wolfEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {


        if (wolfEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof WolfCollar && !wolfEntity.isInvisible()) {

            WolfCollar wolfCollar = (WolfCollar) wolfEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
            ItemStack collar = wolfEntity.getItemStackFromSlot(EquipmentSlotType.HEAD);
            this.bindTexture(wolfCollar.collarLocation());



            int i = 61680;
            int j = i % 65536;
            int k = i / 65536;
        //      GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
            GlStateManager.color4f(1, 1, 1, 1.0F);


            this.getEntityModel().render(wolfEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

            i = wolfEntity.getBrightnessForRender();
            j = i % 65536;
            k = i / 65536;
         //   GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
            this.func_215334_a(wolfEntity);


            ///ENCHANT
            if (wolfCollar.hasEffect(collar)) {

                this.bindTexture(ENCHANTED);
               // func_215338_a(this::bindTexture, wolfEntity, this.getEntityModel(), limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);


                 i = 61680;
                 j = i % 65536;
                 k = i / 65536;
                //      GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
                GlStateManager.color4f(1, 1, 1, 1.0F);


                this.getEntityModel().render(wolfEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

                i = wolfEntity.getBrightnessForRender();
                j = i % 65536;
                k = i / 65536;
                //   GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
                this.func_215334_a(wolfEntity);



            }



        }
    }




    public static <T extends Entity> void func_215338_a(Consumer<ResourceLocation> p_215338_0_, T p_215338_1_, EntityModel<T> p_215338_2_, float p_215338_3_, float p_215338_4_, float p_215338_5_, float p_215338_6_, float p_215338_7_, float p_215338_8_, float p_215338_9_) {
        float f = (float)p_215338_1_.ticksExisted + p_215338_5_;
        p_215338_0_.accept(ENCHANTED_ITEM_GLINT_RES);
        GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
        gamerenderer.setupFogColor(true);
        GlStateManager.enableBlend();
        GlStateManager.depthFunc(514);
        GlStateManager.depthMask(false);
        float f1 = 0.5F;
        GlStateManager.color4f(0.5F, 0.5F, 0.5F, 1.0F);

        for(int i = 0; i < 2; ++i) {
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
            float f2 = 0.76F;
            GlStateManager.color4f(0.38F, 0.19F, 0.608F, 1.0F);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f3 = 0.33333334F;
            GlStateManager.scalef(0.33333334F, 0.33333334F, 0.33333334F);
            GlStateManager.rotatef(30.0F - (float)i * 60.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translatef(0.0F, f * (0.001F + (float)i * 0.003F) * 20.0F, 0.0F);
            GlStateManager.matrixMode(5888);
            p_215338_2_.render(p_215338_1_, p_215338_3_, p_215338_4_, p_215338_6_, p_215338_7_, p_215338_8_, p_215338_9_);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }

        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
        GlStateManager.depthFunc(515);
        GlStateManager.disableBlend();
        gamerenderer.setupFogColor(false);
    }




    @Override
    public boolean shouldCombineTextures() {
        return false;
    }


}