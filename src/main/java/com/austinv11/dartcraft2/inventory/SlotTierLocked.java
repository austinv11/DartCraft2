package com.austinv11.dartcraft2.inventory;

import com.austinv11.collectiveframework.minecraft.inventory.ExclusiveSlot;
import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.tileentities.TileEntityInfuser;
import net.minecraft.item.ItemStack;

public class SlotTierLocked extends ExclusiveSlot {
	
	private int tier = 0;
	private TileEntityInfuser tile;
	
	public SlotTierLocked(TileEntityInfuser inv, int index, int xPos, int yPos) {
		super(inv, index, xPos, yPos);
		setExclusive(DartCraft2.UPGRADE_REGISTRY.getRegisteredItems());
		setWhitelist(true);
		tile = inv;
	}
	
	public SlotTierLocked setTier(int tier) {
		this.tier = tier;
		return this;
	}
	
	@Override
	public boolean isItemValid(ItemStack itemStack) {
		if (tier > tile.getTier())
			return false;
			
		return super.isItemValid(itemStack);
	}
}
