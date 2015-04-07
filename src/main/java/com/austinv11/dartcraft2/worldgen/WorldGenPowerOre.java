package com.austinv11.dartcraft2.worldgen;

import com.austinv11.collectiveframework.minecraft.utils.WorldGenHelper;
import com.austinv11.dartcraft2.init.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

/**
 * Spawns power ore in the overworld and nether.
 * It spawns in the overworld between y levels 1 and 64, nether 1 and 256.
 * It spawns in veins of 1 to 5 ores which spawn anywhere within a 3x3x3 area of the center block.
 * There are 3-10 veins in every chunk.
 */
public class WorldGenPowerOre implements IWorldGenerator {
	
	private static final int MAX_GEN_PASSES = 8; //Maximum number of tries for finding a suitable location for each vein
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == -1 || world.provider.dimensionId == 0) {
			genVein(random, world, chunkX, chunkZ);
		}
	}
	
	private void genVein(Random rng, World world, int chunkX, int chunkZ) {
		int numOfVeins = MathHelper.getRandomIntegerInRange(rng, 3, 10);
		outerLoop: for (int i = 0; i < numOfVeins; i++) {
			int x = (chunkX*16) + MathHelper.getRandomIntegerInRange(rng, 0, 15);
			int z = (chunkZ*16) + MathHelper.getRandomIntegerInRange(rng, 0, 15);
			int y;
			int passes = 0;
			do {
				y = MathHelper.getRandomIntegerInRange(rng, 1,  world.provider.dimensionId == 0 ? 64 : 128);
				if (passes++ > MAX_GEN_PASSES) {
					continue outerLoop;
				}
			} while (world.isAirBlock(x, y, z));
			int numOfOres = MathHelper.getRandomIntegerInRange(rng, 1, 5);
			WorldGenHelper.spawnOreBlock(world, x, y, z, world.provider.dimensionId == 0 ? ModBlocks.powerOre : ModBlocks.netherPowerOre);
			for (int j = 0; j < numOfOres-1; j++) {
				int newX = MathHelper.getRandomIntegerInRange(rng, x-1, x+1);
				int newZ =  MathHelper.getRandomIntegerInRange(rng, z-1, z+1);
				int newY =  MathHelper.getRandomIntegerInRange(rng, y-1, y+1);
				if (!WorldGenHelper.isLocationSuitableForOre(world, newX, newY, newZ))
					WorldGenHelper.spawnOreBlock(world, newX, newY, newZ, world.provider.dimensionId == 0 ? ModBlocks.powerOre : ModBlocks.netherPowerOre);
			}
		}
	}
}
