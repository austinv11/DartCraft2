package com.austinv11.dartcraft2.api;

import net.minecraft.item.Item;

import java.util.EnumSet;
import java.util.Set;

/**
 * This represents the infusion upgrade registry.
 * <b>Do NOT instantiate this, retrieve the instance via {@link DartCraft2API#getUpgradeRegistry()}</b>
 */
public interface IUpgradeRegistry {
	
	/**
	 * Returns all the registered items which can be infused
	 * @return The set of upgrade items
	 */
	public Set<Item> getRegisteredItems();
	
	/**
	 * Returns all registered upgrades
	 * @return The set of upgrades
	 */
	public Set<IForceUpgrade> getUpgrades();
	
	/**
	 * Returns all of the registered tools which can be infused
	 * @return The set of tools
	 */
	public Set<Item> getTools();
	
	/**
	 * Returns all of the registered tools which can be infused
	 * @param filter The tooltypes to limit the set to
	 * @return The set of tools
	 */
	public Set<Item> getTools(EnumSet<ToolType> filter);
	
	/**
	 * Returns all of the registered force armor
	 * @return The set of {@link IForceArmor}
	 */
	public Set<IForceArmor> getForceArmor();
	
	/**
	 * Returns all of the registered force tools
	 * @return The set of {@link IForceTool}
	 */
	public Set<IForceTool> getForceTools();
	
	/**
	 * Registers an infusion upgrade
	 * @param upgrade The upgrade
	 */
	public void registerUpgrade(IForceUpgrade upgrade);
	
	/**
	 * Registers a tool which could be upgraded
	 * @param tool The tool
	 */
	public void registerTool(IForceTool tool);
	
	/**
	 * Registers an armor piece which could be upgraded
	 * @param armor The armor
	 */
	public void registerArmor(IForceArmor armor);
}
