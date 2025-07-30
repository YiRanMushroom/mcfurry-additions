package com.github.yiranmushroom.mixin;

import com.github.yiranmushroom.MCFurryAdditions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Redirect(method = "tryUseDeathProtector",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    private void preventItemStackDecrement(ItemStack stack, int amount) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (self.getWorld().isClient()) {
            // If this is a client-side operation, we do not want to decrement the stack.
            stack.decrement(amount);
            return;
        }

        if (stack.getCount() != 1) {
            stack.decrement(amount);
            return;
        }

        // if stack is undying totem then check
        if (stack.isOf(Items.TOTEM_OF_UNDYING)) {
            // check if stack has infinity enchantment
            if (stack.hasEnchantments() && stack.getEnchantments().getEnchantments().stream()
                    .anyMatch(enchantment ->
                            enchantment.getIdAsString().equals("minecraft:infinity"))) {
                if (self instanceof PlayerEntity) {
                    return;
                }
            } else {
                MCFurryAdditions.INSTANCE.getLogger().info("LivingEntityMixin: Decrementing stack of {} with item {}", self.getName().getString(), stack.getName().getString());
            }
        }

        stack.decrement(amount);
    }

}
