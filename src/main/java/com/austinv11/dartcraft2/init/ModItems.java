package com.austinv11.dartcraft2.init;

import com.austinv11.dartcraft2.items.ItemDC;
import com.austinv11.dartcraft2.items.ItemForceShard;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(value = Reference.MOD_ID)
public class ModItems {
	
	public static final ItemDC forceShard = new ItemForceShard();
	
	public static void init() {
		GameRegistry.registerItem(forceShard, "forceShard");
	}
}
