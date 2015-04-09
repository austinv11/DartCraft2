package com.austinv11.dartcraft2.inventory;

import com.austinv11.dartcraft2.items.ItemDC;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDCOnly extends Slot {
    public SlotDCOnly(IInventory inv, int index, int xPos, int yPos) {
        super(inv, index, xPos, yPos);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemDC;
    }
}
