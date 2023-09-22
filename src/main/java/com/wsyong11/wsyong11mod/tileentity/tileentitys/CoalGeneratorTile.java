package com.wsyong11.wsyong11mod.tileentity.tileentitys;

import com.wsyong11.wsyong11mod.tileentity.TileEntityRegister;
import com.wsyong11.wsyong11mod.tileentity.tileentitys.base.InventoryTileEntityBase;

public class CoalGeneratorTile extends InventoryTileEntityBase {
    public CoalGeneratorTile() {
        super(TileEntityRegister.coal_generator.get(), 1);
    }
}
