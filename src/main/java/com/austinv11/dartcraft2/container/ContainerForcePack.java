package com.austinv11.dartcraft2.container;

import com.austinv11.dartcraft2.utils.DartCraftUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ContainerForcePack extends Container {

    private EntityPlayer player;
    private InventoryBasic inv;
    private Slot slots[];
    private int numRows;
    private int numCols;

    public ContainerForcePack(EntityPlayer player, int xSize, int ySize) {
        this.player = player;
        this.inv = new InventoryBasic(StatCollector.translateToLocal("gui.forcePack.name"), false, getInventorySize(player.getHeldItem().getItemDamage()));
        this.slots = new Slot[9 + (player.getHeldItem().getItemDamage() * 9)];
        this.numRows = 1 + player.getHeldItem().getItemDamage();
        this.numCols = 9;
        layout(xSize, ySize);

        DartCraftUtils.readItemInventoryFromNBT(inv, player.getHeldItem());
    }

    protected void layout(int xSize, int ySize) {
        int leftCol = (xSize - 162) / 2 + 1;

        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numCols; column++) {
                slots[column + row * 9] = new Slot(inv, column + row * 9, leftCol + column * 18, ySize - ((4 - row) * 18) - (48 + player.getHeldItem().getItemDamage() * 18));
                addSlotToContainer(slots[column + row * 9]);
            }
        }

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
    public void onContainerClosed(EntityPlayer player) {
        DartCraftUtils.writeItemInventoryToNBT(inv, player.getHeldItem());
        super.onContainerClosed(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return inv.isUseableByPlayer(player);
    }

    // See ContainerForceBelt for documentation
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(i);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (i < inv.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, inv.getSizeInventory(), 36 + inv.getSizeInventory(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, inv.getSizeInventory(), false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    public int getInventorySize(int meta) {
        return (meta + 1) * 9;
    }
}
