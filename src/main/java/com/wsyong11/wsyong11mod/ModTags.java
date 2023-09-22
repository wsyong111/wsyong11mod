package com.wsyong11.wsyong11mod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_ID;

public class ModTags {
	public static class Items {
		@Nonnull
		private static Tags.IOptionalNamedTag<Item> tag(String name) {
			return ItemTags.createOptional(new ResourceLocation(MOD_ID, name));
		}

		@Nonnull
		private static Tags.IOptionalNamedTag<Item> forgeTag(String name) {
			return ItemTags.createOptional(new ResourceLocation("forge", name));
		}

		private Items() {
		}
	}

	public static class Blocks {
		@Nonnull
		private static Tags.IOptionalNamedTag<Block> tag(String name) {
			return BlockTags.createOptional(new ResourceLocation(MOD_ID, name));
		}

		@Nonnull
		private static Tags.IOptionalNamedTag<Block> forgeTag(String name) {
			return BlockTags.createOptional(new ResourceLocation("forge", name));
		}

		private Blocks() {
		}
	}

	private ModTags() {
	}
}
