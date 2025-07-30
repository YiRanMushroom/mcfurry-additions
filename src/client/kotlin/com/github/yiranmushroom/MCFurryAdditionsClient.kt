package com.github.yiranmushroom

import com.github.yiranmushroom.network.ClientMessagePayloadReceiver
import com.github.yiranmushroom.network.MessageS2CPayload
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking

object MCFurryAdditionsClient : ClientModInitializer {
	override fun onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(
			MessageS2CPayload.ID, ::ClientMessagePayloadReceiver)
	}
}