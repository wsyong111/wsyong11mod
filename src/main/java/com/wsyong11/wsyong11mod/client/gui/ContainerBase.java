package com.wsyong11.wsyong11mod.client.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class ContainerBase extends Container {
	protected ContainerBase(@Nullable ContainerType<?> containerType, PlayerInventory playerInventory, int windowId) {
		super(containerType, windowId);

		// 添加玩家物品栏槽
		for (int x = 0; x < 9; x++) {
			int xPos = 8 + x * 18;
			int yPos = 142;
			this.addSlot(new Slot(playerInventory, x, xPos, yPos));
		}

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				int index = (x + y * 9) + 9;
				int xPos = 8 + x * 18;
				int yPos = 84 + y * 18;

				this.addSlot(new Slot(playerInventory, index, xPos, yPos));
			}
		}
	}

	@Override
	@ParametersAreNonnullByDefault
	public boolean stillValid(PlayerEntity player) {
		return true;
	}
}
