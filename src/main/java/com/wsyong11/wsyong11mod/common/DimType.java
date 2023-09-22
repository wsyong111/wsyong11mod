package com.wsyong11.wsyong11mod.common;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public enum DimType {
    OVERWORLD("minecraft:overworld"),
    NETHER("minecraft:nether"),
    THE_END("minecraft:the_end");

    private final ResourceLocation resourceLocation;

    DimType(@Nonnull String name) {
        this.resourceLocation = new ResourceLocation(name);
    }

    String getDimName() {
        return this.resourceLocation.toString();
    }

    ResourceLocation getResourceLocation() {
        return this.resourceLocation;
    }

    boolean equals(@Nonnull String namespace) {
        return this.resourceLocation.equals(new ResourceLocation(namespace));
    }

    boolean equals(@Nonnull ResourceLocation resourceLocation) {
        return this.resourceLocation.equals(resourceLocation);
    }
}
