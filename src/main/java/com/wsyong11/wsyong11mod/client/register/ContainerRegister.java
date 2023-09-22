package com.wsyong11.wsyong11mod.client.register;

import com.wsyong11.wsyong11mod.client.gui.coalgenerator.CoalGeneratorContainer;
import com.wsyong11.wsyong11mod.tileentity.tileentitys.CoalGeneratorTile;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.wsyong11.wsyong11mod.StaticValues.MOD_ID;

public class ContainerRegister {
	private static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD_ID);

//	public static final RegistryObject<ContainerType<CoalGeneratorContainer>> coal_generator_container = CONTAINER_TYPES.register(
//		"coal_generator_container",
//		() -> IForgeContainerType.create(
//			(windowId, inv, data) -> new CoalGeneratorContainer(inv, windowId, new CoalGeneratorTile())
//		)
//	);

	public static final RegistryObject<ContainerType<CoalGeneratorContainer>> coal_generator_container = register(
		"coal_generator_container",
		(windowId, inventory, data) -> new CoalGeneratorContainer(windowId, inventory, new CoalGeneratorTile())
	);

	private static <T extends Container> RegistryObject<ContainerType<T>> register(String name, IContainerFactory<T> containerBuilderMethod) {
		return CONTAINER_TYPES.register(
			name,
			() -> IForgeContainerType.create(containerBuilderMethod)
		);
	}

	public static void register(IEventBus eventBus) {
		CONTAINER_TYPES.register(eventBus);
	}

	private ContainerRegister() {
	}
}
