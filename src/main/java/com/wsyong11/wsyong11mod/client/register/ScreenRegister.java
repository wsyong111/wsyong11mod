package com.wsyong11.wsyong11mod.client.register;

import com.wsyong11.wsyong11mod.client.gui.coalgenerator.CoalGeneratorScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class ScreenRegister {
	public static void register() {
		ScreenManager.register(
			ContainerRegister.coal_generator_container.get(),
			CoalGeneratorScreen::new
		);
	}

	private ScreenRegister() {
	}
}
