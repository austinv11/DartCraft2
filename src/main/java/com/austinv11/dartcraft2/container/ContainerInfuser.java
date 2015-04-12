package com.austinv11.dartcraft2.container;

import com.austinv11.collectiveframework.minecraft.inventory.ExclusiveSlot;
import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.init.ModItems;
import com.austinv11.dartcraft2.inventory.SlotTierLocked;
import com.austinv11.dartcraft2.tileentities.TileEntityInfuser;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class ContainerInfuser extends Container {
	
	private TileEntityInfuser infuser;
	private EntityPlayer player;
	
	private Slot[] slots = new Slot[11];
	
	public ContainerInfuser(TileEntityInfuser infuser, EntityPlayer player, int xSize, int ySize) {
		this.infuser = infuser;
		this.player = player;
		slots[0] = new ExclusiveSlot(infuser, 0, 12, -10).setExclusive(ImmutableSet.of((Item) ModItems.upgradeTome)).setWhitelist(true);
		slots[1] = new ExclusiveSlot(infuser, 1, 12, 11).setExclusive(getSetForSlot1()).setWhitelist(true);
		slots[2] = new SlotTierLocked(infuser, 2, 80, -3).setTier(0);
		slots[3] = new SlotTierLocked(infuser, 3, 106, 7).setTier(1);
		slots[4] = new SlotTierLocked(infuser, 4, 116, 33).setTier(2);
		slots[5] = new SlotTierLocked(infuser, 5, 106, 59).setTier(3);
		slots[6] = new SlotTierLocked(infuser, 6, 80, 69).setTier(4);
		slots[7] = new SlotTierLocked(infuser, 7, 54, 59).setTier(5);
		slots[8] = new SlotTierLocked(infuser, 8, 44, 33).setTier(6);
		slots[9] = new SlotTierLocked(infuser, 9, 54, 7).setTier(7);
		slots[10] = new ExclusiveSlot(infuser, 10, 80, 33).setExclusive(DartCraft2.UPGRADE_REGISTRY.getTools()).setWhitelist(true);
		layout(xSize, ySize);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		int numberOfSlots = 11;
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotIndex);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (slotIndex < numberOfSlots) {
				if (!this.mergeItemStack(itemstack1, numberOfSlots, this.inventorySlots.size(), true)) {
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, numberOfSlots, false)) {
				return null;
			}
			
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack)null);
			}
			else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return infuser.isUseableByPlayer(player);
	}
	
	protected void layout(int xSize, int ySize) {
		for (Slot s : slots)
			addSlotToContainer(s);
		
		int leftCol = (xSize - 162) / 2 + 1;
		for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
			for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
				addSlotToContainer(new Slot(player.inventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, ySize - (4 - playerInvRow) * 18 - 28));
			}
		}
		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlotToContainer(new Slot(player.inventory, hotbarSlot, leftCol + hotbarSlot * 18, ySize - 42));
		}
	}
	
	private ImmutableSet<Item> getSetForSlot1() {
		List<Item> items = new ArrayList<Item>();
		for (ItemStack stack : OreDictionary.getOres("gemForce"))
			items.add(stack.getItem());
		items.add(ModItems.liquidForceBucket);
		return ImmutableSet.copyOf(items.toArray(new Item[items.size()]));
	}
}
