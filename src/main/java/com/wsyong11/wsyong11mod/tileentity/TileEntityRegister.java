package com.wsyong11.wsyong11mod.tileentity;

import com.wsyong11.wsyong11mod.tileentity.tileentitys.CoalGeneratorTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_ID;

public class TileEntityRegister {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

	public static final RegistryObject<TileEntityType<CoalGeneratorTile>> coal_generator =
		register(
			"coal_generator_tile",
			CoalGeneratorTile::new
		);

	@SuppressWarnings("ALL")
	public static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(@Nonnull String name, Supplier<T> factory) {
		TileEntityType.Builder<T> builder = TileEntityType.Builder.of(factory);

		return TILE_ENTITY_TYPE.register(
			name,
			() -> builder.build(null)
		);
	}

	public static void register(IEventBus eventBus) {
		TILE_ENTITY_TYPE.register(eventBus);
	}

	private TileEntityRegister() {
	}
}
