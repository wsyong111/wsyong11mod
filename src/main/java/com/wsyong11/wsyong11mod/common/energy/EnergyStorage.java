package com.wsyong11.wsyong11mod.common.energy;

import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

/**
 * 能量储存类的通用实现
 */
public class EnergyStorage implements IEnergyStorage {
	private int storageEnergy;
	private int capacity;
	private int maxReceive;
	private int maxExtract;

	private Boolean canExtract;
	private Boolean canReceive;

	/**
	 * 默认构造函数
	 *
	 * @param capacity      最大容量, 当传入值低于 0 时容量为无限 (-2^31 到 2^31-1)
	 * @param maxReceive    输入能量的最大速度 (每 tick)
	 * @param maxExtract    输出能量的最大速度 (每 tick)
	 * @param storageEnergy 默认储存的能量, 不得小于最大容量, 否则将引发 IllegalArgumentException 错误
	 * @throws IllegalArgumentException 当默认储存的能量大于最大容量时引发此错误
	 */
	public EnergyStorage(int capacity, int maxReceive, int maxExtract, int storageEnergy) {
		// 如果 capacity 大于 0 就检测 storageEnergy 是否大于 capacity
		if (capacity > 0 && storageEnergy > capacity) {
			throw new IllegalArgumentException("Storage energy cannot exceed capacity");
		}

		if (storageEnergy < 0) {
			throw new IllegalArgumentException("storageEnergy value is lower than 0");
		}

		this.storageEnergy = storageEnergy;
		this.capacity = capacity < 0 ? -1 : capacity; // 当最大容量低于 0 时改成 -1 以表示容量无限
		this.maxReceive = maxReceive < 0 ? -1 : maxReceive;
		this.maxExtract = maxExtract < 0 ? -1 : maxExtract;

		this.canExtract = null;
		this.canReceive = null;
	}

	/**
	 * 无参数构造函数
	 * <p>
	 * 容量 -1, 最大输入 128FE/t, 最大输出 128FE/t, 能量储存 0
	 *
	 * @see EnergyStorage#EnergyStorage(int, int, int, int)
	 */
	public EnergyStorage() {
		this(-1, 128, 128, 0);
	}

	/**
	 * 构造函数 (无初始能量)
	 *
	 * @see EnergyStorage#EnergyStorage(int, int, int, int)
	 */
	public EnergyStorage(int capacity, int maxReceive, int maxExtract) {
		this(capacity, maxReceive, maxExtract, 0);
	}

	/**
	 * 设定最大输入速度 (每 tick)
	 */
	public void setMaxReceive(int maxReceive) {
		this.maxReceive = maxReceive < 0 ? -1 : maxReceive;
	}

	/**
	 * 设定最大输出速度 (每 tick)
	 */
	public void setMaxExtract(int maxExtract) {
		this.maxExtract = maxExtract < 0 ? -1 : maxExtract;
	}

	/**
	 * 设定能量储量
	 */
	public void setStorageEnergy(int storageEnergy) {
		// 如果 capacity 大于 0 就检测 storageEnergy 是否大于 capacity
		if (this.capacity > 0 && storageEnergy > this.capacity) {
			throw new IllegalArgumentException("Storage energy cannot exceed capacity");
		}

		if (storageEnergy < 0) {
			throw new IllegalArgumentException("storageEnergy value is lower than 0");
		}

		this.storageEnergy = storageEnergy;
	}

	/**
	 * 设定最大能量储量
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity < 0 ? -1 : capacity;
	}

	/**
	 * 是否能输出能量, 如果传入 null 则判定最大输出能量是否为 -1
	 */
	public void setCanExtract(@Nullable Boolean canExtract) {
		this.canExtract = canExtract;
	}

	/**
	 * 是否输入能量, 如果传入 null 则判定最输入能量是否为 -1
	 */
	public void setCanReceive(@Nullable Boolean canReceive) {
		this.canReceive = canReceive;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		// 当 maxReceive 为 -1 则用最快的速度接收能量
		if (this.maxReceive == -1) {
			if (!simulate) {
				this.storageEnergy += maxReceive;
			}

			return maxReceive;
		}

		int energyReceived = Math.min(this.capacity - this.storageEnergy, Math.min(this.maxReceive, maxReceive));

		if (!simulate) {
			this.storageEnergy += energyReceived;
		}

		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		// 当 maxExtract 为 -1 则用最快的速度接收能量
		if (this.maxExtract == -1) {
			if (!simulate) {
				this.storageEnergy -= maxExtract;
			}

			return maxExtract;
		}

		int energyExtracted = Math.min(this.capacity - this.storageEnergy, Math.min(this.maxExtract, maxExtract));

		if (!simulate) {
			this.storageEnergy -= energyExtracted;
		}

		return energyExtracted;
	}

	@Override
	public int getEnergyStored() {
		return this.storageEnergy;
	}

	@Override
	public int getMaxEnergyStored() {
		return this.capacity == -1 ? Integer.MAX_VALUE : this.capacity;
	}

	@Override
	public boolean canExtract() {
		return this.canExtract == null ? this.maxExtract != 0 : this.canExtract;
	}

	@Override
	public boolean canReceive() {
		return this.canReceive == null ? this.maxReceive != 0 : this.canReceive;
	}
}
