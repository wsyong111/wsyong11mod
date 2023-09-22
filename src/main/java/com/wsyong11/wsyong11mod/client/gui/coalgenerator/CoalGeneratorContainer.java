package com.wsyong11.wsyong11mod.client.gui.coalgenerator;

import com.wsyong11.wsyong11mod.client.gui.ContainerBase;
import com.wsyong11.wsyong11mod.client.register.ContainerRegister;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class CoalGeneratorContainer extends ContainerBase {
	public CoalGeneratorContainer(int windowId,PlayerInventory playerInventory, IInventory blockInventory) {
		super(ContainerRegister.coal_generator_container.get(), playerInventory, windowId);

		this.addSlot(new Slot(blockInventory, 0, 80, 35));
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity p_82846_1_, int p_82846_2_) {
		return super.quickMoveStack(p_82846_1_, p_82846_2_);
	}
}
