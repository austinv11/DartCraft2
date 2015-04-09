package com.austinv11.dartcraft2.recipes;

import com.austinv11.dartcraft2.api.DartCraft2API;
import com.austinv11.dartcraft2.api.FailedAPIRequest;
import com.austinv11.dartcraft2.api.ITransmutationItem;
import com.austinv11.dartcraft2.api.ITransmutationRecipeHandler;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeTransmutation implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting craftingInventory, World world) {
        int transmutationItemSlot = -1;
        int transmuted = -1;
        for (int i = 0; i < craftingInventory.getSizeInventory(); i++) {
            if (craftingInventory.getStackInSlot(i) != null) {
                if (craftingInventory.getStackInSlot(i).getItem() instanceof ITransmutationItem) {
                    if (transmutationItemSlot != -1)
                        return false;
                    transmutationItemSlot = i;
                } else {
                    if (transmuted != -1)
                        return false;
                    ITransmutationRecipeHandler.ItemInfo toTransmute = null;
                    try {
                        toTransmute = DartCraft2API.getTransmutationRecipeHandler().getAvailableTransmutation(
                                craftingInventory.getStackInSlot(i).getItem(), craftingInventory.getStackInSlot(i).getItemDamage());
                    } catch (FailedAPIRequest failedAPIRequest) {
//						failedAPIRequest.printStackTrace();
                    }
                    if (toTransmute == null || toTransmute.item == null)
                        return false;
                    transmuted = i;
                }
            }
        }
        if (transmutationItemSlot != -1 && transmuted != -1)
            try {
                ITransmutationRecipeHandler.ItemInfo transmute = new ITransmutationRecipeHandler.ItemInfo(craftingInventory.getStackInSlot(transmuted).getItem(),
                        craftingInventory.getStackInSlot(transmuted).getItemDamage());
                ITransmutationRecipeHandler.ItemInfo toTransmute = DartCraft2API.getTransmutationRecipeHandler().getAvailableTransmutation(
                        transmute.item, transmute.meta);
                return ((ITransmutationItem) craftingInventory.getStackInSlot(transmutationItemSlot).getItem()).canTransmute(
                        craftingInventory.getStackInSlot(transmutationItemSlot), transmute.item, transmute.meta, toTransmute.item, toTransmute.meta);
            } catch (FailedAPIRequest failedAPIRequest) {
                //			failedAPIRequest.printStackTrace();
            }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting craftingInventory) {
        int transmutationItemSlot = -1;
        int transmuted = -1;
        for (int i = 0; i < craftingInventory.getSizeInventory(); i++) {
            if (craftingInventory.getStackInSlot(i) != null) {
                if (craftingInventory.getStackInSlot(i).getItem() instanceof ITransmutationItem) {
                    if (transmutationItemSlot != -1)
                        return null;
                    transmutationItemSlot = i;
                } else {
                    if (transmuted != -1)
                        return null;
                    ITransmutationRecipeHandler.ItemInfo toTransmute = null;
                    try {
                        toTransmute = DartCraft2API.getTransmutationRecipeHandler().getAvailableTransmutation(
                                craftingInventory.getStackInSlot(i).getItem(), craftingInventory.getStackInSlot(i).getItemDamage());
                    } catch (FailedAPIRequest failedAPIRequest) {
//						failedAPIRequest.printStackTrace();
                    }
                    if (toTransmute == null || toTransmute.item == null)
                        return null;
                    transmuted = i;
                }
            }
        }
        if (transmutationItemSlot != -1 && transmuted != -1)
            try {
                craftingInventory.getStackInSlot(transmutationItemSlot).setItemDamage(craftingInventory.getStackInSlot(transmutationItemSlot).getItemDamage() == 0 ? 0 : craftingInventory.getStackInSlot(transmutationItemSlot).getItemDamage() - 1);
                ITransmutationRecipeHandler.ItemInfo transmute = new ITransmutationRecipeHandler.ItemInfo(craftingInventory.getStackInSlot(transmuted).getItem(),
                        craftingInventory.getStackInSlot(transmuted).getItemDamage());
                ITransmutationRecipeHandler.ItemInfo toTransmute = DartCraft2API.getTransmutationRecipeHandler().getAvailableTransmutation(
                        transmute.item, transmute.meta);
                return new ItemStack(toTransmute.item, 1, toTransmute.meta);
            } catch (FailedAPIRequest failedAPIRequest) {
//				failedAPIRequest.printStackTrace();
            }
        return null;
    }

    @Override
    public int getRecipeSize() {
        return 2;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
