package com.github.yiranmushroom.Items

import com.github.yiranmushroom.MCFurryAdditions
import com.github.yiranmushroom.network.MessageS2CPayload
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier
import net.minecraft.world.GameMode
import net.minecraft.world.World


class GameModeControllerItem(settings: Settings) : Item(settings) {
    override fun use(
        world: World,
        player: net.minecraft.entity.player.PlayerEntity,
        hand: net.minecraft.util.Hand
    ): net.minecraft.util.ActionResult {
        if (!world.isClient && player is ServerPlayerEntity) {
            val currentGameMode = player.gameMode
            val newGameMode = when (currentGameMode) {
                GameMode.SURVIVAL -> GameMode.CREATIVE
                GameMode.CREATIVE -> GameMode.ADVENTURE
                GameMode.ADVENTURE -> GameMode.SURVIVAL
                GameMode.SPECTATOR -> GameMode.SPECTATOR
            }
            player.changeGameMode(newGameMode)

            val payload = MessageS2CPayload(
                "Game mode changed to ${newGameMode.name.lowercase()}"
            )

            ServerPlayNetworking.send(player, payload)

            return net.minecraft.util.ActionResult.SUCCESS
        }
        return net.minecraft.util.ActionResult.PASS
    }
}