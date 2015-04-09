package com.austinv11.dartcraft2.api.implementations;

import com.austinv11.dartcraft2.api.FailedAPIRequest;
import com.austinv11.dartcraft2.api.ITransmutationRecipeHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransmutationRecipeHandler implements ITransmutationRecipeHandler {
	
	private final Map<ItemInfo, ItemInfo> transmutationMap = new HashMap<ItemInfo, ItemInfo>();
	
	@Override
	public void addTransmutation(Block fromBlock, Block toBlock) throws FailedAPIRequest {
		addTransmutation(fromBlock, 0, toBlock, 0);
	}
	
	@Override
	public void addTransmutation(Block fromBlock, Block toBlock, int toMeta) throws FailedAPIRequest {
		addTransmutation(fromBlock, 0, toBlock, toMeta);
	}
	
	@Override
	public void addTransmutation(Block fromBlock, int fromMeta, Block toBlock) throws FailedAPIRequest {
		addTransmutation(fromBlock, fromMeta, toBlock, 0);
	}
	
	@Override
	public void addTransmutation(Block fromBlock, int fromMeta, Block toBlock, int toMeta) throws FailedAPIRequest {
		addTransmutation(Item.getItemFromBlock(fromBlock), fromMeta, Item.getItemFromBlock(toBlock), toMeta);
	}
	
	@Override
	public void addTransmutation(Item fromItem, Item toItem) throws FailedAPIRequest {
		addTransmutation(fromItem, 0, toItem, 0);
	}
	
	@Override
	public void addTransmutation(Item fromItem, Item toItem, int toMeta) throws FailedAPIRequest {
		addTransmutation(fromItem, 0, toItem, toMeta);
	}
	
	@Override
	public void addTransmutation(Item fromItem, int fromMeta, Item toItem) throws FailedAPIRequest {
		addTransmutation(fromItem, fromMeta, toItem, 0);
	}
	
	@Override
	public void addTransmutation(Item fromItem, int fromMeta, Item toItem, int toMeta) throws FailedAPIRequest {
		if (transmutationMap.containsKey(new ItemInfo(fromItem, fromMeta)))
			throw new FailedAPIRequest("Item "+fromItem+" already has a transmutation recipe!");
		transmutationMap.put(new ItemInfo(fromItem, fromMeta), new ItemInfo(toItem, toMeta));
	}
	
	@Override
	public List<ItemInfo> getTransmutableItems() {
		ArrayList<ItemInfo> items = new ArrayList<ItemInfo>();
		items.addAll(transmutationMap.keySet());
		return items;
	}
	
	@Override
	public ItemInfo getAvailableTransmutation(Item toTransmute) {
		return getAvailableTransmutation(toTransmute, 0);
	}
	
	@Override
	public ItemInfo getAvailableTransmutation(Item toTransmute, int meta) {
		return transmutationMap.get(new ItemInfo(toTransmute, meta));
	}
	
	@Override
	public List<BlockInfo> getTransmutableBlocks() {
		ArrayList<BlockInfo> items = new ArrayList<BlockInfo>();
		for (ItemInfo i : transmutationMap.keySet())
			if (i.item instanceof ItemBlock)
				items.add(new BlockInfo(Block.getBlockFromItem(i.item), i.meta));
		return items;
	}
	
	@Override
	public BlockInfo getAvailableTransmutation(Block toTransmute) {
		return getAvailableTransmutation(toTransmute, 0);
	}
	
	@Override
	public BlockInfo getAvailableTransmutation(Block toTransmute, int meta) {
		ItemInfo info = getAvailableTransmutation(Item.getItemFromBlock(toTransmute), meta);
		if (info != null)
			return new BlockInfo(Block.getBlockFromItem(info.item), meta);
		return null;
	}
}
