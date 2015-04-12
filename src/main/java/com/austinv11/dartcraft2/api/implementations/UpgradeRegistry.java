package com.austinv11.dartcraft2.api.implementations;

import com.austinv11.dartcraft2.api.IUpgradeRegistry;
import com.google.common.collect.ImmutableSet;
import net.minecraft.item.Item;

import java.util.Set;

public class UpgradeRegistry implements IUpgradeRegistry {
	
	@Override
	public Set<Item> getRegisteredItems() {
		return ImmutableSet.of();
	}
	
	@Override
	public Set<Item> getTools() {
		return ImmutableSet.of();
	}
}
