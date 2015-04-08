package com.austinv11.dartcraft2.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityForceSapling extends TileEntity {
	
	public static final int MAX_CYCLES = 5;
	public static String publicName = "forceSapling";
	public int currentCycle = 0;
	
	@Override
	public boolean canUpdate() {
		return false;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		currentCycle = tagCompound.getInteger("currentCycle");
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("currentCycle", currentCycle);
	}
}
