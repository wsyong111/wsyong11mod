package com.wsyong11.wsyong11mod.block.blocks;

import com.wsyong11.wsyong11mod.StaticValues;
import com.wsyong11.wsyong11mod.block.blocks.base.BlockBase;
import com.wsyong11.wsyong11mod.client.gui.coalgenerator.CoalGeneratorContainer;
import com.wsyong11.wsyong11mod.tileentity.tileentitys.CoalGeneratorTile;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class CoalGenerator extends BlockBase {
	public CoalGenerator() {
		super(
			Properties.of(Material.METAL)
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(StaticValues.HARVEST_LEVEL.IRON)
				.strength(1.2F, 5.2F)
				.requiresCorrectToolForDrops()
		);
	}

	@Override
	@ParametersAreNonnullByDefault
	@SuppressWarnings("deprecation")
	@Nonnull
	public ActionResultType use(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult blockRayTraceResult) {
		if (world.isClientSide()) {
			return ActionResultType.SUCCESS;
		}

		CoalGeneratorTile tileEntity = (CoalGeneratorTile) world.getBlockEntity(pos);

		if (tileEntity != null) {
			INamedContainerProvider containerProvider = this.createContentProvider(tileEntity);

			NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getBlockPos());
		}

		return ActionResultType.SUCCESS;
	}

	private INamedContainerProvider createContentProvider(CoalGeneratorTile tileEntity) {
		return new INamedContainerProvider() {
			@Override
			@Nonnull
			public ITextComponent getDisplayName() {
				return new TranslationTextComponent("screen.wsyong11mod.coal_generator");
			}

			@Override
			@Nonnull
			@ParametersAreNonnullByDefault
			public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
				return new CoalGeneratorContainer(windowId, playerInventory, tileEntity.getInventory());
			}
		};
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new CoalGeneratorTile();
	}
}
