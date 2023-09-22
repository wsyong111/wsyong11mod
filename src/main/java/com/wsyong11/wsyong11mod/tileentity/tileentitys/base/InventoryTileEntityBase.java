package com.wsyong11.wsyong11mod.tileentity.tileentitys.base;

import com.wsyong11.wsyong11mod.common.ItemStorage;
import com.wsyong11.wsyong11mod.common.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

public abstract class InventoryTileEntityBase extends TileEntityBase implements IInventory {
	protected final ItemStack[] items;

	public InventoryTileEntityBase(TileEntityType<?> tileEntityType, int itemStackSize) {
		super(tileEntityType);

		this.items = Utils.createItemStacks(itemStackSize);
	}

	@Override
	@ParametersAreNonnullByDefault
	public void load(BlockState blockState, CompoundNBT nbt) {
		super.load(blockState, nbt);

		this.setItems(ItemStorage.load(nbt, this.getContainerSize()));
	}

	@Override
	@Nonnull
	@ParametersAreNonnullByDefault
	public CompoundNBT save(CompoundNBT nbt) {
		super.save(nbt);

		ItemStorage.save(this.getInventory(), nbt);

		return nbt;
	}

	@Override
	public int getContainerSize() {
		return this.items.length;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemStack : this.items) {
			if (!itemStack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	@Nonnull
	public ItemStack getItem(int slot) {
		return this.items[slot];
	}

	@Override
	@Nonnull
	public ItemStack removeItem(int slot, int amount) {
		if (slot < 0 || slot >= this.items.length || this.items[slot].isEmpty()) {
			return ItemStack.EMPTY;
		}

		ItemStack itemStack = this.items[slot].split(amount);

		if (this.items[slot].isEmpty()) {
			this.items[slot] = ItemStack.EMPTY;
		}

		this.setChanged();

		return itemStack;
	}

	@Override
	@Nonnull
	public ItemStack removeItemNoUpdate(int slot) {
		if (slot < 0 || slot >= this.items.length || this.items[slot].isEmpty()) {
			return ItemStack.EMPTY;
		}

		ItemStack itemStack = this.items[slot];
		this.items[slot] = ItemStack.EMPTY;

		this.setChanged();

		return itemStack;
	}

	@Override
	@ParametersAreNonnullByDefault
	public void setItem(int slot, ItemStack stack) {
		if (slot < 0 || slot >= this.items.length) {
			return;
		}

		this.items[slot] = stack;

		if (!stack.isEmpty() && stack.getCount() > getMaxStackSize()) {
			stack.setCount(getMaxStackSize());
		}

		this.setChanged();
	}

	@Override
	@ParametersAreNonnullByDefault
	public boolean stillValid(PlayerEntity player) {
		return true;
	}

	@Override
	public void clearContent() {
		Arrays.fill(this.items, ItemStack.EMPTY);

		this.setChanged();
	}

	protected void setItems(ItemStack[] itemStacks) {
		System.arraycopy(itemStacks, 0, this.items, 0, this.items.length);
	}

	public IInventory getInventory() {
		return this;
	}
}
