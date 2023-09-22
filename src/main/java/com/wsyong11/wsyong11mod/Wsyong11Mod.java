package com.wsyong11.wsyong11mod;

import com.wsyong11.wsyong11mod.block.BlockRegister;
import com.wsyong11.wsyong11mod.client.register.ContainerRegister;
import com.wsyong11.wsyong11mod.client.register.ScreenRegister;
import com.wsyong11.wsyong11mod.common.ThisModConfig;
import com.wsyong11.wsyong11mod.item.ItemRegister;
import com.wsyong11.wsyong11mod.tileentity.TileEntityRegister;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_ID;
import static com.wsyong11.wsyong11mod.StaticValues.MOD_LOGGER;

@Mod(MOD_ID)
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Wsyong11Mod {
	public static ThisModConfig config;

	public Wsyong11Mod() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		config = new ThisModConfig(builder);

		ModLoadingContext modLoadingContext = ModLoadingContext.get();
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modLoadingContext.registerConfig(ModConfig.Type.COMMON, builder.build());

		ItemRegister.register(modEventBus);
		BlockRegister.register(modEventBus);
		TileEntityRegister.register(modEventBus);
		ContainerRegister.register(modEventBus);
	}

	@SubscribeEvent
	public static void onModSetupEvent(RegistryEvent.NewRegistry event) {
		if (ThisModConfig.Debug.debugMode.get()) {
			MOD_LOGGER.warn("Debug mode is Enabled!");
		}
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onFMLClientSetupEvent(FMLClientSetupEvent event) {
		ScreenRegister.register();
	}
}
