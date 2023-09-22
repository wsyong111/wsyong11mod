package com.wsyong11.wsyong11mod.block.blocks;

import com.wsyong11.wsyong11mod.StaticValues;
import com.wsyong11.wsyong11mod.block.blocks.base.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

/**
 * 机器框架, 用于合成机器
 */
public class MachineFrame extends BlockBase {
	public MachineFrame() {
		super(
			Properties.of(Material.METAL)
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(StaticValues.HARVEST_LEVEL.IRON)
				.strength(1.2F, 5.2F)
				.requiresCorrectToolForDrops()
		);
	}
}
