package com.austinv11.dartcraft2.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.List;

/**
 * An interface representing the recipe handler for transmutation recipes.
 * <b>Do NOT instantiate this, retrieve the instance via {@link DartCraft2API#getTransmutationRecipeHandler()}</b>
 */
public interface ITransmutationRecipeHandler {
	
	/**
	 * Adds a transmutation recipe
	 * @param fromBlock The block to transmute
	 * @param toBlock The block to transmute into
	 * @throws FailedAPIRequest If a transmutation already exists for the block
	 */
	public void addTransmutation(Block fromBlock, Block toBlock) throws FailedAPIRequest;
	
	/**
	 * Adds a transmutation recipe
	 * @param fromBlock The block to transmute
	 * @param toBlock The block to transmute into
	 * @param toMeta The meta value of the block to transmute into
	 * @throws FailedAPIRequest If a transmutation already exists for the block
	 */
	public void addTransmutation(Block fromBlock, Block toBlock, int toMeta) throws FailedAPIRequest;
	
	/**
	 * Adds a transmutation recipe
	 * @param fromBlock The block to transmute
	 * @param fromMeta The meta value of the block to transmute
	 * @param toBlock The block to transmute into
	 * @throws FailedAPIRequest If a transmutation already exists for the block
	 */
	public void addTransmutation(Block fromBlock, int fromMeta, Block toBlock) throws FailedAPIRequest;
	
	/**
	 * Adds a transmutation recipe
	 * @param fromBlock The block to transmute
	 * @param fromMeta The meta value of the block to transmute
	 * @param toBlock The block to transmute into
	 * @param toMeta The meta value of the block to transmute into
	 * @throws FailedAPIRequest If a transmutation already exists for the block
	 */
	public void addTransmutation(Block fromBlock, int fromMeta, Block toBlock, int toMeta) throws FailedAPIRequest;
	
	/**
	 * Adds a transmutation recipe
	 * @param fromItem The block to transmute
	 * @param toItem The block to transmute into
	 * @throws FailedAPIRequest If a transmutation already exists for the item
	 */
	public void addTransmutation(Item fromItem, Item toItem) throws FailedAPIRequest;
	
	/**
	 * Adds a transmutation recipe
	 * @param fromItem The block to transmute
	 * @param toItem The block to transmute into
	 * @param toMeta The meta value of the block to transmute into
	 * @throws FailedAPIRequest If a transmutation already exists for the item
	 */
	public void addTransmutation(Item fromItem, Item toItem, int toMeta) throws FailedAPIRequest;
	
	/**
	 * Adds a transmutation recipe
	 * @param fromItem The block to transmute
	 * @param fromMeta The meta value of the block to transmute
	 * @param toItem The block to transmute into
	 * @throws FailedAPIRequest If a transmutation already exists for the item
	 */
	public void addTransmutation(Item fromItem, int fromMeta, Item toItem) throws FailedAPIRequest;
	
	/**
	 * Adds a transmutation recipe
	 * @param fromItem The block to transmute
	 * @param fromMeta The meta value of the block to transmute
	 * @param toItem The block to transmute into
	 * @param toMeta The meta value of the block to transmute into
	 * @throws FailedAPIRequest If a transmutation already exists for the item
	 */
	public void addTransmutation(Item fromItem, int fromMeta, Item toItem, int toMeta) throws FailedAPIRequest;
	
	/**
	 * Gets the list of transmutable items
	 * @return The list of transmutable items. Note, this includes the ItemBlock versions of all the transmutable blocks
	 */
	public List<ItemInfo> getTransmutableItems();
	
	/**
	 * Gets the transmutation for the requested item. Note, this works with the ItemBlock versions of transmutable blocks
	 * @param toTransmute The item to transmute
	 * @return The transmuted item, or null if there aren't any
	 */
	public ItemInfo getAvailableTransmutation(Item toTransmute);
	
	/**
	 * Gets the transmutation for the requested item. Note, this works with the ItemBlock versions of transmutable blocks
	 * @param toTransmute The item to transmute
	 * @param meta The metadata of the item
	 * @return The transmuted item, or null if there aren't any
	 */
	public ItemInfo getAvailableTransmutation(Item toTransmute, int meta);
	
	/**
	 * Same as {@link #getTransmutableItems()} except for blocks exclusively
	 */
	public List<BlockInfo> getTransmutableBlocks();
	
	/**
	 * Same as {@link #getAvailableTransmutation(Item)} except for blocks exclusively
	 */
	public BlockInfo getAvailableTransmutation(Block toTransmute);
	
	/**
	 * Same as {@link #getAvailableTransmutation(Item, int)} except for blocks exclusively
	 */
	public BlockInfo getAvailableTransmutation(Block toTransmute, int meta);
	
	/**
	 * An object holding various block data
	 */
	public static class BlockInfo {
		
		public Block block;
		public int meta;
		
		public BlockInfo(Block block, int meta) {
			this.block = block;
			this.meta = meta;
		}
		
		@Override
		public boolean equals(Object other) {
			if (other instanceof BlockInfo)
				return Block.getIdFromBlock(((BlockInfo) other).block) == Block.getIdFromBlock(block) 
						&& ((BlockInfo) other).meta == meta;
			return false;
		}
		
		@Override
		public int hashCode() { //Done to allow for use as keys in HashMaps
			return 0;
		}
	}
	
	/**
	 * An object holding various item data
	 */
	public static class ItemInfo {
		
		public Item item;
		public int meta;
		
		public ItemInfo(Item item, int meta) {
			this.item = item;
			this.meta = meta;
		}
		
		@Override
		public boolean equals(Object other) {
			if (other instanceof ItemInfo)
				return Item.getIdFromItem(((ItemInfo) other).item) == Item.getIdFromItem(item) 
						&& ((ItemInfo) other).meta == meta;
			return false;
		}
		
		@Override
		public int hashCode() { //Done to allow for use as keys in HashMaps
			return 0;
		}
	}
}
