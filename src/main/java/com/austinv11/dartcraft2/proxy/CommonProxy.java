package com.austinv11.dartcraft2.proxy;

import com.austinv11.dartcraft2.events.handlers.BucketHandler;
import com.austinv11.dartcraft2.init.ModBlocks;
import com.austinv11.dartcraft2.init.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy {
	
	public void registerClient() {
		
	}
	
	public void registerEvents() {
		MinecraftForge.EVENT_BUS.register(new BucketHandler());
	}
	
	public void registerOreDictEntries() {
		OreDictionary.registerOre("orePower", ModBlocks.powerOre);
		OreDictionary.registerOre("orePower", ModBlocks.netherPowerOre);
		OreDictionary.registerOre("gemForce", ModItems.forceShard);
		OreDictionary.registerOre("ingotForce", ModItems.forceIngot);
	}
	
	public void registerTileEntities() {
		
	}
}
