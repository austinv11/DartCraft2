package com.austinv11.dartcraft2.init;

import com.austinv11.dartcraft2.events.handlers.BucketHandler;
import com.austinv11.dartcraft2.items.ItemClipboard;
import com.austinv11.dartcraft2.items.ItemDC;
import com.austinv11.dartcraft2.items.ItemForceShard;
import com.austinv11.dartcraft2.items.ItemLiquidForceBucket;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	
	public static final ItemDC forceShard = new ItemForceShard();
	public static final Item liquidForceBucket = new ItemLiquidForceBucket(ModFluids.liquidForce);
	public static final ItemDC clipboard = new ItemClipboard();
	
	public static void init() {
		GameRegistry.registerItem(forceShard, "forceShard");
		GameRegistry.registerItem(liquidForceBucket, "liquidForceBucket");
		FluidContainerRegistry.registerFluidContainer(ModFluids.liquidForce, new ItemStack(liquidForceBucket), new ItemStack(Items.bucket));
		BucketHandler.buckets.put(ModBlocks.liquidForce, liquidForceBucket);
		GameRegistry.registerItem(clipboard, "clipboard");
	}
}
