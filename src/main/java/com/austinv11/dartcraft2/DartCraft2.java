package com.austinv11.dartcraft2;

import com.austinv11.collectiveframework.minecraft.logging.Logger;
import com.austinv11.dartcraft2.init.ModBlocks;
import com.austinv11.dartcraft2.init.ModItems;
import com.austinv11.dartcraft2.proxy.CommonProxy;
import com.austinv11.dartcraft2.reference.Reference;
import com.austinv11.dartcraft2.worldgen.WorldGenPowerOre;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class DartCraft2 {
	
	public static SimpleNetworkWrapper NETWORK;
	
	public static Logger LOGGER = new Logger(Reference.MOD_NAME);
	
	@Mod.Instance(Reference.MOD_ID)
	public static DartCraft2 instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("dartcraft2");
		ModItems.init();
		ModBlocks.init();
		GameRegistry.registerWorldGenerator(new WorldGenPowerOre(), 0);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
