package com.austinv11.dartcraft2.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.EnumSet;

/**
 * Implement this on a class which represents a force upgrade TODO: Add actual hooks
 */
public interface IForceUpgrade {
	
	/**
	 * Gets the item which represents the upgrade
	 * @return The item
	 */
	public Item getItem();
	
	/**
	 * Called to update the upgrade
	 * @param stack The stack the upgrade is on
	 * @param level The level of the upgrade
	 */
	public void update(ItemStack stack, int level);
	
	/**
	 * Called when an itemstack is saved to nbt
	 * @param compound The tag
	 */
	public void writeToNBT(NBTTagCompound compound);
	
	/**
	 * Called when an itemstack is read from nbt
	 * @param compound The tag
	 */
	public void readFromNBT(NBTTagCompound compound);
	
	/**
	 * Gets the amount of experience gained on an upgrade tome for infusing this upgrade
	 * @param amount The amount of the upgrades present in the infusion
	 * @param isFirstTime If the upgrade is being infused for the first time
	 * @return The amount of experience earned
	 */
	public int getExperience(int amount, boolean isFirstTime);
	
	/**
	 * Gets the unlocalized name of the upgrade
	 * @return The unlocalized upgrade name
	 */
	public String getUnlocalizedName();
	
	/**
	 * Gets the tool types the upgrade can be applied to
	 * @return The set of tool types
	 */
	public EnumSet<ToolType> getToolTypes();
	
	/**
	 * Gets the minimum tier required to infuse this upgrade
	 * @return The tier
	 */
	public int getRequiredTier();
	
	/**
	 * Gets the amount of time (in game ticks) the upgrade adds to the total infusion time
	 * @param amount The amount of the upgrades present in the infusion
	 * @return The amount of time
	 */
	public int getRequiredTime(int amount);
	
	/**
	 * Gets the amount of aura required for the upgrade to be infused
	 * @param amount The amount of the upgrades present in the infusion
	 * @return The amount of aura
	 */
	public int getRequiredAura(int amount);
	
	/**
	 * Gets the highest level upgrade possible
	 * @return The level
	 */
	public int getMaxLevel();
}
