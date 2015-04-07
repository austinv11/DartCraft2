package com.austinv11.dartcraft2.client.gui;

import com.austinv11.dartcraft2.container.ContainerClipboard;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUIs.CLIPBOARD.ordinal())
			return new ContainerClipboard(player);
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUIs.CLIPBOARD.ordinal())
			return new GuiClipboard(player);
		return null;
	}
}
