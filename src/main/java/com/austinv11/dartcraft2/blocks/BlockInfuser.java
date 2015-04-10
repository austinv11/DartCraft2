package com.austinv11.dartcraft2.blocks;

import com.austinv11.dartcraft2.tileentities.TileEntityInfuser;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInfuser extends BlockDC implements ITileEntityProvider {
	
	public BlockInfuser() {
		super();
		this.setBlockName("infuser");
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
}
