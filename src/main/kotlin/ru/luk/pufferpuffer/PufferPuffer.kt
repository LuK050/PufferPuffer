package ru.luk.pufferpuffer
import ru.luk.pufferpuffer.item.ModItems

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object PufferPuffer : ModInitializer {
	private val logger = LoggerFactory.getLogger("pufferpuffer")

	override fun onInitialize() {
		ModItems.initialize()
		logger.info("Hello Puffer!")
	}
}