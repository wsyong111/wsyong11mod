package com.wsyong11.wsyong11mod.world;

import com.wsyong11.wsyong11mod.world.gen.WorldOreGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_ID;

/**
 * 这个类用于获取跟世界事件有关的事件
 */
@Mod.EventBusSubscriber(modid = MOD_ID)
public class WorldEvent {
	@SubscribeEvent
	public static void biomeLoadingEvent(BiomeLoadingEvent event) {
		WorldOreGeneration.generateOres(event);
	}

	private WorldEvent() {
	}
}
