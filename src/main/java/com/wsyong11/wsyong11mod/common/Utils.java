package com.wsyong11.wsyong11mod.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 杂项静态函数的类
 */
public class Utils {
	/**
	 * 获取工具提示文本列表
	 * <p>
	 * 文本翻译路径: &lt;type&gt;.&lt;MOD_ID&gt;.&lt;id&gt;.description&lt;line&gt;
	 *
	 * @param type 类型
	 * @param id   id
	 * @return 工具提示文本列表
	 */
	@Nonnull
	public static List<TranslationTextComponent> getItemTooltipList(@Nonnull ObjectType type, @Nonnull String id) {
		Objects.requireNonNull(type, "type is null");
		Objects.requireNonNull(id, "id is null");

		int index = 0;

		String typeString;

		if (type == ObjectType.ITEM) {
			typeString = "item";
		} else {
			typeString = "?";
		}

		List<TranslationTextComponent> returnList = new ArrayList<>();

		String descriptionItemID = typeString + "." + id.replace(":", ".");

		while (true) {
			String descriptionID = descriptionItemID + ".description" + index;

			if (I18n.exists(descriptionID)) {
				returnList.add(new TranslationTextComponent(descriptionID));
			} else {
				break;
			}

			index++;
		}

		return returnList;
	}

	/**
	 * 返回方块是否是空气
	 *
	 * @param world 世界
	 * @param pos   位置
	 * @return 是否是空气
	 */
	public static boolean isAir(@Nonnull World world, @Nonnull BlockPos pos) {
		Objects.requireNonNull(world, "world is null");
		Objects.requireNonNull(pos, "pos is null");

		return world.isEmptyBlock(pos);
	}

	/**
	 * 从世界中摧毁方块
	 *
	 * @param world 目标世界
	 * @param pos   方块位置
	 */
	public static void destroyBlock(@Nonnull World world, @Nonnull BlockPos pos) {
		Objects.requireNonNull(world, "world is null");
		Objects.requireNonNull(pos, "pos is null");

		BlockState blockState = world.getBlockState(pos);

		if (isAir(world, pos)) {
			return;
		}

		TileEntity tileEntity = blockState.hasTileEntity() ? world.getBlockEntity(pos) : null;
		Block.dropResources(
			blockState,
			world,
			pos,
			tileEntity
		);
		world.removeBlock(pos, false);

		for (Direction direction : Direction.values()) {
			world.updateNeighborsAt(pos.relative(direction), blockState.getBlock());
		}
	}

	/**
	 * 创建给定长度的物品列表
	 */
	public static ItemStack[] createItemStacks(int size) {
		ItemStack[] items = new ItemStack[size];

		for (int i = 0; i < size; i++) {
			items[i] = ItemStack.EMPTY;
		}

		return items;
	}

	public enum ObjectType {
		ITEM
	}

	private Utils() {
	}
}
