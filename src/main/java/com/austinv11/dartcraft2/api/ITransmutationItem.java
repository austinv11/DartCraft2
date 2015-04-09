package com.austinv11.dartcraft2.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * An interface to represent an item which could transmute other items/blocks
 */
public interface ITransmutationItem {

    /**
     * This is called on an in-world transmutation
     *
     * @param world     The world of the block to transmute
     * @param x         The x coord of the block to transmute
     * @param y         The y coord of the block to transmute
     * @param z         The z coord of the block to transmute
     * @param itemStack The stack attempting to transmute a block
     * @param toBlock   The block to transmute into
     * @param toMeta    The metadata to transmute into
     * @return True if the transmutation should be performed
     */
    public boolean canTransmute(World world, int x, int y, int z, ItemStack itemStack, Block toBlock, int toMeta);

    /**
     * This is called on a crafting transmutation
     *
     * @param itemStack The stack attempting to transmute
     * @param fromItem  The item to be transmuted
     * @param fromMeta  The metadata of the item to be transmuted
     * @param toItem    The item to be transmuted into
     * @param toMeta    The metadata of the item to be transmuted into
     * @return True if the transmutation should be performed
     */
    public boolean canTransmute(ItemStack itemStack, Item fromItem, int fromMeta, Item toItem, int toMeta);
}
