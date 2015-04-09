package com.austinv11.dartcraft2.blocks;

import com.austinv11.dartcraft2.creativetab.CreativeTabDC;
import com.austinv11.dartcraft2.particles.BreakEffect;
import com.austinv11.dartcraft2.proxy.ClientProxy;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockForceLog extends BlockLog {
	
	private Random rng = new Random();
	public String[] variants = new String[]{"Oak", "Spruce", "Birch", "Jungle"};
	
	public BlockForceLog() {
		super();
		this.setBlockName("forceLog");
		this.setCreativeTab(CreativeTabDC.DC_TAB);
		this.setHardness(2.0F);
		this.setStepSound(soundTypeWood);
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
		this.field_150167_a = new IIcon[variants.length];
		this.field_150166_b = new IIcon[variants.length];
		
		for (int i = 0; i < this.field_150167_a.length; ++i) {
			this.field_150167_a[i] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())) + "_" + variants[i]);
			this.field_150166_b[i] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())) + "_" + variants[i] + "_top");
		}
	}
	
	@Override
	public String getUnlocalizedName(){//Formats the name
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase()+":", getUnwrappedUnlocalizedName(getUnwrappedUnlocalizedName(super.getUnlocalizedName())));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){//Removes the "item." from the item name
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
		if (world.getBlock(x, y, z) instanceof BlockForceLog) {
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
