package com.austinv11.dartcraft2.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes {
	
	public static void init() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.clipboard), "pip", "pap", "pap", 'p', "plankWood", 'i', "ingotIron", 'a', new ItemStack(Items.paper)));
		ItemStack twoIngots = new ItemStack(ModItems.forceIngot);
		twoIngots.stackSize = 2;
		ItemStack threeIngots = twoIngots.copy();
		threeIngots.stackSize = 3;
		GameRegistry.addRecipe(new ShapelessOreRecipe(twoIngots, "gemForce", "ingotIron", "ingotIron"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(threeIngots, "gemForce", "ingotGold", "ingotGold"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(twoIngots, "gemForce", "ingotBronze", "ingotBronze"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(threeIngots, "gemForce", "ingotRefinedIron", "ingotRefinedIron"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(threeIngots, "gemForce", "ingotSilver", "ingotSilver"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(threeIngots, "gemForce", "ingotSteel", "ingotSteel"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(twoIngots, "gemForce", "ingotIron", "ingotIron"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(threeIngots, "gemForce", "ingotGold", "ingotGold"));
		//TODO: Mob ingot recipes for force ingots
	}
}
