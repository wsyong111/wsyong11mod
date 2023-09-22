package com.wsyong11.wsyong11mod.item.items;

import com.wsyong11.wsyong11mod.common.TNTExplosion;
import com.wsyong11.wsyong11mod.item.ItemGroup;
import com.wsyong11.wsyong11mod.item.ItemRegister;
import com.wsyong11.wsyong11mod.item.items.base.ItemBase;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_ID;

/**
 * 藏了 TNT 的面包, 吃了会爆炸
 */
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TNTBread extends ItemBase {
	private static final Food food = new Food.Builder()
		.nutrition(5) // 饱食度为5
		.saturationMod(6.0F) // 饥饿饱和度为2.0
		.build();

	public TNTBread() {
		super(
			new Properties()
				.tab(ItemGroup.foodItemGroup)
				.food(food)
		);
	}

	@SubscribeEvent
	public static void onItemConsumed(LivingEntityUseItemEvent.Finish event) {
		LivingEntity entity = event.getEntityLiving();

		// 如果实体是玩家
		if (entity instanceof PlayerEntity) {
			// 如果是服务器
			if (!event.getEntityLiving().level.isClientSide()) {
				ItemStack usedItem = event.getItem();

				// 如果玩家拿着 tnt_bread
				if (usedItem.getItem() == ItemRegister.tnt_bread.get()) {
					// 默认爆炸威力为 4.0
					float level = 4.0F;

					CompoundNBT nbtData = usedItem.getTag();
					if (nbtData != null) {
						// 获取爆炸威力
						if (nbtData.contains("level")) {
							level = nbtData.getFloat("level");
						}
					}

					TNTExplosion explosion = new TNTExplosion(
						entity.level,
						null,
						entity.getX(),
						entity.getY(),
						entity.getZ(),
						level,
						false,
						Explosion.Mode.BREAK
					);

					explosion.explode();
				}
			}
		}
	}
}
