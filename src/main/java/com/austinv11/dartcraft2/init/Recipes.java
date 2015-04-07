package com.austinv11.dartcraft2.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Recipes {
	
	public static void init() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.clipboard), "pip", "pap", "pap", 'p', "plankWood", 'i', "ingotIron", 'a', new ItemStack(Items.paper)));
	}
}
