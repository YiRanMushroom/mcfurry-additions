package com.github.yiranmushroom.event

import com.github.yiranmushroom.MCFurryAdditions
import com.github.yiranmushroom.network.MessageS2CPayload
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.networking.v1.PlayerLookup
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.MinecraftServer
import net.minecraft.server.world.ServerWorld

object MCFurryEventHandlerPerSecond {
    private var tickCounter = 0
    private const val TICKS_PER_SECOND = 20
    private val subscriptions = mutableMapOf<String, (MinecraftServer) -> Unit>()

    fun subscribe(
        key: String,
        callback: (MinecraftServer) -> Unit
    ) {
        if (!subscriptions.containsKey(key)) {
            subscriptions[key] = callback
        } else {
            throw IllegalArgumentException("Key '$key' is already subscribed.")
        }
    }

    fun unsubscribe(key: String) {
        if (subscriptions.containsKey(key)) {
            subscriptions.remove(key)
        } else {
            throw IllegalArgumentException("Key '$key' is not subscribed.")
        }
    }

    fun tick(server: MinecraftServer) {
        tickCounter++
        if (tickCounter >= TICKS_PER_SECOND) {
            tickCounter = 0
            subscriptions.values.forEach { it.invoke(server) }
        }
    }

    fun RegisterTickHandler() {
        ServerTickEvents.END_SERVER_TICK.register(::tick)
    }
}

fun OnInitialize() {
    ServerLifecycleEvents.SERVER_STARTING.register { _ ->
        MCFurryEventHandlerPerSecond.RegisterTickHandler()
    }
}