package com.austinv11.dartcraft2.client.gui;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;

public class GuiClipboard extends GuiCrafting {
	
	public GuiClipboard(EntityPlayer player) {
		this(player.inventory, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
	}
	
	protected GuiClipboard(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		super(inventoryPlayer, world, x, y, z);
	}
}
