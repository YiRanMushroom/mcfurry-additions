package com.github.yiranmushroom

import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantments

object Utils {
    @JvmStatic
    fun HasInfinityEnchantment(
        itemStack: ItemStack
    ) : Boolean {
        return itemStack.enchantments.keySet().stream().
                anyMatch {
                    it.`is`(Enchantments.INFINITY)
                }
    }
}