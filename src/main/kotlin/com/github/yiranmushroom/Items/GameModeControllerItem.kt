package com.github.yiranmushroom.Items

import com.github.yiranmushroom.MCFurryAdditions
import com.github.yiranmushroom.network.MessageS2CPayload
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.dedicated.Settings
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.level.GameType
import net.minecraft.world.level.Level

class GameModeControllerItem(settings: Properties) : Item(settings) {
    override fun use(
        world: Level,
        player: Player,
        hand: InteractionHand
    ): InteractionResult {
        if (player is ServerPlayer) {
            val currentGameMode = player.gameMode.gameModeForPlayer
            val newGameMode = when (currentGameMode) {
                GameType.SURVIVAL -> GameType.CREATIVE
                GameType.CREATIVE -> GameType.ADVENTURE
                GameType.ADVENTURE -> GameType.SURVIVAL
                else -> GameType.CREATIVE
            }

            player.setGameMode(newGameMode)

            val payload = MessageS2CPayload(
                "Game mode changed to ${newGameMode.name.lowercase()}"
            )

            ServerPlayNetworking.send(player, payload)

            return InteractionResult.SUCCESS
        }
        return InteractionResult.PASS
    }
}