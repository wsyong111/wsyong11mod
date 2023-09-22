package com.wsyong11.wsyong11mod.block.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * 继承自这个类的方块会在放置时朝向玩家
 */
public class HorizontalBlockBase extends BlockBase {
	public static final EnumProperty<Direction> FACING = EnumProperty.create(
		"facing",
		Direction.class,
		Direction.NORTH,
		Direction.SOUTH,
		Direction.EAST,
		Direction.WEST
	);

	public HorizontalBlockBase(Properties properties) {
		super(properties);
	}

	@Override
	@Nullable
	@ParametersAreNonnullByDefault
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction facing = context.getHorizontalDirection().getOpposite();

		return this.defaultBlockState()
			.setValue(FACING, facing);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);

		super.createBlockStateDefinition(builder);
	}
}
