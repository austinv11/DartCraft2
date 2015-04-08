package com.austinv11.dartcraft2.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockForceLeaves extends ItemBlock {
	
	public ItemBlockForceLeaves(Block block) {
		super(block);
		this.hasSubtypes = true;
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
}
