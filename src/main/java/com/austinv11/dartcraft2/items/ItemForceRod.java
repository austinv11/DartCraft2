package com.austinv11.dartcraft2.items;

import com.austinv11.dartcraft2.api.ITransmutationItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemForceRod extends ItemDC implements ITransmutationItem {
	
	public static int MAX_DURABILITY = 200;
	
	public ItemForceRod() {
		super();
		this.setUnlocalizedName("forceRod");
		this.setMaxDamage(MAX_DURABILITY);
		this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
	
	@Override
	public boolean canTransmute(World world, int x, int y, int z, ItemStack itemStack, Block toBlock, int toMeta) {
		return true;
	}
	
	@Override
	public boolean canTransmute(ItemStack itemStack, Item fromItem, int fromMeta, Item toItem, int toMeta) {
		return true;
	}
	
	@Override
	public void transmute(World world, int x, int y, int z, ItemStack itemStack, Block toBlock, int toMeta) {
		itemStack.setItemDamage(itemStack.getItemDamage()+1);
	}
	
	@Override
	public void transmute(ItemStack itemStack, Item fromItem, int fromMeta, Item toItem, int toMeta) {
		itemStack.setItemDamage(itemStack.getItemDamage()+1);
	}
	
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isInHand) {
		if (itemStack.getItemDamage() > MAX_DURABILITY) {
			if (entity instanceof EntityPlayer) {
				if (isInHand)
					((EntityPlayer) entity).destroyCurrentEquippedItem();
				else
					((EntityPlayer) entity).inventory.setInventorySlotContents(slot, null);
				((EntityLivingBase) entity).renderBrokenItemStack(itemStack);
			}
			itemStack.stackSize = 0;
		}
	}

//	@Override
//	public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
//		ItemStack mat = this.toolMaterial.getRepairItemStack();
//		if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, p_82789_2_, false)) return true;
//		return super.getIsRepairable(p_82789_1_, p_82789_2_);
//	}
}
