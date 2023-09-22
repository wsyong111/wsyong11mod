package com.wsyong11.wsyong11mod.block.blocks;

import com.wsyong11.wsyong11mod.block.blocks.base.RedstoneDiodeBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * 红石能量调整器
 */
public class RedstonePowerAdjuster extends RedstoneDiodeBlockBase {
	public static final IntegerProperty POWER = IntegerProperty.create(
		"power",
		0,
		15
	);

	public RedstonePowerAdjuster() {
		super(
			Properties.of(Material.STONE)
				.strength(0.0F, 0.0F)
				.noOcclusion()
				.requiresCorrectToolForDrops()
		);
	}

	@Override
	@ParametersAreNonnullByDefault
	@SuppressWarnings("deprecation")
	@Nonnull
	public ActionResultType use(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
		if (hand != Hand.MAIN_HAND) {
			return ActionResultType.FAIL;
		}

		int redstoneLevelAdd = blockState.getValue(POWER) + 1;

		if (redstoneLevelAdd > 15) {
			redstoneLevelAdd = 0;
		}

		world.setBlockAndUpdate(
			pos,
			blockState.setValue(POWER, redstoneLevelAdd)
		);

		float soundTone = ((float) redstoneLevelAdd / 15.0F) + 0.5F;

		world.playSound(
			null,
			pos.getX(),
			pos.getY(),
			pos.getZ(),
			SoundEvents.LEVER_CLICK,
			SoundCategory.BLOCKS,
			1.0F,
			soundTone
		);

		return ActionResultType.SUCCESS;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(POWER);

		super.createBlockStateDefinition(builder);
	}

	@Override
	@ParametersAreNonnullByDefault
	@SuppressWarnings("deprecation")
	public int getSignal(BlockState state, IBlockReader world, BlockPos pos, Direction side) {
		if (side == Direction.UP || side == Direction.DOWN) {
			return 0;
		}

		return state.getValue(POWER);
	}

	@Override
	@ParametersAreNonnullByDefault
	@SuppressWarnings("deprecation")
	public boolean isSignalSource(BlockState state) {
		return true;
	}
}
