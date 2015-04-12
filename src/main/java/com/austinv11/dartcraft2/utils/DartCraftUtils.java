package com.austinv11.dartcraft2.utils;

import com.austinv11.dartcraft2.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class DartCraftUtils {

    /**
     * Used to determine whether the the belt that is being used has been opened with a keybind or right-click.
     * If it was opened by right-clicking, it will return the belt in the player's hand.
     * If there is not a belt in the player's hand (it was opened with a key bind), it will attempt to find one in the player's hotbar.
     */
    public static ItemStack getCorrectForceBelt(EntityPlayer player) {
        if (player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.forceBelt) {
            return player.getHeldItem();
        } else {
            for (int i = 0; i < 9; i++) {
                if (player.inventory.getStackInSlot(i).getItem() == ModItems.forceBelt) {
                    return player.inventory.getStackInSlot(i);
                }
            }
        }
        return null;
    }
}
