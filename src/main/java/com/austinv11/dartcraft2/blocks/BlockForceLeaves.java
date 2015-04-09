package com.austinv11.dartcraft2.blocks;

import com.austinv11.dartcraft2.creativetab.CreativeTabDC;
import com.austinv11.dartcraft2.init.ModBlocks;
import com.austinv11.dartcraft2.init.ModItems;
import com.austinv11.dartcraft2.particles.BreakEffect;
import com.austinv11.dartcraft2.proxy.ClientProxy;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockForceLeaves extends BlockLeaves {
	
	private Random rng = new Random();
	
	public String[][] textures = new String[][] {{"forceLeaves_Oak", "forceLeaves_Spruce", "forceLeaves_Birch", 
			"forceLeaves_Jungle"}, 
			{"forceLeaves_Oak_Opaque", "forceLeaves_Spruce_Opaque", "forceLeaves_Birch_Opaque", 
					"forceLeaves_Jungle_Opaque"}};
	public String[] variants = new String[] {"Oak", "Spruce", "Birch", "Jungle"};
	
	public BlockForceLeaves() {
		super();
		this.setBlockName("forceLeaves");
		this.setLightLevel(.6F);
		this.setLightOpacity(0);
		this.setCreativeTab(CreativeTabDC.DC_TAB);
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int chance) {
		return Item.getItemFromBlock(ModBlocks.forceSapling);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		if (MathHelper.getRandomIntegerInRange(rng, 0, 16-fortune) == 1) {
			drops.add(new ItemStack(ModBlocks.forceSapling, 1, damageDropped(metadata)));
		}
		if (MathHelper.getRandomIntegerInRange(rng, 0, 10-fortune) == 1) {
			drops.add(new ItemStack(ModItems.forceNugget));
		}
		return drops;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
//	@Override
//	@SideOnly(Side.CLIENT)
//	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
//		return Minecraft.getMinecraft().gameSettings.fancyGraphics || super.shouldSideBeRendered(world, x, y, z, side);
//	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if (MathHelper.getRandomIntegerInRange(random, 0, 3) == 1) {
			BreakEffect effect = new BreakEffect(world, x+.5+(random.nextGaussian()/3), y+.5+(random.nextGaussian()/3), z+.5+(random.nextGaussian()/3), random.nextGaussian(), random.nextGaussian(), random.nextGaussian());
			effect.setGravity(.25F);
			Minecraft.getMinecraft().effectRenderer.addEffect(effect);
		}
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 16777215;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int side) {
		return 16777215;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return 16777215;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return (meta & 3) == 1 ? this.field_150129_M[Minecraft.getMinecraft().gameSettings.fancyGraphics ? 0 : 1][1] : ((meta & 3) == 3 ? this.field_150129_M[Minecraft.getMinecraft().gameSettings.fancyGraphics ? 0 : 1][3] : ((meta & 3) == 2 ? this.field_150129_M[Minecraft.getMinecraft().gameSettings.fancyGraphics ? 0 : 1][2] : this.field_150129_M[Minecraft.getMinecraft().gameSettings.fancyGraphics ? 0 : 1][0]));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List items) {
		for (int i = 0; i < variants.length; i++)
			items.add(new ItemStack(item, 1, i));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		for (int i = 0; i < textures.length; ++i) {
			this.field_150129_M[i] = new IIcon[textures[i].length];
			
			for (int j = 0; j < textures[i].length; ++j) {
				this.field_150129_M[i][j] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase()+":"+textures[i][j]);
			}
		}
	}
	
	@Override
	public String getUnlocalizedName(){//Formats the name
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase()+":", getUnwrappedUnlocalizedName(getUnwrappedUnlocalizedName(super.getUnlocalizedName())));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){//Removes the "item." from the item name
		return unlocalizedName.substring(unlocalizedName.indexOf(".")+1);
	}
	
	@Override
	public String[] func_150125_e() {
		return variants;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
		if (world.getBlock(x, y, z) instanceof BlockForceLeaves) {
			ClientProxy.addBlockEffects(world, x, y, z, effectRenderer, rng);
			return true;
		}
		return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
		Double[] coords = getOffsetCoordsForSide(target.blockX+.5, target.blockY+.5, target.blockZ+.5, target.sideHit);
		effectRenderer.addEffect(new BreakEffect(worldObj, coords[0], coords[1], coords[2], rng.nextGaussian(), rng.nextGaussian(), rng.nextGaussian()));
		return true;
	}
	
	private Double[] getOffsetCoordsForSide(double x, double y, double z, int side) {
		List<Double> coords = new ArrayList<Double>();
		switch (side) {
			case 0: //Bottom
				y -= .75;
				x += rng.nextGaussian()/3;
				z += rng.nextGaussian()/3;
				break;
			case 1: //Top
				y += .75;
				x += rng.nextGaussian()/3;
				z += rng.nextGaussian()/3;
				break;
			case 2: //East
				z -= .75;
				x += rng.nextGaussian()/3;
				y += rng.nextGaussian()/3;
				break;
			case 3: //West
				z += .75;
				x += rng.nextGaussian()/3;
				y += rng.nextGaussian()/3;
				break;
			case 4: //North
				x -= .75;
				y += rng.nextGaussian()/3;
				z += rng.nextGaussian()/3;
				break;
			case 5: //South
				x += .75;
				y += rng.nextGaussian()/3;
				z += rng.nextGaussian()/3;
				break;
		}
		coords.add(x);
		coords.add(y);
		coords.add(z);
		return coords.toArray(new Double[3]);
	}
}
