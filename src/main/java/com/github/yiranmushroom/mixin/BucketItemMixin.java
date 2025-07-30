package com.github.yiranmushroom.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public class BucketItemMixin {
    @Inject(method = "getEmptiedStack", at = @At("RETURN"), cancellable = true)
    private static void getEmptiedStack(ItemStack stack, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        if (stack.getEnchantments().getEnchantments().stream().anyMatch(
                enchantment -> enchantment.getIdAsString().equals("minecraft:infinity"))) {
            ItemStack newStack = stack.copy();
            cir.setReturnValue(newStack);
        }
    }
}

// /give @p minecraft:water_bucket[minecraft:enchantments={"infinity":1}]