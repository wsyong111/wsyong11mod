package com.wsyong11.wsyong11mod.block.blocks;

import com.wsyong11.wsyong11mod.StaticValues;
import com.wsyong11.wsyong11mod.block.blocks.base.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

// TODO: 编写电缆的代码

/**
 * 能量线缆
 */
public class ElectricCable extends BlockBase {
	public ElectricCable() {
		super(
			Properties.of(Material.METAL)
				.noOcclusion()
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(StaticValues.HARVEST_LEVEL.IRON)
				.strength(0.7F, 2.0F)
				.requiresCorrectToolForDrops()
		);
	}
}
