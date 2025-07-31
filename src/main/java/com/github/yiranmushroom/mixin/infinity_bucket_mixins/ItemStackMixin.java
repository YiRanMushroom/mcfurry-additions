package com.github.yiranmushroom.mixin.infinity_bucket_mixins;

import com.github.yiranmushroom.Utils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.UseRemainder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Unique
    private ItemStack uni$milk;

    @Inject(method = "applyAfterUseComponentSideEffects", at = @At("HEAD"))
    public void getBucketItem(LivingEntity entity, ItemStack stack, CallbackInfoReturnable<ItemStack> info) {
        uni$milk = stack.copy();
    }

    @Redirect(method = "applyAfterUseComponentSideEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/UseRemainder;convertIntoRemainder(Lnet/minecraft/world/item/ItemStack;IZLnet/minecraft/world/item/component/UseRemainder$OnExtraCreatedRemainder;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack iwb$finishUsingItem(UseRemainder remainder, ItemStack stack, int amount, boolean reduce, UseRemainder.OnExtraCreatedRemainder extra) {
        if (Utils.HasInfinityEnchantment(uni$milk))
            return uni$milk;
        return remainder.convertIntoRemainder(stack, amount, reduce, extra);
    }
}
