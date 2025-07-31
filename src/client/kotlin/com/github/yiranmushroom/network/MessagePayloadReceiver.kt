package com.github.yiranmushroom.network

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking

fun ClientMessagePayloadReceiver(payload: Any?, context: ClientPlayNetworking.Context) {
    when (payload) {
        is MessageS2CPayload -> {
            val player = context.player()
            val component = net.minecraft.network.chat.Component.literal("[MCFurry] ${payload.message}")
            player.displayClientMessage(component, false)
        }
    }
}