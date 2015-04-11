package com.austinv11.dartcraft2.network;

import com.austinv11.collectiveframework.minecraft.utils.WorldUtils;
import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.api.ITransmutationItem;
import com.austinv11.dartcraft2.api.ITransmutationRecipeHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TransmutePacket implements IMessage {
	
	public World world;
	public int x, y, z;
	public EntityPlayer player;
	public boolean isEntity = false;
	public int entityId;
	
	public TransmutePacket() {
		
	}
	
	public TransmutePacket(World world, int x, int y, int z, EntityPlayer player) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
	}
	
	public TransmutePacket(Entity entity, EntityPlayer player) {
		world = entity.worldObj;
		isEntity = true;
		entityId = entity.getEntityId();
		this.player = player;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		world = WorldUtils.getWorldFromDimensionId(tag.getInteger("dim"));
		isEntity = tag.getBoolean("isEntity");
		if (isEntity) 
			entityId = tag.getInteger("entityId");
		else {
			x = tag.getInteger("x");
			y = tag.getInteger("y");
			z = tag.getInteger("z");
		}
		player = (EntityPlayer) world.getEntityByID(tag.getInteger("player"));
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("isEntity", isEntity);
		tag.setInteger("player", player.getEntityId());
		tag.setInteger("dim", world.provider.dimensionId);
		if (isEntity)
			tag.setInteger("entityId", entityId);
		else {
			tag.setInteger("x", x);
			tag.setInteger("y", y);
			tag.setInteger("z", z);
		}
		ByteBufUtils.writeTag(buf, tag);
	}
	
	public static class TransmutePacketHandler implements IMessageHandler<TransmutePacket, IMessage> {
		
		@Override
		public IMessage onMessage(TransmutePacket message, MessageContext ctx) {
			if (message.isEntity) {
				Entity entity = message.world.getEntityByID(message.entityId);
				if (entity instanceof EntityItem) {
					ItemStack original = ((EntityItem) entity).getEntityItem();
					ITransmutationRecipeHandler.ItemInfo toTransmute = DartCraft2.TRANSMUTATION_HANDLER.getAvailableTransmutation(original.getItem(), 
							original.getItemDamage());
					if (toTransmute != null) {
						ItemStack newStack = new ItemStack(toTransmute.item, 0, toTransmute.meta);
						int originalStackSize = original.stackSize;
						for (int i = 0; i < originalStackSize; i++)
							if (((ITransmutationItem) message.player.getCurrentEquippedItem().getItem()).canTransmute(message.player.getCurrentEquippedItem(),
									original.getItem(), original.getItemDamage(), toTransmute.item, toTransmute.meta)) {
								((ITransmutationItem) message.player.getCurrentEquippedItem().getItem()).transmute(message.player.getCurrentEquippedItem(),
										original.getItem(), original.getItemDamage(), toTransmute.item, toTransmute.meta);
								newStack.stackSize++;
								original.stackSize--;
							}
						EntityItem transmutedEntity = new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, newStack);
						message.world.spawnEntityInWorld(transmutedEntity);
						if (original.stackSize <= 0)
							entity.setDead();
						else
							((EntityItem) entity).setEntityItemStack(original);
						return new DartCraftEffectPacket(transmutedEntity);
					}
				}
			} else {
				ITransmutationRecipeHandler.BlockInfo toTransmute = DartCraft2.TRANSMUTATION_HANDLER.getAvailableTransmutation(
						message.world.getBlock(message.x, message.y, message.z), message.world.getBlockMetadata(message.x, message.y, message.z));
				if (toTransmute != null)
					if (((ITransmutationItem) message.player.getCurrentEquippedItem().getItem()).canTransmute(
							message.world, message.x, message.y, message.z, message.player.getCurrentEquippedItem(), toTransmute.block, toTransmute.meta)) {
						((ITransmutationItem) message.player.getCurrentEquippedItem().getItem()).transmute(
								message.world, message.x, message.y, message.z, message.player.getCurrentEquippedItem(), toTransmute.block, toTransmute.meta);
						message.world.setBlock(message.x, message.y, message.z, toTransmute.block, toTransmute.meta, 3);
						return new DartCraftEffectPacket(message.world, message.x, message.y, message.z);
					}
			}
			return null;
		}
	}
}
