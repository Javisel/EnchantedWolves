package com.javisel.enchantedwolves.client;

import com.javisel.enchantedwolves.EnchantedWolves;
import com.javisel.enchantedwolves.common.item.WolfCollar;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntityWolf;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EWCollarLayer implements LayerRenderer<EntityWolf> {

    private ResourceLocation ENCHANTED = new ResourceLocation(EnchantedWolves.MODID, "textures/entity/collar/enchanted_collar.png");

    private final RenderLiving renderer;


    public EWCollarLayer( RenderLiving renderer) {
        this.renderer = renderer;
    }


    @Override
    public void doRenderLayer(EntityWolf pupper, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (pupper.isTamed() && !pupper.isInvisible()) {

            if (pupper.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof WolfCollar) {
                WolfCollar collar = (WolfCollar) pupper.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem();

                renderer.bindTexture(collar.collarLocation());
                renderer.getMainModel().render(pupper,limbSwing,limbSwingAmount,partialTicks,netHeadYaw,headPitch,scale);

                if (pupper.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isItemEnchanted()) {


                    renderer.bindTexture(ENCHANTED);
                    renderer.getMainModel().render(pupper,limbSwing,limbSwingAmount,partialTicks,netHeadYaw,headPitch,scale);

                }


            }
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}