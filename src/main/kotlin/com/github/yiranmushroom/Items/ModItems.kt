package com.github.yiranmushroom.Items

import com.github.yiranmushroom.MCFurryAdditions
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier


object ModItems {
    fun register(
        path: String,
        factory: java.util.function.Function<Item.Settings, Item>,
        settings: Item.Settings?
    ): Item {
        val registryKey = RegistryKey.of(
            RegistryKeys.ITEM,
            Identifier.of(MCFurryAdditions.MOD_ID, path)
        )
        return Items.register(registryKey, factory, settings)
    }

    var gameModeControllerItem = register(
        "game_mode_controller_item",
        { settings: Item.Settings -> GameModeControllerItem(settings) },
        Item.Settings().maxCount(1)
    ) as GameModeControllerItem

    fun RegisterItems() {

    }
}