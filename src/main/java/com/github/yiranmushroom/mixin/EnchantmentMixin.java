package com.github.yiranmushroom.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.HashSet;
import java.util.Set;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Shadow public abstract Component description();

    @Unique
    private static Set<Item> uni$allowEnchantmentItems;

    @Unique Set<Item> GetAllowEnchantmentItems() {
        if (uni$allowEnchantmentItems != null) {
            return uni$allowEnchantmentItems;
        }

        uni$allowEnchantmentItems = Set.of(
                Items.WATER_BUCKET
//                ,
//                Items.LAVA_BUCKET,
//                Items.MILK_BUCKET,
//                Items.TOTEM_OF_UNDYING
        );

        return uni$allowEnchantmentItems;
    }

    @Inject(at = @At("HEAD"), method = "canEnchant", cancellable = true)
    protected void uni$infinityStub(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
        if (this.description().getString().equals(Component.translatable("enchantment.minecraft.infinity").getString())
                && GetAllowEnchantmentItems().contains(stack.getItem())) {
            info.setReturnValue(true);
        }
    }
}
