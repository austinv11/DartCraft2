package com.austinv11.dartcraft2.api.implementations;

import com.austinv11.dartcraft2.api.*;
import com.google.common.collect.ImmutableSet;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class UpgradeRegistry implements IUpgradeRegistry {
	
	private List<IForceUpgrade> upgrades = new ArrayList<IForceUpgrade>();
	private List<IForceArmor> armor = new ArrayList<IForceArmor>();
	private List<IForceTool> tools = new ArrayList<IForceTool>();
	
	
	@Override
	public Set<Item> getRegisteredItems() {
		ImmutableSet.Builder<Item> builder = ImmutableSet.builder();
		for (IForceUpgrade upgrade : upgrades)
			builder.add(upgrade.getItem());
		return builder.build();
	}
	
	@Override
	public Set<IForceUpgrade> getUpgrades() {
		return ImmutableSet.copyOf(upgrades);
	}
	
	@Override
	public Set<Item> getTools() {
		return getTools(EnumSet.allOf(ToolType.class));
	}
	
	@Override
	public Set<Item> getTools(EnumSet<ToolType> filter) {
		ImmutableSet.Builder<Item> builder = ImmutableSet.builder();
		for (ToolType t : filter) {
			for (IForceTool tool : tools)
				if (tool.getToolTypes().contains(t))
					builder.add((Item) tool);
			for (IForceArmor armor : this.armor)
				if (armor.getToolTypes().contains(t))
					builder.add((Item) armor);
		}
		return builder.build();
	}
	
	@Override
	public Set<IForceArmor> getForceArmor() {
		return ImmutableSet.copyOf(armor);
	}
	
	@Override
	public Set<IForceTool> getForceTools() {
		return ImmutableSet.copyOf(tools);
	}
	
	@Override
	public void registerUpgrade(IForceUpgrade upgrade) {
		upgrades.add(upgrade);
	}
	
	@Override
	public void registerTool(IForceTool tool) {
		tools.add(tool);
	}
	
	@Override
	public void registerArmor(IForceArmor armor) {
		this.armor.add(armor);
	}
}
