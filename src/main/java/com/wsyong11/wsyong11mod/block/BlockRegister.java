package com.wsyong11.wsyong11mod.block;

import com.wsyong11.wsyong11mod.block.blocks.CoalGenerator;
import com.wsyong11.wsyong11mod.block.blocks.MachineFrame;
import com.wsyong11.wsyong11mod.block.blocks.RedstonePowerAdjuster;
import com.wsyong11.wsyong11mod.block.blocks.TinOre;
import net.minecraft.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_ID;

/**
 * 这个类用于注册方块
 */
public class BlockRegister {
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

	// -------------------------------------------------------------------------------------------------------------- //
	public static final RegistryObject<Block> redstone_power_adjuster = BLOCKS.register(
		"redstone_power_adjuster",
		RedstonePowerAdjuster::new
	);

	public static final RegistryObject<Block> coal_generator = BLOCKS.register(
		"coal_generator",
		CoalGenerator::new
	);

	public static final RegistryObject<Block> machine_frame = BLOCKS.register(
		"machine_frame",
		MachineFrame::new
	);

	public static final RegistryObject<Block> tin_ore = BLOCKS.register(
		"tin_ore",
		TinOre::new
	);
	// -------------------------------------------------------------------------------------------------------------- //

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}

	private BlockRegister() {
	}
}
