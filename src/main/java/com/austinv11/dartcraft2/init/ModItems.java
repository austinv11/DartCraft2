package com.austinv11.dartcraft2.init;

import com.austinv11.dartcraft2.events.handlers.BucketHandler;
import com.austinv11.dartcraft2.items.*;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	
	public static final ItemDC forceGem = new ItemForceGem();
	public static final Item liquidForceBucket = new ItemLiquidForceBucket(ModFluids.liquidForce);
	public static final ItemDC clipboard = new ItemClipboard();
	public static final ItemDC forceIngot = new ItemForceIngot();
	public static final ItemDC forceNugget = new ItemForceNugget();
	public static final ItemDC forceStick = new ItemForceStick();
    public static final ItemDC forceRod = new ItemForceRod();
    public static final ItemDC forceBelt = new ItemForceBelt();
	
	public static void init() {
		GameRegistry.registerItem(forceGem, "forceGem");
		GameRegistry.registerItem(liquidForceBucket, "liquidForceBucket");
		FluidContainerRegistry.registerFluidContainer(ModFluids.liquidForce, new ItemStack(liquidForceBucket), new ItemStack(Items.bucket));
		BucketHandler.buckets.put(ModBlocks.liquidForce, liquidForceBucket);
		GameRegistry.registerItem(clipboard, "clipboard");
		GameRegistry.registerItem(forceIngot, "forceIngot");
		GameRegistry.registerItem(forceNugget, "forceNugget");
        GameRegistry.registerItem(forceRod, "forceRod");
		GameRegistry.registerItem(forceStick, "forceStick");
        GameRegistry.registerItem(forceBelt, "forceBelt");

	}
}
