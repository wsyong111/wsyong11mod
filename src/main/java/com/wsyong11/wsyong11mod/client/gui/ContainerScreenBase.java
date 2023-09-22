package com.wsyong11.wsyong11mod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_ID;

public abstract class ContainerScreenBase<T extends Container> extends ContainerScreen<T> {
	private static final Minecraft MINECRAFT = Minecraft.getInstance();
	private static final TextureManager TEXTURE_MANAGER = MINECRAFT.getTextureManager();

	private ResourceLocation backgroundTexture;

	public ContainerScreenBase(T container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title);

		this.backgroundTexture = null;
	}

	protected abstract String getBackgroundTexture();

	private ResourceLocation pGetBackgroundTexture() {
		if (this.backgroundTexture == null) {
			this.backgroundTexture = new ResourceLocation(
				MOD_ID,
				this.getBackgroundTexture()
			);
		}

		return this.backgroundTexture;
	}

	@Override
	@ParametersAreNonnullByDefault
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}

	@Override
	@ParametersAreNonnullByDefault
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		TEXTURE_MANAGER.bind(this.pGetBackgroundTexture());
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;

		this.blit(matrixStack, x, y, 0, 0, imageWidth, imageHeight);
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}
}
