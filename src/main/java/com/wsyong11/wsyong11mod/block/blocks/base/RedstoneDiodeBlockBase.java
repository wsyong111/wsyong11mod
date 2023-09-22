package com.wsyong11.wsyong11mod.block.blocks.base;

import com.wsyong11.wsyong11mod.common.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * 红石二极管基类
 */
public class RedstoneDiodeBlockBase extends HorizontalBlockBase {
	private static final VoxelShape SHAPE = Block.box(
		0.0D,
		0.0D,
		0.0D,
		16.0D,
		2.0D,
		16.0D
	);

	public RedstoneDiodeBlockBase(Properties properties) {
		super(properties);
	}

	@Override
	@ParametersAreNonnullByDefault
	@SuppressWarnings("deprecation")
	public boolean canSurvive(BlockState blockState, IWorldReader world, BlockPos pos) {
		return canSupportRigidBlock(world, pos.below());
	}

	@Override
	@ParametersAreNonnullByDefault
	@SuppressWarnings("deprecation")
	public void neighborChanged(BlockState blockState, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isRedstone) {
		if (!blockState.canSurvive(world, pos)) {
			Utils.destroyBlock(world, pos);
		}
	}

	@Override
	@ParametersAreNonnullByDefault
	@SuppressWarnings("deprecation")
	@Nonnull
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}


/*

 */
