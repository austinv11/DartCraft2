package com.austinv11.dartcraft2.tileentities;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;

public class TileEntityInfuser extends TileEntity {
	
	public TileEntityEnchantmentTable dummyTable;
	public static String publicName = "infuser";
	
	@Override
	public void validate() {
		dummyTable = new TileEntityEnchantmentTable();
		dummyTable.setWorldObj(this.getWorldObj());
		dummyTable.xCoord = this.xCoord;
		dummyTable.yCoord = this.yCoord;
		dummyTable.zCoord = this.zCoord;
		dummyTable.blockMetadata = Integer.MIN_VALUE;
	}
	
	@Override
	public void updateEntity() {
		if (dummyTable != null)
			dummyTable.updateEntity();
	}
}
