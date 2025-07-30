package com.github.yiranmushroom

import com.github.yiranmushroom.*
import com.github.yiranmushroom.Items.ModItems
import com.github.yiranmushroom.network.registerS2CPayloads
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object MCFurryAdditions : ModInitializer {
	val MOD_ID = "mcfurry-additions"

	val logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("MCFurryAdditions main is initializing...")

//		registerS2CPayloads()
//		com.github.yiranmushroom.event.OnInitialize()
//		ModItems.RegisterItems()
	}
}