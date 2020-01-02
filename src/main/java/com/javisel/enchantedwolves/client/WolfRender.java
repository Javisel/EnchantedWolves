package com.javisel.enchantedwolves.client;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class WolfRender extends WolfRenderer
{
    public WolfRender(EntityRendererManager p_i47187_1_) {
        super(p_i47187_1_);
        addLayer(new EWCollarLayer(this));
    }
    public static class RenderFactory implements IRenderFactory<WolfEntity> {
        @Override
        public EntityRenderer<? super WolfEntity> createRenderFor(EntityRendererManager manager) {
            final WolfRender render = new WolfRender(manager);
            return render;
        }

    }

}
