package com.austinv11.dartcraft2.blocks;

import com.austinv11.dartcraft2.init.ModFluids;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockLiquidForce extends BlockFluidClassic {
	
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
		return (side == 0 || side == 1) ? stillIcon : flowingIcon;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		stillIcon = register.registerIcon(Reference.MOD_ID+":fluids/liquidForceStill");
		flowingIcon = register.registerIcon(Reference.MOD_ID+":fluids/liquidForceFlowing");
		this.blockIcon = stillIcon;
		ModFluids.liquidForce.setIcons(stillIcon, flowingIcon);
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
	public String getUnlocalizedName(){
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase()+":", getUnwrappedUnlocalizedName(getUnwrappedUnlocalizedName(super.getUnlocalizedName())));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){
		return unlocalizedName.substring(unlocalizedName.indexOf(".")+1);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		super.onEntityCollidedWithBlock(world, x, y, z, entity);
		
		if (entity instanceof EntityLivingBase) {
			
			if (((EntityLivingBase) entity).getActivePotionEffect(Potion.regeneration) == null)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.regeneration.id, 80, 0, true));
			entity.setAir(300);
			
			if (entity instanceof EntityMob) {
				if (((EntityMob) entity).getActivePotionEffect(Potion.weakness) == null)
					((EntityMob) entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 80, 0, true));
				
				for (int i = entity instanceof EntitySkeleton ? 1 : 0; i < 5; i++) {
					ItemStack equipment = ((EntityMob) entity).getEquipmentInSlot(i);
					if (equipment != null) {
						world.spawnEntityInWorld(new EntityItem(world, x, y+1, z, equipment));
						entity.setCurrentItemOrArmor(i, null);
					}
				}
				
				if (!((EntityMob) entity).isChild())
					if (entity instanceof EntityZombie) {
						((EntityZombie) entity).setChild(true);
					}
			}
		}
	}
}
