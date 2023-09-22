package com.wsyong11.wsyong11mod.common;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemStorage {
	private static final String STORAGE_KEY_NAME = "items";

	/**
	 * 将给定的物品栏保存到 nbt 中
	 *
	 * @param inventory 物品栏
	 * @param nbt       nbt
	 */
	@Nonnull
	public static CompoundNBT save(@Nonnull IInventory inventory, CompoundNBT nbt) {
		ListNBT inventoryNBT = new ListNBT();

		for (int i = 0; i < inventory.getContainerSize(); i++) {
			ItemStack itemStack = inventory.getItem(i);

			if (itemStack!=null) {
				if (!itemStack.isEmpty()) {
					inventoryNBT.add(
						itemStack.save(new CompoundNBT())
					);
				}
			}
		}

		nbt.put(STORAGE_KEY_NAME, inventoryNBT);

		return nbt;
	}

	/**
	 * 从给定的 nbt 中加载物品栏
	 */
	public static ItemStack[] load(@Nonnull CompoundNBT nbt, int inventorySize) {
		ListNBT inventoryNBT = (ListNBT) nbt.get(STORAGE_KEY_NAME);

		ItemStack[] itemStacks = new ItemStack[inventorySize];

		if (inventoryNBT != null) {
			for (int i = 0; i < inventoryNBT.size(); i++) {
				CompoundNBT nbtItem = (CompoundNBT) inventoryNBT.get(i);

				itemStacks[i] = ItemStack.of(nbtItem);
			}
		}

		return itemStacks;
	}

	private ItemStorage() {
	}
}
