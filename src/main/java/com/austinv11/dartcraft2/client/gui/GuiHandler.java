package com.austinv11.dartcraft2.client.gui;

import com.austinv11.dartcraft2.container.ContainerClipboard;
import com.austinv11.dartcraft2.container.ContainerForceBelt;
import com.austinv11.dartcraft2.container.ContainerForcePack;
import com.austinv11.dartcraft2.container.ContainerInfuser;
import com.austinv11.dartcraft2.reference.Reference;
import com.austinv11.dartcraft2.tileentities.TileEntityInfuser;
import com.austinv11.dartcraft2.utils.DartCraftUtils;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUIs.CLIPBOARD.ordinal())
			return new ContainerClipboard(player);
        else if (ID == Reference.GUIs.FORCE_BELT.ordinal())
            return new ContainerForceBelt(player, 176, 137);
        else if (ID == Reference.GUIs.FORCE_PACK.ordinal())
            return new ContainerForcePack(player, DartCraftUtils.getForcePackGuiYSize(player.getHeldItem().getItemDamage()), 140);
		else if (ID == Reference.GUIs.INFUSER.ordinal())
			return new ContainerInfuser((TileEntityInfuser) world.getTileEntity(x, y, z), player, 176, 200);
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUIs.CLIPBOARD.ordinal())
			return new GuiClipboard(player);
        else if (ID == Reference.GUIs.FORCE_BELT.ordinal())
            return new GuiForceBelt(player);
        else if (ID == Reference.GUIs.FORCE_PACK.ordinal())
            return new GuiForcePack(player);
		else if (ID == Reference.GUIs.INFUSER.ordinal())
			return new GuiInfuser(player, world, x, y, z);
		return null;
	}
}
