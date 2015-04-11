package com.austinv11.dartcraft2;

import com.austinv11.collectiveframework.minecraft.config.ConfigException;
import com.austinv11.collectiveframework.minecraft.config.ConfigRegistry;
import com.austinv11.collectiveframework.minecraft.logging.Logger;
import com.austinv11.dartcraft2.api.FailedAPIRequest;
import com.austinv11.dartcraft2.api.ITransmutationRecipeHandler;
import com.austinv11.dartcraft2.api.implementations.TransmutationRecipeHandler;
import com.austinv11.dartcraft2.client.gui.GuiHandler;
import com.austinv11.dartcraft2.init.ModBlocks;
import com.austinv11.dartcraft2.init.ModFluids;
import com.austinv11.dartcraft2.init.ModItems;
import com.austinv11.dartcraft2.init.Recipes;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "after:CollectiveFramework")
public class DartCraft2 {
	
	public static SimpleNetworkWrapper NETWORK;
	
	public static Logger LOGGER = new Logger(Reference.MOD_NAME);
	
	public static ITransmutationRecipeHandler TRANSMUTATION_HANDLER = new TransmutationRecipeHandler();
	
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
		proxy.registerPackets();
		GameRegistry.registerWorldGenerator(new WorldGenPowerOre(), 1);
		ModFluids.init();
		ModBlocks.init();
		ModItems.init();
		try {
			prepareAPI();
		} catch (FailedAPIRequest failedAPIRequest) {
			failedAPIRequest.printStackTrace();
			LOGGER.warn("Did another mod mess with the API?");
		}
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		proxy.registerEvents();
		proxy.registerClient();
		proxy.registerOreDictEntries();
		proxy.registerTileEntities();
        proxy.handleKeyBindings();
		Recipes.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	private void prepareAPI() throws FailedAPIRequest {
		//Transmutation recipes
		for (int i = 0; i < 6; i++)
			TRANSMUTATION_HANDLER.addTransmutation(Blocks.sapling, i, ModBlocks.forceSapling, i);
		TRANSMUTATION_HANDLER.addTransmutation(Blocks.enchanting_table, ModBlocks.infuser);
		TRANSMUTATION_HANDLER.addTransmutation(Items.book, ModItems.upgradeTome);
	}
}
