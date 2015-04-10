package com.austinv11.dartcraft2.proxy;

import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.events.handlers.BucketHandler;
import com.austinv11.dartcraft2.events.handlers.TransmutationHandler;
import com.austinv11.dartcraft2.init.ModBlocks;
import com.austinv11.dartcraft2.init.ModItems;
import com.austinv11.dartcraft2.network.ClipboardButtonPressPacket;
import com.austinv11.dartcraft2.network.DartCraftEffectPacket;
import com.austinv11.dartcraft2.network.OpenGuiContainerPacket;
import com.austinv11.dartcraft2.tileentities.TileEntityForceSapling;
import com.austinv11.dartcraft2.tileentities.TileEntityInfuser;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy {
	
	public void registerClient() {
		
	}
	
	public void registerEvents() {
		MinecraftForge.EVENT_BUS.register(new BucketHandler());
		MinecraftForge.EVENT_BUS.register(new TransmutationHandler());
		FMLCommonHandler.instance().bus().register(new TransmutationHandler());
	}
	
	public void registerOreDictEntries() {
		OreDictionary.registerOre("orePower", ModBlocks.powerOre);
		OreDictionary.registerOre("orePower", ModBlocks.netherPowerOre);
		OreDictionary.registerOre("gemForce", ModItems.forceGem);
		OreDictionary.registerOre("ingotForce", ModItems.forceIngot);
		OreDictionary.registerOre("nuggetForce", ModItems.forceNugget);
		OreDictionary.registerOre("treeForce", new ItemStack(ModBlocks.forceSapling, 1, 0));
		OreDictionary.registerOre("treeForce", new ItemStack(ModBlocks.forceSapling, 1, 1));
		OreDictionary.registerOre("treeForce", new ItemStack(ModBlocks.forceSapling, 1, 2));
		OreDictionary.registerOre("treeForce", new ItemStack(ModBlocks.forceSapling, 1, 3));
		OreDictionary.registerOre("treeForce", new ItemStack(ModBlocks.forceSapling, 1, 4));
		OreDictionary.registerOre("treeForce", new ItemStack(ModBlocks.forceSapling, 1, 5));
		OreDictionary.registerOre("logForce", new ItemStack(ModBlocks.forceLog, 1, 0));
		OreDictionary.registerOre("logForce", new ItemStack(ModBlocks.forceLog, 1, 1));
		OreDictionary.registerOre("logForce", new ItemStack(ModBlocks.forceLog, 1, 2));
		OreDictionary.registerOre("logForce", new ItemStack(ModBlocks.forceLog, 1, 3));
		OreDictionary.registerOre("logForce", new ItemStack(ModBlocks.forceLog2, 1, 0));
		OreDictionary.registerOre("logForce", new ItemStack(ModBlocks.forceLog2, 1, 1));
		OreDictionary.registerOre("treeForceLeaves", new ItemStack(ModBlocks.forceLeaves, 1, 0));
		OreDictionary.registerOre("treeForceLeaves", new ItemStack(ModBlocks.forceLeaves, 1, 1));
		OreDictionary.registerOre("treeForceLeaves", new ItemStack(ModBlocks.forceLeaves, 1, 2));
		OreDictionary.registerOre("treeForceLeaves", new ItemStack(ModBlocks.forceLeaves, 1, 3));
		OreDictionary.registerOre("treeForceLeaves", new ItemStack(ModBlocks.forceLeaves2, 1, 0));
		OreDictionary.registerOre("treeForceLeaves", new ItemStack(ModBlocks.forceLeaves2, 1, 1));
		OreDictionary.registerOre("plankForce", ModBlocks.forcePlanks);
		OreDictionary.registerOre("stickForce", ModItems.forceStick);
	}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityForceSapling.class, TileEntityForceSapling.publicName);
		GameRegistry.registerTileEntity(TileEntityInfuser.class, TileEntityInfuser.publicName);
	}
	
	public void registerPackets() {
		DartCraft2.NETWORK.registerMessage(ClipboardButtonPressPacket.ClipboardButtonPressPacketHandler.class, ClipboardButtonPressPacket.class, 0, Side.SERVER);
		DartCraft2.NETWORK.registerMessage(DartCraftEffectPacket.DartCraftEffectPacketHandler.class, DartCraftEffectPacket.class, 1, Side.CLIENT);
        DartCraft2.NETWORK.registerMessage(OpenGuiContainerPacket.OpenGuiContainerPacketHandler.class, OpenGuiContainerPacket.class, 2, Side.SERVER);
	}

    public void handleKeyBindings() {
        // NO-OP
    }
}
