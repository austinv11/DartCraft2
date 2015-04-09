package com.austinv11.dartcraft2.events.handlers;

import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.api.DartCraft2API;
import com.austinv11.dartcraft2.api.FailedAPIRequest;
import com.austinv11.dartcraft2.api.ITransmutationItem;
import com.austinv11.dartcraft2.api.ITransmutationRecipeHandler;
import com.austinv11.dartcraft2.network.DartCraftEffectPacket;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class TransmutationHandler {
	
	@SubscribeEvent
	public void onPlayerRightClick(PlayerInteractEvent event) throws FailedAPIRequest {
		if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
			if (!event.isCanceled())
				if (event.entityPlayer.getCurrentEquippedItem() != null)
					if (event.entityPlayer.getCurrentEquippedItem().getItem() instanceof ITransmutationItem) {
						ITransmutationRecipeHandler.BlockInfo toTransmute = DartCraft2API.getTransmutationRecipeHandler().getAvailableTransmutation(
								event.world.getBlock(event.x, event.y, event.z), event.world.getBlockMetadata(event.x, event.y, event.z));
						if (toTransmute != null)
							if (((ITransmutationItem) event.entityPlayer.getCurrentEquippedItem().getItem()).canTransmute(
								event.world, event.x, event.y, event.z, event.entityPlayer.getCurrentEquippedItem(), toTransmute.block, toTransmute.meta)) {
								((ITransmutationItem) event.entityPlayer.getCurrentEquippedItem().getItem()).transmute(
										event.world, event.x, event.y, event.z, event.entityPlayer.getCurrentEquippedItem(), toTransmute.block, toTransmute.meta);
								event.world.setBlock(event.x, event.y, event.z, toTransmute.block, toTransmute.meta, 3);
								if (!event.world.isRemote)
									DartCraft2.NETWORK.sendToAll(new DartCraftEffectPacket(event.world, event.x, event.y, event.z));
							}
					}
	}
	
	@SubscribeEvent
	public void onCraft(PlayerEvent.ItemCraftedEvent event) {
		if (event.crafting != null) {
			ItemStack rod = null;
			ItemStack toTransmute = null;
			ItemStack transmuted = event.crafting;
			for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++)
				if (event.craftMatrix.getStackInSlot(i) != null)
					if (event.craftMatrix.getStackInSlot(i).getItem() instanceof ITransmutationItem) {
						rod = event.craftMatrix.getStackInSlot(i).copy();
					} else {
						if (toTransmute != null)
							return;
						toTransmute = event.craftMatrix.getStackInSlot(i);
					}
			if (rod != null && toTransmute != null && transmuted != null) {
				((ITransmutationItem)rod.getItem()).transmute(rod, toTransmute.getItem(), toTransmute.getItemDamage(), 
						transmuted.getItem(), transmuted.getItemDamage());
				event.player.inventory.addItemStackToInventory(rod);
				if (!event.player.getEntityWorld().isRemote)
					DartCraft2.NETWORK.sendToAll(new DartCraftEffectPacket(event.player.getEntityWorld(), (int) event.player.posX, (int) event.player.posY, (int) event.player.posZ));
			}
		}
	}
}
