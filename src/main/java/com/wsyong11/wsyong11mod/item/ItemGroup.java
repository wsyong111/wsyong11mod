package com.wsyong11.wsyong11mod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_ID;

/**
 * 模组标签页
 */
public class ItemGroup extends net.minecraft.item.ItemGroup {
	public static final ItemGroup oresItemGroup = new ItemGroup("ores", null);
	public static final ItemGroup machineItemGroup = new ItemGroup("machine", ItemRegister.coal_generator);
	public static final ItemGroup redstoneItemGroup = new ItemGroup("redstone", ItemRegister.redstone_power_adjuster);
	public static final ItemGroup miscItemGroup = new ItemGroup("misc");
	public static final ItemGroup foodItemGroup = new ItemGroup("food", ItemRegister.tnt_bread);

	private final RegistryObject<Item> iconItem;

	public ItemGroup(@Nonnull String id, @Nullable RegistryObject<Item> iconItem) {
		super(MOD_ID + "." + id);

		this.iconItem = iconItem;
	}

	public ItemGroup(@Nonnull String id) {
		this(id, null);
	}

	@Override
	@Nonnull
	public ItemStack makeIcon() {
		Item iconItem = this.iconItem == null ? Items.AIR : this.iconItem.get();

		return new ItemStack(iconItem);
	}
}
