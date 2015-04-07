package com.austinv11.dartcraft2.container;

import com.austinv11.collectiveframework.minecraft.utils.NBTHelper;
import com.austinv11.collectiveframework.utils.ArrayUtils;
import com.austinv11.dartcraft2.items.ItemClipboard;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContainerClipboard extends ContainerWorkbench {
	
	public EntityPlayer player;
	public boolean isClosed = true;
	
	public ContainerClipboard(EntityPlayer player) {
		this(player.inventory, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
		this.player = player;
		ItemStack clipboard = player.getHeldItem();
		for (int i = 0; i < 9; i++)
			if (NBTHelper.hasTag(clipboard, "slot"+i))
				craftMatrix.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(NBTHelper.getCompoundTag(clipboard, "slot"+i)));
		isClosed = false;
	}
	
	protected ContainerClipboard(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
		super(inventoryPlayer, world, x, y, z);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		if (player != null && player.getHeldItem() != null && !isClosed) {
			player.getHeldItem().stackTagCompound = null;
			for (int i = 0; i < inventory.getSizeInventory(); i++)
				if (inventory.getStackInSlot(i) != null) {
					NBTHelper.setCompoundTag(player.getHeldItem(), "slot"+i, inventory.getStackInSlot(i).writeToNBT(new NBTTagCompound()));
				}
		}
		super.onCraftMatrixChanged(inventory);
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		isClosed = true;
		for (int i = 0; i < craftMatrix.getSizeInventory(); i++)
			craftMatrix.setInventorySlotContents(i, null);
		super.onContainerClosed(player);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		if (player.getHeldItem() != null)
			if (player.getHeldItem().getItem() instanceof ItemClipboard)
				return true;
		return false;
	}
	
	public void doItemDistribution() {
		HashMap<Item, List<Integer>> items = new HashMap<Item, List<Integer>>();
		HashMap<Item, Integer> itemCount = new HashMap<Item, Integer>();
		for (int i = 0; i < craftMatrix.getSizeInventory(); i++)
			if (craftMatrix.getStackInSlot(i) != null) {
				if (!items.containsKey(craftMatrix.getStackInSlot(i).getItem())) {
					items.put(craftMatrix.getStackInSlot(i).getItem(), new ArrayList<Integer>());
					itemCount.put(craftMatrix.getStackInSlot(i).getItem(), 0);
				}
				items.get(craftMatrix.getStackInSlot(i).getItem()).add(i);
				itemCount.put(craftMatrix.getStackInSlot(i).getItem(), itemCount.get(craftMatrix.getStackInSlot(i).getItem())+craftMatrix.getStackInSlot(i).stackSize);
				craftMatrix.getStackInSlot(i).stackSize = 0;
			}
		for (Item item : itemCount.keySet()) {
			int left = itemCount.get(item);
			whileLoop: while (left > 0)
				for (Integer slot : items.get(item)) {
					ItemStack stack = craftMatrix.getStackInSlot(slot);
					stack.stackSize++;
					craftMatrix.setInventorySlotContents(slot, stack);
					left--;
					if (left == 0)
						break whileLoop;
				}
		}
	}
	
	public void doSmartAssist() {
		List<Item> itemTypes = new ArrayList<Item>();
		boolean rotate = true;
		int amount = 0;
		int slot = 0;
		for (int i = 0; i < craftMatrix.getSizeInventory(); i++)
			if (craftMatrix.getStackInSlot(i) != null) {
				if (!itemTypes.contains(craftMatrix.getStackInSlot(i).getItem()))
					itemTypes.add(craftMatrix.getStackInSlot(i).getItem());
				amount += craftMatrix.getStackInSlot(i).stackSize;
				slot = i;
			} else if (i != 4)
				rotate = false;
		
		if (rotate || itemTypes.size() != 1 || amount < 8) {
			ItemStack[] stacks = inventoryToArray(craftMatrix); //ugh, hard coding
			craftMatrix.setInventorySlotContents(0, ArrayUtils.wrappedRetrieve(stacks, 1));
			craftMatrix.setInventorySlotContents(1, ArrayUtils.wrappedRetrieve(stacks, 2));
			craftMatrix.setInventorySlotContents(2, ArrayUtils.wrappedRetrieve(stacks, 4));
			craftMatrix.setInventorySlotContents(3, ArrayUtils.wrappedRetrieve(stacks, 0));
			craftMatrix.setInventorySlotContents(5, ArrayUtils.wrappedRetrieve(stacks, 7));
			craftMatrix.setInventorySlotContents(6, ArrayUtils.wrappedRetrieve(stacks, 3));
			craftMatrix.setInventorySlotContents(7, ArrayUtils.wrappedRetrieve(stacks, 5));
			craftMatrix.setInventorySlotContents(8, ArrayUtils.wrappedRetrieve(stacks, 6));
		} else {
			if (!itemTypes.isEmpty() && itemTypes.size() == 1) {
				ItemStack ghost = craftMatrix.getStackInSlot(slot).copy();
				ghost.stackSize = 1;
				for (int i = 0; i < (amount > 9 ? 9 : amount); i++)
					if (i != 4) {
						craftMatrix.setInventorySlotContents(i, ghost.copy());
					}
				if (amount > 8) {
					ghost.stackSize = amount-7;
					craftMatrix.setInventorySlotContents(0, ghost);
					doItemDistribution();
				}
			}
		}
	}
	
	private ItemStack[] inventoryToArray(InventoryCrafting inventoryCrafting) {
		ItemStack[] stacks = new ItemStack[8];
		for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
			if (i < 4) {
				stacks[i] = inventoryCrafting.getStackInSlot(i);
			} else if (i > 4) {
				stacks[i-1] = inventoryCrafting.getStackInSlot(i); 
			}
		}
		return stacks;
	}
	
	public void doRemoveItems() {
		for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
			boolean didMove = player.inventory.addItemStackToInventory(craftMatrix.getStackInSlot(i));
			if (didMove)
				craftMatrix.setInventorySlotContents(i, null);
		}
	}
}
