package com.github.yiranmushroom.mixin.infinity_bucket_mixins;

import com.github.yiranmushroom.Utils;
import net.minecraft.core.dispenser.BlockSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Mixin(targets = "net/minecraft/core/dispenser/DispenseItemBehavior$5")
public class DispenseItemBehavior5Mixin {

    @Unique
    private ItemStack uni$bucket;

    @Inject(method = "execute", at = @At("HEAD"))
    public void getBucketItem(BlockSource source, ItemStack stack, CallbackInfoReturnable<ItemStack> info) {
        uni$bucket = stack.copy();
    }

    @Inject(at = @At(value = "RETURN", ordinal = 0), method = "execute", cancellable = true)
    private void iwb$modifyWaterBucketBehavior(BlockSource source, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (Utils.HasInfinityEnchantment(uni$bucket) && (uni$bucket.is(Items.WATER_BUCKET) ||
                (uni$bucket.is(Items.LAVA_BUCKET))))
            cir.setReturnValue(uni$bucket);
    }
}
