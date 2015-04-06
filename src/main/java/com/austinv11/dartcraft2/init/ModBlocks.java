package com.austinv11.dartcraft2.init;

import com.austinv11.dartcraft2.blocks.BlockDC;
import com.austinv11.dartcraft2.blocks.BlockPowerOre;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(value = Reference.MOD_ID)
public class ModBlocks {
	
	public static final BlockDC powerOre = new BlockPowerOre();
	
	public static void init() {
		GameRegistry.registerBlock(powerOre, "powerOre");
	}
}
