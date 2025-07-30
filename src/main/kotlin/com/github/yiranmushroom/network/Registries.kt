package com.github.yiranmushroom.network

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry

fun registerS2CPayloads() {
    PayloadTypeRegistry.playS2C().register(MessageS2CPayload.ID, MessageS2CPayload.CODEC)
}