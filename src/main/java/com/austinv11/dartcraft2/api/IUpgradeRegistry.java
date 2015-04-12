package com.austinv11.dartcraft2.api;

import net.minecraft.item.Item;

import java.util.Set;

/**
 * This represents the infusion upgrade registry.
 * <b>Do NOT instantiate this, retrieve the instance via {@link DartCraft2API#getUpgradeRegistry()}</b>
 */
public interface IUpgradeRegistry {
	
	public Set<Item> getRegisteredItems();
	
	public Set<Item> getTools();
}
