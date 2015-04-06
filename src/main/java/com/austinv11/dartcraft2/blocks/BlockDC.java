package com.austinv11.dartcraft2.blocks;

import com.austinv11.collectiveframework.minecraft.blocks.BlockBase;
import com.austinv11.dartcraft2.creativetab.CreativeTabDC;
import com.austinv11.dartcraft2.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;

public class BlockDC extends BlockBase {
	
	public BlockDC() {
		super();
	}
	
	@Override
	public CreativeTabs getTab() {
		return CreativeTabDC.DC_TAB;
	}
	
	@Override
	public String getModId() {
		return Reference.MOD_ID;
	}
}
