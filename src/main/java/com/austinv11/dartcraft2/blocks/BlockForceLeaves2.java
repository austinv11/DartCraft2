package com.austinv11.dartcraft2.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockForceLeaves2 extends BlockForceLeaves {
	
	public BlockForceLeaves2() {
		super();
		textures = new String[][]{{"forceLeaves_Acacia", "forceLeaves_Big_Oak"}, {"forceLeaves_Acacia_Opaque", "forceLeaves_Big_Oak_Opaque"}};
		variants = new String[]{"Acacia", "Big_Oak"};
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return (meta & 3) == 1 ? this.field_150129_M[Minecraft.getMinecraft().gameSettings.fancyGraphics ? 0 : 1][1] : this.field_150129_M[Minecraft.getMinecraft().gameSettings.fancyGraphics ? 0 : 1][0];
	}
	
	@Override
	public int damageDropped(int meta) {
		return (meta & 3) + 4;
	}
	
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) & 3;
	}
}
