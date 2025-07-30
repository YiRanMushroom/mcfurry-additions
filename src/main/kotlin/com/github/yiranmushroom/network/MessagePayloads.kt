package com.github.yiranmushroom.network

import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier

data class MessageS2CPayload(
    val message: String
) : CustomPayload {
    companion object {
        val SEND_MESSAGE_PAYLOAD_ID = Identifier.of("mcfurry-send-message")
        val ID = CustomPayload.Id<MessageS2CPayload>(SEND_MESSAGE_PAYLOAD_ID)
        val CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            MessageS2CPayload::message
        ) { message -> MessageS2CPayload(message) }
    }

    override fun getId(): CustomPayload.Id<out CustomPayload?>? {
        return ID
    }
}