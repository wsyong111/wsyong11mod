package com.wsyong11.wsyong11mod.item.items.base;

import com.wsyong11.wsyong11mod.common.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_LOGGER;

/**
 * 所有物品的基类
 */
public class ItemBase extends Item {
	public ItemBase(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	@ParametersAreNonnullByDefault
	public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> textComponentList, ITooltipFlag tooltipFlag) {
		super.appendHoverText(itemStack, world, textComponentList, tooltipFlag);

		ResourceLocation itemID = this.getRegistryName();

		if (itemID != null) {
			textComponentList.addAll(
				Utils.getItemTooltipList(Utils.ObjectType.ITEM, itemID.toString())
			);
		} else {
			MOD_LOGGER.debug("[{}] Cannot get item ResourceLocation", this);
		}
	}
}
