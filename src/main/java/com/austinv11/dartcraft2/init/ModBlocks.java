package com.austinv11.dartcraft2.init;

import com.austinv11.dartcraft2.blocks.*;
import com.austinv11.dartcraft2.items.ItemBlockForceLeaves;
import com.austinv11.dartcraft2.items.ItemBlockForceLog;
import com.austinv11.dartcraft2.items.ItemBlockForceSapling;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
	
	public static final BlockDC powerOre = new BlockPowerOre();
	public static final BlockDC netherPowerOre = new BlockNetherPowerOre();
	public static Block liquidForce;
	public static final Block forceSapling = new BlockForceSapling();
	public static final Block forceLog = new BlockForceLog();
	public static final Block forceLog2 = new BlockForceLog2();
	public static final Block forceLeaves = new BlockForceLeaves();
	public static final Block forceLeaves2 = new BlockForceLeaves2();
	public static final BlockDC forcePlanks = new BlockForcePlanks();
	
	public static void init() {
		GameRegistry.registerBlock(powerOre, "powerOre");
		GameRegistry.registerBlock(netherPowerOre, "netherPowerOre");
		GameRegistry.registerBlock(forceSapling, ItemBlockForceSapling.class, "forceSapling");
		GameRegistry.registerBlock(forceLog, ItemBlockForceLog.class, "forceLog");
		GameRegistry.registerBlock(forceLog2, ItemBlockForceLog.class, "forceLog2");
		GameRegistry.registerBlock(forceLeaves, ItemBlockForceLeaves.class, "forceLeaves");
		GameRegistry.registerBlock(forceLeaves2, ItemBlockForceLeaves.class, "forceLeaves2");
		GameRegistry.registerBlock(forcePlanks, "forcePlanks");
	}
}
