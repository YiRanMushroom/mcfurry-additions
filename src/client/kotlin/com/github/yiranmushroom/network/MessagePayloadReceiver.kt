package com.github.yiranmushroom.network

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.text.Text

fun ClientMessagePayloadReceiver(payload: Any?, context: ClientPlayNetworking.Context) {
    when (payload) {
        is MessageS2CPayload -> {
            val message = Text.of("[MCFurry] ${payload.message}")
            // send the message to the chat
            context.player()?.sendMessage(message, false)
        }
    }
}