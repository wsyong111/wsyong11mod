package com.wsyong11.wsyong11mod.world.gen;

import com.wsyong11.wsyong11mod.block.BlockRegister;
import com.wsyong11.wsyong11mod.common.DimType;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * 在这里注册的方块会在世界上作为矿石生成
 */
public enum OreType {
	TIN(BlockRegister.tin_ore, 0, 70, 6, 64);

	private final Lazy<Block> block;
	private final int minHeight;
	private final int maxHeight;
	private final int maxVeinSize;
	private final int rangeSize;
	private final List<DimType> dimList;

	/**
	 * @param blockGenerator 方块构造函数
	 * @param minHeight      最低生成高度
	 * @param maxHeight      最高生成高度
	 * @param maxVeinSize    最大矿脉大小
	 * @param rangeSize      稀有度 (值越大, 矿脉生成的高度范围就越广, 也就是说矿脉可以生成在更高和更低的地方。这会使矿脉变得更加常见, 因为它们可以在更多不同高度的区块中生成。)
	 * @param dimList        生成维度
	 */
	OreType(Supplier<Block> blockGenerator, int minHeight, int maxHeight, int maxVeinSize, int rangeSize, List<DimType> dimList) {
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
		this.maxVeinSize = maxVeinSize;
		this.rangeSize = rangeSize;
		this.dimList = dimList;
		this.block = Lazy.of(blockGenerator);
	}

	OreType(Supplier<Block> blockGenerator, int minHeight, int maxHeight, int maxVeinSize, int rangeSize, DimType... dimList) {
		this(blockGenerator, minHeight, maxHeight, maxVeinSize, rangeSize, Arrays.asList(dimList));
	}

	/**
	 * @param blockGenerator 方块构造函数
	 * @param minHeight      最低生成高度
	 * @param maxHeight      最高生成高度
	 * @param maxVeinSize    最大矿脉大小
	 */
	OreType(Supplier<Block> blockGenerator, int minHeight, int maxHeight, int maxVeinSize) {
		this(blockGenerator, minHeight, maxHeight, maxVeinSize, 0, Collections.singletonList(DimType.OVERWORLD));
	}

	/**
	 * @param blockGenerator 方块构造函数
	 * @param minHeight      最低生成高度
	 * @param maxHeight      最高生成高度
	 * @param maxVeinSize    最大矿脉大小
	 * @param rangeSize      稀有度 (值越大, 矿脉生成的高度范围就越广, 也就是说矿脉可以生成在更高和更低的地方。这会使矿脉变得更加常见, 因为它们可以在更多不同高度的区块中生成。)
	 */
	OreType(Supplier<Block> blockGenerator, int minHeight, int maxHeight, int maxVeinSize, int rangeSize) {
		this(blockGenerator, minHeight, maxHeight, maxVeinSize, rangeSize, Collections.singletonList(DimType.OVERWORLD));
	}

	public int getMaxHeight() {
		return this.maxHeight;
	}

	public int getMinHeight() {
		return this.minHeight;
	}

	public int getMaxVeinSize() {
		return this.maxVeinSize;
	}

	public int getRangeSize() {
		return this.rangeSize;
	}

	public boolean canSpawn(DimType dim) {
		for (DimType dimType : this.dimList) {
			if (dimType.equals(dim)) return true;
		}

		return false;
	}

	public Block getBlock() {
		return this.block.get();
	}
}
