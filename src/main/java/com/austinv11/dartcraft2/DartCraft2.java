package com.austinv11.dartcraft2;

import com.austinv11.collectiveframework.minecraft.config.ConfigException;
import com.austinv11.collectiveframework.minecraft.config.ConfigRegistry;
import com.austinv11.collectiveframework.minecraft.logging.Logger;
import com.austinv11.dartcraft2.init.ModBlocks;
import com.austinv11.dartcraft2.init.ModFluids;
import com.austinv11.dartcraft2.init.ModItems;
import com.austinv11.dartcraft2.proxy.CommonProxy;
import com.austinv11.dartcraft2.reference.Config;
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

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "after:CollectiveFramework")
public class DartCraft2 {
	
	public static SimpleNetworkWrapper NETWORK;
	
	public static Logger LOGGER = new Logger(Reference.MOD_NAME);
	
	@Mod.Instance(Reference.MOD_ID)
	public static DartCraft2 instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		try {
			ConfigRegistry.registerConfig(new Config());
		} catch (ConfigException e) {
			e.printStackTrace();
		}
		NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("dartcraft2");
		GameRegistry.registerWorldGenerator(new WorldGenPowerOre(), 1);
		ModFluids.init();
		ModBlocks.init();
		ModItems.init();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerEvents();
		proxy.registerClient();
		proxy.registerOreDictEntries();
		proxy.registerTileEntities();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
