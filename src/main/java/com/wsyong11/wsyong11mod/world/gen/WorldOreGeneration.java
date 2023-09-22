package com.wsyong11.wsyong11mod.world.gen;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import javax.annotation.Nonnull;

/**
 * 这个类用于从 OreType 中获取和注册矿石
 */
public class WorldOreGeneration {
	public static void generateOres(@Nonnull BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();

		for (OreType oreType : OreType.values()) {
			// 生成配置文件
			// 参数1: 矿石周围的填充类型
			// 参数2: 方块 (方块状态)
			// 参数3: 矿脉大小
			OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(
				OreFeatureConfig.FillerBlockType.NATURAL_STONE,
				oreType.getBlock().defaultBlockState(),
				oreType.getMaxVeinSize()
			);

			ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configured(
				new TopSolidRangeConfig(
					oreType.getMinHeight(),
					oreType.getMinHeight(),
					oreType.getMaxHeight()
				)
			);

			ConfiguredFeature<?, ?> oreConfiguredFeature = registerOreFeature(oreType, oreFeatureConfig, configuredPlacement);

			generation.addFeature(
				GenerationStage.Decoration.UNDERGROUND_ORES,
				oreConfiguredFeature
			);
		}
	}

	@Nonnull
	public static ConfiguredFeature<?, ?> registerOreFeature(@Nonnull OreType oreType, @Nonnull OreFeatureConfig oreFeatureConfig, @Nonnull ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement) {
		ResourceLocation blockRegisterName = oreType.getBlock().getRegistryName();

		Block block = oreType.getBlock();

		if (blockRegisterName == null) {
			// 获取类的完整包名
			String className = block.getClass().getName();

			throw new RuntimeException("Cannot get block register name for " + className);
		}

		return Registry.register(
			WorldGenRegistries.CONFIGURED_FEATURE,
			blockRegisterName,
			Feature.ORE.configured(oreFeatureConfig)
				.decorated(configuredPlacement)
				.squared()
				.count(oreType.getMaxVeinSize())
		);
	}

	private WorldOreGeneration() {
	}
}
