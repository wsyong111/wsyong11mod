package com.wsyong11.wsyong11mod.common;

import net.minecraft.entity.Entity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_LOGGER;

/**
 * 类似 TNT 爆炸的实现 (包含随机爆炸威力)
 */
public class TNTExplosion {
	private static final Random random = new Random();

	private final World world;
	@Nullable
	private final Entity source;
	private final double posX;
	private final double posY;
	private final double posZ;
	private final float power;
	private final boolean causesFire;
	private final Explosion.Mode mode;

	/**
	 * 默认构造函数
	 *
	 * @param world      爆炸所在的维度
	 * @param source     爆炸原
	 * @param posX       位置 X
	 * @param posY       位置 Y
	 * @param posZ       位置 Z
	 * @param power      爆炸威力
	 * @param causesFire 是否产生火焰
	 * @param mode       爆炸模式
	 */
	public TNTExplosion(World world, @Nullable Entity source, double posX, double posY, double posZ, float power, boolean causesFire, Explosion.Mode mode) {
		this.world = world;
		this.source = source;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.power = power;
		this.causesFire = causesFire;
		this.mode = mode;
	}

	public void explode() {
		MOD_LOGGER.debug(
			"Create explosion at ({}, {}, {})",
			String.format("%.4f", this.posX),
			String.format("%.4f", this.posY),
			String.format("%.4f", this.posZ)
		);

		float power = (float) (this.power + random.nextDouble() * 1.5D);

		this.world.explode(
			this.source,
			this.posX,
			this.posY,
			this.posZ,
			power,
			this.causesFire,
			this.mode
		);
	}
}
