package com.austinv11.dartcraft2.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.util.StatCollector;

public class ContainerForcePack extends Container {

    private EntityPlayer player;
    private InventoryBasic inv = new InventoryBasic(StatCollector.translateToLocal("gui.forcePack.name"), false, getInventorySize(player.getHeldItem().getItemDamage()));

    public ContainerForcePack(EntityPlayer player, int xSize, int ySize) {
        this.player = player;
        layout(xSize, ySize);
    }

    protected void layout(int xSize, int ySize) {
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

    public int getInventorySize(int meta) {
        return (meta + 1) * 9;
    }
}
