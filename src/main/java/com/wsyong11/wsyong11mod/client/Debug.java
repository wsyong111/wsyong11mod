package com.wsyong11.wsyong11mod.client;

import com.wsyong11.wsyong11mod.common.ThisModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.Collection;
import java.util.List;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_ID;

/**
 * 这个类是用于除错的类, 提供了显示物品标签列表的功能
 * <p>
 * 在配置文件中 debugMode 为 false 时这个类不会被运行
 */
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class Debug {
	private static final Minecraft MINECRAFT = Minecraft.getInstance();

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onItemTooltip(ItemTooltipEvent event) {
		if (isDebugMode() && ThisModConfig.Debug.debugTagTooltip.get()) {
			if (shiftPressed()) {
				ItemStack itemStack = event.getItemStack();
				List<ITextComponent> toolTip = event.getToolTip();

				if (!itemStack.isEmpty()) {
					Collection<ResourceLocation> tags = itemStack.getItem().getTags();

					if (tags.isEmpty()) {
						toolTip.add(new StringTextComponent("Tags: []"));
					} else {
						toolTip.add(new StringTextComponent("Tags: " + tags));
					}
				}
			}
		}
	}

	public static boolean shiftPressed() {
		long window = MINECRAFT.getWindow().getWindow();

		return InputMappings.isKeyDown(
			window,
			GLFW.GLFW_KEY_LEFT_SHIFT
		) || InputMappings.isKeyDown(
			window,
			GLFW.GLFW_KEY_RIGHT_SHIFT
		);
	}

	private static boolean isDebugMode() {
		return ThisModConfig.Debug.debugMode.get();
	}

	private Debug() {
	}
}
