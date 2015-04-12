package com.austinv11.dartcraft2.tileentities;

import com.austinv11.collectiveframework.minecraft.tiles.TileEntityInventory;
import com.austinv11.collectiveframework.minecraft.utils.NBTHelper;
import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.init.ModFluids;
import com.austinv11.dartcraft2.init.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityInfuser extends TileEntityInventory implements IFluidHandler {
	
	public TileEntityEnchantmentTable dummyTable;
	public static String publicName = "infuser";
	public FluidTank liquidForceTank = new FluidTank(ModFluids.liquidForce, 0, 10000);
	
	public TileEntityInfuser() {
		super();
		this.size = 11;
		this.items = new ItemStack[size];
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		liquidForceTank.writeToNBT(nbtTagCompound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		liquidForceTank.readFromNBT(nbtTagCompound);
	}
	
	@Override
	public void validate() {
		super.validate();
		dummyTable = new TileEntityEnchantmentTable();
		dummyTable.setWorldObj(this.getWorldObj());
		dummyTable.xCoord = this.xCoord;
		dummyTable.yCoord = this.yCoord;
		dummyTable.zCoord = this.zCoord;
		dummyTable.blockMetadata = Integer.MIN_VALUE;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (dummyTable != null)
			dummyTable.updateEntity();
		if (getStackInSlot(1) != null) {
			if (getStackInSlot(1).getItem() == ModItems.liquidForceBucket) {
				if (liquidForceTank.fill(new FluidStack(ModFluids.liquidForce, 1000), false) == 1000) {
					liquidForceTank.fill(new FluidStack(ModFluids.liquidForce, 1000), true);
					setInventorySlotContents(1, new ItemStack(Items.bucket));
				}
			} else if (isForceGem(getStackInSlot(1))) {
				if (liquidForceTank.fill(new FluidStack(ModFluids.liquidForce, 1000), false) == 1000) {
					liquidForceTank.fill(new FluidStack(ModFluids.liquidForce, 1000), true);
					ItemStack stack = getStackInSlot(1).copy();
					stack.stackSize--;
					setInventorySlotContents(1, stack.stackSize == 0 ? null : stack);
					NBTTagCompound tag = new NBTTagCompound();
					writeToNBT(tag);
//					CollectiveFramework.NETWORK.sendToServer(new TileEntityUpdatePacket(worldObj, xCoord, yCoord, zCoord, tag));
				}
			}
		}
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 3, tag);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
	}
	
	public int getTier() {
		if (getStackInSlot(0) != null)
			return NBTHelper.getInt(getStackInSlot(0), "tier");
		return 0;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		switch (slot) {
			case 0:
				return stack.getItem() == ModItems.upgradeTome;
			case 1:
				return isForceGem(stack);
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				return DartCraft2.UPGRADE_REGISTRY.getRegisteredItems().contains(stack.getItem());
			case 10:
				return DartCraft2.UPGRADE_REGISTRY.getTools().contains(stack.getItem());
		}
		return true;
	}
	
	private boolean isForceGem(ItemStack stack) {
		for (int id : OreDictionary.getOreIDs(stack)) {
			if (OreDictionary.getOreName(id).equals("gemForce"))
				return true;
		}
		return stack.getItem() == ModItems.liquidForceBucket;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource.getFluid() == ModFluids.liquidForce)
			return liquidForceTank.fill(resource, doFill);
		return 0;
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return liquidForceTank.drain(resource.amount, doDrain);
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return liquidForceTank.drain(maxDrain, doDrain);
	}
	
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return fluid == ModFluids.liquidForce;
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return fluid == ModFluids.liquidForce;
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{liquidForceTank.getInfo()};
	}
}
