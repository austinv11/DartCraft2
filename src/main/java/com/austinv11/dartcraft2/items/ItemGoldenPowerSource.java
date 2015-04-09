package com.austinv11.dartcraft2.items;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

public class ItemGoldenPowerSource extends ItemDC implements IFuelHandler {
	
	public ItemGoldenPowerSource() {
		super();
		this.setUnlocalizedName("goldenPowerSource");
	}
	
	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.getItem() instanceof ItemGoldenPowerSource)
			return fuel.stackSize * 2000;
		return 0;
	}
}
