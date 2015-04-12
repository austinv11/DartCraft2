package com.austinv11.dartcraft2.utils;

import baubles.api.BaublesApi;
import com.austinv11.collectiveframework.minecraft.utils.NBTHelper;
import com.austinv11.dartcraft2.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DartCraftUtils {

    /**
     * Used to determine whether the the belt that is being used has been opened with a keybind or right-click.
     * If it was opened by right-clicking, it will return the belt in the player's hand.
     * If there is not a belt in the player's hand (it was opened with a key bind), it will attempt to find one in the player's hotbar.
     */
    public static ItemStack getCorrectForceBelt(EntityPlayer player) {
        if (player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.forceBelt) {
            return player.getHeldItem();
        } else if(BaublesApi.getBaubles(player).getStackInSlot(3).getItem() == ModItems.forceBelt) {
            return BaublesApi.getBaubles(player).getStackInSlot(3);
        }
        return null;
    }

    /**
     * Used to determine the correct Y size of the gui being used for the force pack.
     * This is needed because there are 5 different GUIs used depending on the tier (stored as metadata) of the force pack.
     * It works by adding the metadata (the tier or amount of extra rows) times 18 (the size of a slot) to 141 (the size of the original GUI texture)
     */
    public static int getForcePackGuiYSize(int meta) {
        return 141 + (meta * 18);
    }

    /**
     * Reads the passed Itemstack's nbt and sets the slots in the passed IInventory accordingly.
     * The size of the passed IInventory must be set correctly for this to work properly.
     */
    public static void readItemInventoryFromNBT(IInventory inv, ItemStack stack) {
        if (stack != null) {
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                if (NBTHelper.hasTag(stack, "slot" + i)) {
                    inv.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(NBTHelper.getCompoundTag(stack, "slot" + i)));
                }
            }
        }
    }

    /**
     * Writes the passed IInventory's slot contents to the passed Itemstack's NBT.
     */
    public static void writeItemInventoryToNBT(IInventory inv, ItemStack stack) {
        if (stack != null) {
            stack.stackTagCompound = null;
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                if (inv.getStackInSlot(i) != null) {
                    NBTHelper.setCompoundTag(stack, "slot" + i, inv.getStackInSlot(i).writeToNBT(new NBTTagCompound()));
                }
            }
        }
    }
}
