package com.github.yiranmushroom.Items

import com.github.yiranmushroom.MCFurryAdditions
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import java.util.function.Function

object ModItems {

    var gameModeControllerItem : GameModeControllerItem? = null

    fun RegisterItems() {
        gameModeControllerItem = registerItem(
            ResourceKey.create(Registries.ITEM, ResourceLocation
                .fromNamespaceAndPath(MCFurryAdditions.MOD_ID, "game_mode_controller")),
            {
                GameModeControllerItem(it)
            },
            Item.Properties()
                .stacksTo(1)
        ) as GameModeControllerItem?
    }

    fun registerItem(
        resourceKey: ResourceKey<Item>,
        function: (Item.Properties) -> Item,
        properties: Item.Properties
    ): Item {
        val item = function(properties.setId(resourceKey))
        if (item is BlockItem) {
            item.registerBlocks(Item.BY_BLOCK, item)
        }

        return Registry.register(BuiltInRegistries.ITEM, resourceKey, item) as Item
    }
}