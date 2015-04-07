package com.austinv11.dartcraft2.blocks;

import com.austinv11.dartcraft2.init.ModFluids;
import com.austinv11.dartcraft2.reference.Reference;
import com.austinv11.dartcraft2.tileentities.TileEntityLiquidForce;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

public class BlockLiquidForce extends BlockFluidClassic implements ITileEntityProvider {
	
	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon flowingIcon;
	
	public BlockLiquidForce(Fluid fluid, Material material) {
		super(fluid, material);
		this.setBlockName("liquidForce");
	}
	
	public BlockLiquidForce() {
		this(ModFluids.liquidForce, Material.water);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return (side == 0 || side == 1)? stillIcon : flowingIcon;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		stillIcon = register.registerIcon(Reference.MOD_ID+":fluids/liquidForceStill");
		flowingIcon = register.registerIcon(Reference.MOD_ID+":fluids/liquidForceFlowing");
	}
	
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) 
			return false;
		return super.canDisplace(world, x, y, z);
	}
	
	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) 
			return false;
		return super.displaceIfPossible(world, x, y, z);
	}
	
	@Override
	public String getUnlocalizedName(){//Formats the name
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase()+":", getUnwrappedUnlocalizedName(getUnwrappedUnlocalizedName(super.getUnlocalizedName())));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){//Removes the "item." from the item name
		return unlocalizedName.substring(unlocalizedName.indexOf(".")+1);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
//		List<Entity> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x-1, y-1, z-1, x, y, z));
//		for (Entity entity : entities) {
//			if (entity instanceof EntityLivingBase && entity.isInWater()) {
//				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.regeneration.id, 1, 1, true));
//				entity.setAir(10);
//				if (entity instanceof EntityMob) {
//					((EntityMob) entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 1, 1, true));
//					for (int i = 0; i < 5; i++) {
//						ItemStack equipment = ((EntityMob) entity).getEquipmentInSlot(i);
//						if (equipment != null) {
//							world.spawnEntityInWorld(new EntityItem(world, x, y+1, z, equipment));
//							entity.setCurrentItemOrArmor(i, null);
//						}
//					}
//					if (!((EntityMob) entity).isChild())
//						if (entity instanceof EntityZombie) {
//							((EntityZombie) entity).setChild(true);
//						}
//				}
//			}
//		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityLiquidForce();
	}
}
