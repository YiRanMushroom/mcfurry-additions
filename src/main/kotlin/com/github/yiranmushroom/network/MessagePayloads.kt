package com.github.yiranmushroom.network

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.codec.StreamEncoder
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation

data class MessageS2CPayload(
    val message: String
) : CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return ID
    }

    companion object {
        val SEND_MESSAGE_PAYLOAD_ID: ResourceLocation = ResourceLocation.withDefaultNamespace("mcfurry-send-message")
        val ID = CustomPacketPayload.Type<MessageS2CPayload>(SEND_MESSAGE_PAYLOAD_ID)
        val CODEC : StreamCodec<FriendlyByteBuf, MessageS2CPayload> =
            StreamCodec.of(
                { buf, value -> buf.writeUtf(value.message) },
                { buf -> MessageS2CPayload(buf.readUtf()) }
            )
    }
}