package com.wsyong11.wsyong11mod.block.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_LOGGER;

/**
 * 所有方块的基类
 */
public class BlockBase extends Block {
	private static final List<ItemStack> EMPTY_ITEM_STACK_LIST = new ArrayList<>();

	/**
	 * 在被破坏时是否变成物品
	 */
	protected boolean canDropItem;

	/**
	 * 被破坏时产生的物品数量
	 */
	protected int dropItemCount;

	public BlockBase(Properties properties) {
		super(properties);

		this.canDropItem = true;
		this.dropItemCount = 1;
	}

	@Override
	@ParametersAreNonnullByDefault
	@SuppressWarnings("deprecation")
	@Nonnull
	public List<ItemStack> getDrops(BlockState blockState, LootContext.Builder builder) {
		// 如果 canDropItem 为 false 或者 dropItemCount 小于等于 0
		if (!this.canDropItem || this.dropItemCount <= 0) {
			return EMPTY_ITEM_STACK_LIST;
		}

		ResourceLocation blockRegistryId = this.getRegistryName();

		if (blockRegistryId == null) {
			// 获取类的包名
			String className = this.getClass().getPackage().getName();

			MOD_LOGGER.warn("Cannot get block registry id Class: {}", className);

			return EMPTY_ITEM_STACK_LIST;
		}

		Item item = ForgeRegistries.ITEMS.getValue(blockRegistryId);

		if (item == null) {
			MOD_LOGGER.warn("Cannot get block item {}", String.valueOf(blockRegistryId));

			return EMPTY_ITEM_STACK_LIST;
		}

		// 获取挖掘的工具
//		ItemStack tool = builder.getParameter(LootParameters.TOOL);

		// 检查挖掘工具是否符合要求
		// tool.getHarvestLevel 用于获取工具的挖掘等级
//		// if (tool.getHarvestLevel(blockState.getHarvestTool(), null, null) < blockState.getHarvestLevel()) {
//			return EMPTY_ITEM_STACK_LIST;
//		}

		List<ItemStack> itemStacks = new ArrayList<>();
		itemStacks.add(new ItemStack(item, this.dropItemCount));

		return itemStacks;
	}
}
