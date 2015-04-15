package com.austinv11.dartcraft2.api;

import com.austinv11.collectiveframework.minecraft.utils.NBTHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The class used to interface with DartCraft 2
 */
public class DartCraft2API {
	
	/**
	 * Gets the {@link ITransmutationRecipeHandler} to interface with
	 * @return The instance of the handler
	 * @throws FailedAPIRequest
	 */
	public static ITransmutationRecipeHandler getTransmutationRecipeHandler() throws FailedAPIRequest {
		try {
			return (ITransmutationRecipeHandler) Class.forName("com.austinv11.dartcraft2.DartCraft2").getDeclaredField("TRANSMUTATION_HANDLER").get(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedAPIRequest("Unknown exception retrieving the transmutation recipe handler");
		}
	}
	
	/**
	 * Gets the {@link IUpgradeRegistry} to interface with
	 * @return The instance of the registry
	 * @throws FailedAPIRequest
	 */
	public static IUpgradeRegistry getUpgradeRegistry() throws FailedAPIRequest {
		try {
			return (IUpgradeRegistry) Class.forName("com.austinv11.dartcraft2.DartCraft2").getDeclaredField("UPGRADE_REGISTRY").get(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedAPIRequest("Unknown exception retrieving the transmutation recipe handler");
		}
	}
	
	/**
	 * Attempts to find an aura controller near the provided location
	 * @param world The world for the controller
	 * @param x The x coord to start the search from
	 * @param y The y coord to start the search from
	 * @param z The z coord to start the search from
	 * @param range The range to search around
	 * @return The aura controller
	 */
	public static IAuraController getControllerForLocation(World world, int x, int y, int z, int range) {
		List<TileEntity> controllers = new ArrayList<TileEntity>();
		for (int i = 0-range; i < range+1; i++)
			for (int j = 0-range; j < range+1; j++)
				for (int k = 0-range; k < range+1; k++) {
					if (world.blockExists(x+i, y+j, z+k))
						if (!world.isAirBlock(x+i, y+j, z+k))
							if (world.getBlock(x+i, y+j, z+k) instanceof ITileEntityProvider)
								if (world.getTileEntity(x+i, y+j, z+k) instanceof IAuraController)
									controllers.add(world.getTileEntity(x+i, y+j, z+k));
				}
		TileEntity closestController = null;
		for (TileEntity te : controllers) {
			if (closestController == null)
				closestController = te;
			else {
				Vec3 start = Vec3.createVectorHelper((double) x, (double) y, (double) z);
				Vec3 original = Vec3.createVectorHelper(closestController.xCoord, closestController.yCoord, closestController.zCoord);
				Vec3 next = Vec3.createVectorHelper(te.xCoord, te.yCoord, te.zCoord);
				if (start.distanceTo(original) > start.distanceTo(next)) 
					closestController = te;
//				else if (start.distanceTo(original) == start.distanceTo(next)) TODO: account for this
			}
		}
		if (closestController != null)
			return (IAuraController) closestController;
		try {
			return (IAuraController) Class.forName("com.austinv11.dartcraft2.api.implementations.PassiveAuraController").getConstructor(World.class, Integer.class, Integer.class, Integer.class).newInstance(world, x, y, z);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // This should never be reached
	}
	
	/**
	 * Requests an aura burst from the nearest aura controller, if any. If none are found, aura is taken passively
	 * @param world The world of the block requesting aura
	 * @param x The x coord of the block
	 * @param y The y coord of the block
	 * @param z The z coord of the block
	 * @param range The range to look for the aura controller
	 * @throws FailedAPIRequest This is thrown if the block/tile entity requesting aura doesn't implement {@link IAuraAbsorber}
	 */
	public static void requestAura(World world, int x, int y, int z, int range) throws FailedAPIRequest {
		if (!world.blockExists(x, y, z) || world.isAirBlock(x, y, z))
			throw new FailedAPIRequest("The block at "+world.provider.dimensionId+":"+x+","+y+","+z+" is non-existant!");
		if (!(world.getBlock(x, y, z) instanceof IAuraAbsorber) && !(world.getBlock(x, y, z) instanceof ITileEntityProvider))
			throw new FailedAPIRequest("The block at "+world.provider.dimensionId+":"+x+","+y+","+z+" does not implement IAuraAbsorber!");
		else if (world.getBlock(x, y, z) instanceof ITileEntityProvider && !(world.getTileEntity(x, y, z) instanceof IAuraAbsorber))
			throw new FailedAPIRequest("The block at "+world.provider.dimensionId+":"+x+","+y+","+z+" does not implement IAuraAbsorber!");
		IAuraController controller = getControllerForLocation(world, x, y, z, range);
		controller.burst();
	}
	
	/**
	 * A helper method for finding all {@link IAuraAbsorber} around a point
	 * @param world The world of the point
	 * @param x The x coord of the point
	 * @param y The y coord of the point
	 * @param z The z coord of the point
	 * @param range The range
	 * @return All the {@link IAuraAbsorber}s around the point
	 */
	public static List<AuraLocation<IAuraAbsorber>> findAllAbsorbersWithinRange(World world, int x, int y, int z, int range) {
		ArrayList<AuraLocation<IAuraAbsorber>> list = new ArrayList<AuraLocation<IAuraAbsorber>>();
		for (int i = 0-range; i < range+1; i++)
			for (int j = 0-range; j < range+1; j++)
				for (int k = 0-range; k < range+1; k++) {
					if (world.blockExists(x+i, y+j, z+k))
						if (!world.isAirBlock(x+i, y+j, z+k))
							if (world.getBlock(x, y, z) instanceof IAuraAbsorber) {
								list.add(new AuraLocation<IAuraAbsorber>((IAuraAbsorber) world.getBlock(x, y, z), world, x, y, z));
							} else if (world.getBlock(x, y, z) instanceof ITileEntityProvider) {
								if (world.getTileEntity(x, y, z) instanceof IAuraAbsorber)
									list.add(new AuraLocation<IAuraAbsorber>((IAuraAbsorber) world.getTileEntity(x, y, z), world, x, y, z));
							}
				}
		return list;
	}
	
	/**
	 * A helper method for finding all {@link IAuraEmitter} around a point
	 * @param world The world of the point
	 * @param x The x coord of the point
	 * @param y The y coord of the point
	 * @param z The z coord of the point
	 * @param range The range
	 * @return All the {@link IAuraEmitter}s around the point
	 */
	public static List<AuraLocation<IAuraEmitter>> findAllEmittersWithinRange(World world, int x, int y, int z, int range) {
		ArrayList<AuraLocation<IAuraEmitter>> list = new ArrayList<AuraLocation<IAuraEmitter>>();
		for (int i = 0-range; i < range+1; i++)
			for (int j = 0-range; j < range+1; j++)
				for (int k = 0-range; k < range+1; k++) {
					if (world.blockExists(x+i, y+j, z+k))
						if (!world.isAirBlock(x+i, y+j, z+k))
							if (world.getBlock(x, y, z) instanceof IAuraEmitter) {
								list.add(new AuraLocation<IAuraEmitter>((IAuraEmitter) world.getBlock(x, y, z), world, x, y, z));
							} else if (world.getBlock(x, y, z) instanceof ITileEntityProvider) {
								if (world.getTileEntity(x, y, z) instanceof IAuraEmitter)
									list.add(new AuraLocation<IAuraEmitter>((IAuraEmitter) world.getTileEntity(x, y, z), world, x, y, z));
							}
				}
		return list;
	}
	
	/**
	 * A helper method for finding all {@link IPassiveAuraEmitter} around a point
	 * @param world The world of the point
	 * @param x The x coord of the point
	 * @param y The y coord of the point
	 * @param z The z coord of the point
	 * @param range The range
	 * @return All the {@link IPassiveAuraEmitter}s around the point
	 */
	public static List<AuraLocation<IPassiveAuraEmitter>> findAllPassiveEmittersWithinRange(World world, int x, int y, int z, int range) {
		ArrayList<AuraLocation<IPassiveAuraEmitter>> list = new ArrayList<AuraLocation<IPassiveAuraEmitter>>();
		for (int i = 0-range; i < range+1; i++)
			for (int j = 0-range; j < range+1; j++)
				for (int k = 0-range; k < range+1; k++) {
					if (world.blockExists(x+i, y+j, z+k))
						if (!world.isAirBlock(x+i, y+j, z+k))
							if (world.getBlock(x, y, z) instanceof IPassiveAuraEmitter) {
								list.add(new AuraLocation<IPassiveAuraEmitter>((IPassiveAuraEmitter) world.getBlock(x, y, z), world, x, y, z));
							} else if (world.getBlock(x, y, z) instanceof ITileEntityProvider) {
								if (world.getTileEntity(x, y, z) instanceof IPassiveAuraEmitter)
									list.add(new AuraLocation<IPassiveAuraEmitter>((IPassiveAuraEmitter) world.getTileEntity(x, y, z), world, x, y, z));
							}
				}
		return list;
	}
	
	/**
	 * Reads the list of active upgrades 
	 * @param stack The stack to scan
	 * @return The upgrades
	 * @throws FailedAPIRequest Thrown if the item cannot receive upgrades
	 */
	public static List<IForceUpgrade> getUpgradesFromStack(ItemStack stack) throws FailedAPIRequest {
		if (!(stack.getItem() instanceof IForceArmor) || !(stack.getItem() instanceof IForceTool))
			throw new FailedAPIRequest("Stack "+stack+" is ineligible for upgrades!");
		List<IForceUpgrade> upgrades = new ArrayList<IForceUpgrade>();
		NBTTagCompound info = NBTHelper.getCompoundTag(stack, "DC:UpgradeInfo");
		NBTTagList ups = info.getTagList("Upgrades", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < ups.tagCount(); i++) {
			NBTTagCompound tag = ups.getCompoundTagAt(i);
			IForceUpgrade upgrade = getUpgradeFromName(tag.getString("Name"));
			if (upgrade != null)
				upgrades.add(upgrade);
		}
		return upgrades;
	}
	
	/**
	 * Adds an upgrade to a stack
	 * @param stack The stack
	 * @param upgrade The upgrade to add
	 * @throws FailedAPIRequest Thrown if the item cannot receive upgrades
	 */
	public static void addUpgradeToStack(ItemStack stack, IForceUpgrade upgrade) throws FailedAPIRequest {
		if (!(stack.getItem() instanceof IForceArmor) || !(stack.getItem() instanceof IForceTool))
			throw new FailedAPIRequest("Stack "+stack+" is ineligible for upgrades!");
		NBTTagCompound info = NBTHelper.getCompoundTag(stack, "DC:UpgradeInfo");
		NBTTagList upgrades = info.hasKey("Upgrades") ? info.getTagList("Upgrades", Constants.NBT.TAG_COMPOUND) : 
				new NBTTagList();
		boolean didAdd = false;
		for (int i = 0; i < upgrades.tagCount(); i++) {
			NBTTagCompound tag = upgrades.getCompoundTagAt(i);
			if (tag.getString("Name").equals(upgrade.getUnlocalizedName())) {
				didAdd = true;
				tag.setInteger("Level", tag.getInteger("Level")+1);
				upgrades.func_150304_a(i, tag); //Set tag
				break;
			}
		}
		if (!didAdd) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("Name", upgrade.getUnlocalizedName());
			tag.setInteger("Level", 1);
			upgrades.appendTag(tag);
		}
		info.setTag("Upgrades", upgrades);
		NBTHelper.setCompoundTag(stack, "DC:UpgradeInfo", info);
	}
	
	/**
	 * Removes an upgrade to a stack
	 * @param stack The stack
	 * @param upgrade The upgrade to remove
	 * @throws FailedAPIRequest Thrown if the item cannot receive upgrades
	 */
	public static void removeUpgradeFromStack(ItemStack stack, IForceUpgrade upgrade) throws FailedAPIRequest {
		if (!(stack.getItem() instanceof IForceArmor) || !(stack.getItem() instanceof IForceTool))
			throw new FailedAPIRequest("Stack "+stack+" is ineligible for upgrades!");
		NBTTagCompound info = NBTHelper.getCompoundTag(stack, "DC:UpgradeInfo");
		NBTTagList upgrades = info.hasKey("Upgrades") ? info.getTagList("Upgrades", Constants.NBT.TAG_COMPOUND) :
				new NBTTagList();
		for (int i = 0; i < upgrades.tagCount(); i++) {
			NBTTagCompound tag = upgrades.getCompoundTagAt(i);
			if (tag.getString("Name").equals(upgrade.getUnlocalizedName())) {
				int level = tag.getInteger("Level");
				if (level > 1) {
					tag.setInteger("Level", level-1);
					upgrades.func_150304_a(i, tag); //Set tag
				} else {
					upgrades.removeTag(i);
				}
				break;
			}
		}
		info.setTag("Upgrades", upgrades);
		NBTHelper.setCompoundTag(stack, "DC:UpgradeInfo", info);
	}
	
	private static IForceUpgrade getUpgradeFromName(String name) throws FailedAPIRequest {
		Set<IForceUpgrade> upgrades = getUpgradeRegistry().getUpgrades();
		for (IForceUpgrade upgrade : upgrades)
			if (upgrade.getUnlocalizedName().equals(name))
				return upgrade;
		return null;
	}
}
