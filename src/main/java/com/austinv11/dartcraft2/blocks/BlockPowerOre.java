package com.austinv11.dartcraft2.blocks;

import com.austinv11.dartcraft2.init.ModItems;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockPowerOre extends BlockDC {
	
	private Random rng = new Random();
	
	public BlockPowerOre() {
		super();
		this.setBlockName("powerOre");
		this.setHardness(3F);
		this.lightValue = 5;
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortuneLevel) { //Not sure about the params
		return ModItems.forceGem;
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortuneLevel, Random random) { //Taken from redstone ore
		return this.quantityDropped(random) + random.nextInt(fortuneLevel + 1);
	}
	
	@Override
	public int quantityDropped(Random random) { //Taken from redstone ore
		return 4 + random.nextInt(2);
	}
	
	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune) { //Taken from redstone ore
		if (this.getItemDropped(meta, rng, fortune) != Item.getItemFromBlock(this)) {
			return 1 + rng.nextInt(5);
		}
		return 0;
	}
	
	
}
