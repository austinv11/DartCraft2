package com.austinv11.dartcraft2.container;

import com.austinv11.collectiveframework.minecraft.utils.NBTHelper;
import com.austinv11.dartcraft2.inventory.SlotDCOnly;
import com.austinv11.dartcraft2.utils.DartCraftUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class ContainerForceBelt extends Container {
    private EntityPlayer player;
    private ItemStack forceBelt;
    private InventoryBasic inv = new InventoryBasic(StatCollector.translateToLocal("gui.forceBelt.name"), false, 35);
    private Slot[] slots = new Slot[8];

    public ContainerForceBelt(EntityPlayer player, int xSize, int ySize) {
        this.player = player;

        for (int i = 0; i < 8; i++) {
            slots[i] = new SlotDCOnly(inv, i, 17 + (i * 18), 17);
        }

        layout(xSize, ySize);

        this.forceBelt = DartCraftUtils.getCorrectForceBelt(player);

        for (int i = 0; i < 9; i++) {
            if (NBTHelper.hasTag(forceBelt, "slot" + i)) {
                inv.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(NBTHelper.getCompoundTag(forceBelt, "slot" + i)));
            }
        }
    }

    protected void layout(int xSize, int ySize) {
        for (int i = 0; i < 8; i++) {
            addSlotToContainer(slots[i]);
        }

        int leftCol = (xSize - 162) / 2 + 1;
        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
                addSlotToContainer(new Slot(player.inventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, ySize - (4 - playerInvRow) * 18 - 10));
            }
        }
        for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
            addSlotToContainer(new Slot(player.inventory, hotbarSlot, leftCol + hotbarSlot * 18, ySize - 24));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return inv.isUseableByPlayer(player);
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        forceBelt = DartCraftUtils.getCorrectForceBelt(player);
        if (forceBelt != null) {
            forceBelt.stackTagCompound = null;
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                if (inv.getStackInSlot(i) != null) {
                    NBTHelper.setCompoundTag(forceBelt, "slot" + i, inv.getStackInSlot(i).writeToNBT(new NBTTagCompound()));
                }
            }
        }
        super.onContainerClosed(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i) {
        // Create a new itemstack. This is the stack that will be manipulated and returned.
        ItemStack itemstack = null;
        // Get the slot that was just shift clicked. This is the slot that the itemstack will be transferring from.
        Slot slot = (Slot) this.inventorySlots.get(i);

        // Check that the slot exists and has an itemstack in it
        if (slot != null && slot.getHasStack()) {
            // Get the stack in the slot that was shift-clicked. This stack will act as a base for our return itemstack.
            ItemStack itemstack1 = slot.getStack();
            // Copy that stack to our return itemstack.
            itemstack = itemstack1.copy();

            if (i < 9) // If the slot being transferred from is less than 9, then the item is being transferred from the belt to the player inv.
            {
                if (!this.mergeItemStack(itemstack1, 9, this.inv.getSizeInventory(), true)) // If the itemstack can't merge with any stacks in the player's
                {                                                                           // main inv.
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 9, false)) // If the itemstack can't merge with any stacks in the force belt container, return.
            {                                                       // Implies that the stack being transferred is from a slot in the player's main inv
                return null;
            }

            // After the merging has completed, if the itemstack has a size of 0, replace it with an empty slot.
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                // Inform the game that the slot was changed.
                slot.onSlotChanged();
            }
        }

        // Return the new itemstack. This is the itemstack that will be placed in the slot that is being transferred to.
        return itemstack;
    }


}
