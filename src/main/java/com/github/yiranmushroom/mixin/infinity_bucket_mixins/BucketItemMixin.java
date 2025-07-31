package com.github.yiranmushroom.mixin.infinity_bucket_mixins;


import com.github.yiranmushroom.Utils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public class BucketItemMixin extends Item {
    public BucketItemMixin(Properties settings) {
        super(settings);
    }

    @Inject(method = "getEmptySuccessItem", at = @At("RETURN"), cancellable = true)
    private static void inj$getEmptySuccessItem(ItemStack stack, Player player, CallbackInfoReturnable<ItemStack> cir) {
        if (Utils.HasInfinityEnchantment(stack)) {
            ItemStack newStack = stack.copy();
            cir.setReturnValue(newStack);
        }
    }
}

// /give @p minecraft:water_bucket[minecraft:enchantments={"infinity":1}]