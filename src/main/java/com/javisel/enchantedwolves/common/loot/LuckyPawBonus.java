package com.javisel.enchantedwolves.common.loot;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.functions.ILootFunction;

import java.util.Set;

public class LuckyPawBonus extends LootFunction {
    private final RandomValueRange count;
    private final int limit;

    private LuckyPawBonus(ILootCondition[] conditions, RandomValueRange countIn, int limitIn) {
        super(conditions);
        this.count = countIn;
        this.limit = limitIn;
    }

    public static LuckyPawBonus.Builder func_215915_a(RandomValueRange p_215915_0_) {
        return new LuckyPawBonus.Builder(p_215915_0_);
    }

    public Set<LootParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(LootParameters.KILLER_ENTITY);
    }

    private boolean func_215917_b() {
        return this.limit > 0;
    }

    public ItemStack doApply(ItemStack stack, LootContext context) {
        Entity entity = context.get(LootParameters.DIRECT_KILLER_ENTITY);
        if (entity instanceof LivingEntity) {
            //  int i =EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistration.LUCKYPAW,((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.HEAD));

            int i = 0;

            if (i == 0) {
                return stack;
            }

            float f = (float) i * this.count.generateFloat(context.getRandom());
            stack.grow(Math.round(f));
            if (this.func_215917_b() && stack.getCount() > this.limit) {
                stack.setCount(this.limit);
            }
        }

        return stack;
    }

    public static class Builder extends LootFunction.Builder<LuckyPawBonus.Builder> {
        private final RandomValueRange field_216073_a;
        private int field_216074_b = 0;

        public Builder(RandomValueRange p_i50932_1_) {
            this.field_216073_a = p_i50932_1_;
        }

        protected LuckyPawBonus.Builder doCast() {
            return this;
        }

        public LuckyPawBonus.Builder func_216072_a(int p_216072_1_) {
            this.field_216074_b = p_216072_1_;
            return this;
        }

        public ILootFunction build() {
            return new LuckyPawBonus(this.getConditions(), this.field_216073_a, this.field_216074_b);
        }
    }

    public static class Serializer extends LootFunction.Serializer<LuckyPawBonus> {
        protected Serializer() {
            super(new ResourceLocation("looting_enchant"), LuckyPawBonus.class);
        }

        public void serialize(JsonObject object, LuckyPawBonus functionClazz, JsonSerializationContext serializationContext) {
            super.serialize(object, functionClazz, serializationContext);
            object.add("count", serializationContext.serialize(functionClazz.count));
            if (functionClazz.func_215917_b()) {
                object.add("limit", serializationContext.serialize(functionClazz.limit));
            }

        }

        public LuckyPawBonus deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
            int i = JSONUtils.getInt(object, "limit", 0);
            return new LuckyPawBonus(conditionsIn, JSONUtils.deserializeClass(object, "count", deserializationContext, RandomValueRange.class), i);
        }
    }
}