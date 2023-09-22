package com.wsyong11.wsyong11mod.common;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * 这个类用于控制模组的配置文件
 */
public class ThisModConfig {
	public static class Debug {
		public static ForgeConfigSpec.BooleanValue debugMode;
		public static ForgeConfigSpec.BooleanValue debugTagTooltip;
	}

	public ThisModConfig(ForgeConfigSpec.Builder builder) {
		builder.push("Main");

		builder.pop();

		// ---------------------------------------------------------------------------------------------------------- //

		builder.push("Debug");

		Debug.debugMode = builder
			.comment("Whether the mod enables debug mode")
			.define("debugMode", false);

		Debug.debugTagTooltip = builder
			.comment("Whether to enable item tag list")
			.define("debugTagTooltip", true);

		builder.pop();
	}
}
