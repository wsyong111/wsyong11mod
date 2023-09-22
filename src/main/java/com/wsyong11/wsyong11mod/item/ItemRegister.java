package com.wsyong11.wsyong11mod.item;

import com.wsyong11.wsyong11mod.block.BlockRegister;
import com.wsyong11.wsyong11mod.item.items.TNTBread;
import com.wsyong11.wsyong11mod.item.items.TinIngots;
import com.wsyong11.wsyong11mod.item.items.base.ItemBase;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_ID;

/**
 * 这个类用于注册物品和和方块物品
 */
public class ItemRegister {
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

	// -------------------------------------------------------------------------------------------------------------- //
	public static final RegistryObject<Item> screwdriver = ITEMS.register(
		"screwdriver",
		() -> new ItemBase(new Item.Properties().tab(ItemGroup.miscItemGroup))
	);

	public static final RegistryObject<Item> tnt_bread = ITEMS.register(
		"tnt_bread",
		TNTBread::new
	);

	public static final RegistryObject<Item> tin_ingots = ITEMS.register(
		"tin_ingots",
		TinIngots::new
	);
	// -------------------------------------------------------------------------------------------------------------- //

	// -------------------------------------------------------------------------------------------------------------- //
	public static final RegistryObject<Item> redstone_power_adjuster = ITEMS.register(
		"redstone_power_adjuster",
		() -> new BlockItem(
			BlockRegister.redstone_power_adjuster.get(),
			new Item.Properties().tab(ItemGroup.redstoneItemGroup)
		)
	);

	public static final RegistryObject<Item> coal_generator = ITEMS.register(
		"coal_generator",
		() -> new BlockItem(
			BlockRegister.coal_generator.get(),
			new Item.Properties().tab(ItemGroup.machineItemGroup)
		)
	);

	public static final RegistryObject<Item> machine_frame = ITEMS.register(
		"machine_frame",
		() -> new BlockItem(
			BlockRegister.machine_frame.get(),
			new Item.Properties().tab(ItemGroup.miscItemGroup)
		)
	);

	public static final RegistryObject<Item> tin_ore = ITEMS.register(
		"tin_ore",
		() -> new BlockItem(
			BlockRegister.tin_ore.get(),
			new Item.Properties().tab(ItemGroup.oresItemGroup)
		)
	);
	// -------------------------------------------------------------------------------------------------------------- //

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}

	private ItemRegister() {
	}
}
