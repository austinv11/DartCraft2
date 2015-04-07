package com.austinv11.dartcraft2.container;

import com.austinv11.dartcraft2.items.ItemClipboard;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class ContainerClipboard extends ContainerWorkbench {
	
	public ContainerClipboard(EntityPlayer player) {
		this(player.inventory, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
	}
	
	protected ContainerClipboard(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		super(inventoryPlayer, world, x, y, z);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		if (player.getHeldItem() != null)
			if (player.getHeldItem().getItem() instanceof ItemClipboard)
				return true;
		return false;
	}
}
