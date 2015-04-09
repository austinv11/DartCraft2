package com.austinv11.dartcraft2.init;

import com.austinv11.dartcraft2.blocks.BlockLiquidForce;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {
	
	public static Fluid liquidForce = new Fluid("liquidForce").setLuminosity(15).setDensity(700).setGaseous(false).setTemperature(240).setViscosity(700);
	
	public static void init() {
		FluidRegistry.registerFluid(liquidForce);
		ModBlocks.liquidForce = new BlockLiquidForce(liquidForce, Material.water).setBlockName("liquidForce");
		GameRegistry.registerBlock(ModBlocks.liquidForce, "liquidForce");
		liquidForce = liquidForce.setBlock(ModBlocks.liquidForce).setUnlocalizedName(ModBlocks.liquidForce.getUnlocalizedName());
	}
}
