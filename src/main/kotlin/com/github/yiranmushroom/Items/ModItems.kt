package com.github.yiranmushroom.Items

import com.github.yiranmushroom.MCFurryAdditions
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item


object ModItems {

    var gameModeControllerItem = registerItem(
        "game_mode_controller",
        GameModeControllerItem(
            Item.Properties()
                .stacksTo(1)
        )
    ) as GameModeControllerItem

    fun RegisterItems() {

    }

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(
            BuiltInRegistries.ITEM,
            ResourceLocation.fromNamespaceAndPath(MCFurryAdditions.MOD_ID, name),
            item
        )
    }
}