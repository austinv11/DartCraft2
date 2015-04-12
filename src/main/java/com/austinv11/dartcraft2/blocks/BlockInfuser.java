package com.austinv11.dartcraft2.blocks;

import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.reference.Reference;
import com.austinv11.dartcraft2.tileentities.TileEntityInfuser;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInfuser extends BlockDC implements ITileEntityProvider {
	
	public BlockInfuser() {
		super();
		this.setBlockName("infuser");
		this.setBlockBounds(0, 0, 0, 1, .5F, 1);
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityInfuser();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ){
		TileEntity te = world.getTileEntity(x, y, z);
		if (!world.isRemote) {
			if (te != null && te instanceof TileEntityInfuser)
				player.openGui(DartCraft2.instance, Reference.GUIs.INFUSER.ordinal(), world, x, y, z);
		}
		return true;
	}
}
