package com.wsyong11.wsyong11mod.client.gui.coalgenerator;

import com.wsyong11.wsyong11mod.client.gui.ContainerScreenBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CoalGeneratorScreen extends ContainerScreenBase<CoalGeneratorContainer> {
	public CoalGeneratorScreen(CoalGeneratorContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title);
	}

	@Override
	protected String getBackgroundTexture() {
		return "textures/gui/container/coal_generator.png";
	}
}
