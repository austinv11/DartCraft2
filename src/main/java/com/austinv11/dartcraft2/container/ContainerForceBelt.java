package com.austinv11.dartcraft2.container;

import com.austinv11.collectiveframework.minecraft.utils.NBTHelper;
import com.austinv11.dartcraft2.init.ModItems;
import com.austinv11.dartcraft2.inventory.SlotDCOnly;
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
    public boolean isClosed;

    public Slot slot1 = new SlotDCOnly(inv, 0, 17, 17);
    public Slot slot2 = new SlotDCOnly(inv, 1, 35, 17);
    public Slot slot3 = new SlotDCOnly(inv, 2, 53, 17);
    public Slot slot4 = new SlotDCOnly(inv, 3, 71, 17);
    public Slot slot5 = new SlotDCOnly(inv, 4, 89, 17);
    public Slot slot6 = new SlotDCOnly(inv, 5, 107, 17);
    public Slot slot7 = new SlotDCOnly(inv, 6, 125, 17);
    public Slot slot8 = new SlotDCOnly(inv, 7, 143, 17);

    public ContainerForceBelt(EntityPlayer player, int xSize, int ySize) {
        this.player = player;
        layout(xSize, ySize);

        if (player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.forceBelt) {
            this.forceBelt = player.getHeldItem();
        } else {
            this.forceBelt = getBeltInBar(player);
        }

        for (int i = 0; i < 9; i++) {
            if (NBTHelper.hasTag(forceBelt, "slot" + i)) {
                inv.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(NBTHelper.getCompoundTag(forceBelt, "slot" + i)));
            }
        }

        isClosed = false;
    }

    protected void layout(int xSize, int ySize) {
        this.addSlotToContainer(slot1);
        this.addSlotToContainer(slot2);
        this.addSlotToContainer(slot3);
        this.addSlotToContainer(slot4);
        this.addSlotToContainer(slot5);
        this.addSlotToContainer(slot6);
        this.addSlotToContainer(slot7);
        this.addSlotToContainer(slot8);

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
        if (player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.forceBelt) {
            this.forceBelt = player.getHeldItem();
        } else {
            this.forceBelt = getBeltInBar(player);
        }

        if (forceBelt != null) {
            forceBelt.stackTagCompound = null;
            for (int i = 0; i < inv.getSizeInventory(); i++)
                if (inv.getStackInSlot(i) != null) {
                    NBTHelper.setCompoundTag(forceBelt, "slot" + i, inv.getStackInSlot(i).writeToNBT(new NBTTagCompound()));
                }
        }
        isClosed = true;
        super.onContainerClosed(player);
    }

    private ItemStack getBeltInBar(EntityPlayer player) {
        for (int i = 0; i < 9; i++) {
            if (player.inventory.getStackInSlot(i).getItem() == ModItems.forceBelt) {
                return player.inventory.getStackInSlot(i);
            }
        }
        return null;
    }
}
