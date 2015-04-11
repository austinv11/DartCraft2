package com.austinv11.dartcraft2.events.handlers;

import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.api.ITransmutationItem;
import com.austinv11.dartcraft2.network.DartCraftEffectPacket;
import com.austinv11.dartcraft2.network.TransmutePacket;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class TransmutationHandler {
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.world.isRemote) {
			if (!event.isCanceled())
				if (event.entityPlayer.getCurrentEquippedItem() != null)
					if (event.entityPlayer.getCurrentEquippedItem().getItem() instanceof ITransmutationItem) {
						double posX = event.entityPlayer.posX;
						double posY = event.entityPlayer.posY + event.entityPlayer.getEyeHeight();
						double posZ = event.entityPlayer.posZ;
						double xIncr = -MathHelper.sin(event.entityPlayer.rotationYaw/180.0F*3.141593F) *
								MathHelper.cos(event.entityPlayer.rotationPitch/180.0F*3.141593F);
						double yIncr = -MathHelper.sin(event.entityPlayer.rotationPitch/180.0F*3.141593F);
						double zIncr = MathHelper.cos(event.entityPlayer.rotationYaw/180.0F*3.141593F) *
								MathHelper.cos(event.entityPlayer.rotationPitch/180.0F*3.141593F);
						for (int i = 0; i < 17; i++) {
							AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(posX-.5, posY-.5, posZ-.5, 
									posX+.5, posY+.5, posZ+.5);
							EntityItem entityItem = (EntityItem) event.world.findNearestEntityWithinAABB(EntityItem.class, boundingBox, event.entityPlayer);
							if (entityItem != null) {
								DartCraft2.NETWORK.sendToServer(new TransmutePacket(entityItem, event.entityPlayer));
								return;
							}
							posX += xIncr;
							posY += yIncr;
							posZ += zIncr;
						}
						if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
							DartCraft2.NETWORK.sendToServer(new TransmutePacket(event.world, event.x, event.y, event.z, event.entityPlayer));
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
