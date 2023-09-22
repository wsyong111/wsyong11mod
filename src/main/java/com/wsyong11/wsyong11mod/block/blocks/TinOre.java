package com.wsyong11.wsyong11mod.block.blocks;

import com.wsyong11.wsyong11mod.StaticValues;
import com.wsyong11.wsyong11mod.block.blocks.base.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class TinOre extends BlockBase {
	public TinOre() {
		super(
			Properties.of(Material.STONE)
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(StaticValues.HARVEST_LEVEL.STONE)
				.strength(3.0F, 3.0F)
				.requiresCorrectToolForDrops()
		);
	}
}
