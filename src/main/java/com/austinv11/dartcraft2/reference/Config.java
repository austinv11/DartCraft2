package com.austinv11.dartcraft2.reference;

import com.austinv11.collectiveframework.minecraft.config.Description;

@com.austinv11.collectiveframework.minecraft.config.Config(fileName = "DartCraft2.cfg")
public class Config {
	
	@Description(category = "Liquid Force", comment = "When enabled, liquid force will apply effects to entities submerged in it")
	public static boolean enableExtraLiquidForceEffects = true;
	
	@Description(category = "Aura", comment = "The range at which aura can be passively drained from")
	public static int passiveAuraEmissionRange = 16;
	
	@Description(category = "World Gen", comment = "The minimum amount of power ore veins in each chunk")
	public static int minPowerOreVeins = 3;
	
	@Description(category = "World Gen", comment = "The maximum amount of power ore veins in each chunk")
	public static int maxPowerOreVeins = 10;
	
	@Description(category = "World Gen", comment = "The minimum amount of power ore per vein")
	public static int minPowerOrePerVein = 1;
	
	@Description(category = "World Gen", comment = "The maximum amount of power ore per vein")
	public static int maxPowerOrePerVein = 5;
	
	@Description(category = "World Gen", comment = "The lowest y-level power ore can spawn in, in the overworld")
	public static int minYLevelOverworld = 1;
	
	@Description(category = "World Gen", comment = "The highest y-level power ore can spawn in, in the overworld")
	public static int maxYLevelOverworld = 64;
	
	@Description(category = "World Gen", comment = "The lowest y-level power ore can spawn in, in the nether")
	public static int minYLevelNether = 1;
	
	@Description(category = "World Gen", comment = "The highest y-level power ore can spawn in, in the nether")
	public static int maxYLevelNether = 256;
	
	@Description(category = "World Gen", comment = "Whether to spawn overworld power ore")
	public static boolean spawnOverworldPowerOre = true;
	
	@Description(category = "World Gen", comment = "Whether to spawn nether power ore")
	public static boolean spawnNetherPowerOre = true;
	
	@Description(category = "Infusion", comment = "Experience required for tier 1")
	public static int experienceForTier1 = 1;
	
	@Description(category = "Infusion", comment = "Experience required for tier 2")
	public static int experienceForTier2 = 1;
	
	@Description(category = "Infusion", comment = "Experience required for tier 3")
	public static int experienceForTier3 = 1;
	
	@Description(category = "Infusion", comment = "Experience required for tier 4")
	public static int experienceForTier4 = 1;
	
	@Description(category = "Infusion", comment = "Experience required for tier 5")
	public static int experienceForTier5 = 1;
	
	@Description(category = "Infusion", comment = "Experience required for tier 6")
	public static int experienceForTier6 = 1;
	
	@Description(category = "Infusion", comment = "Experience required for tier 7")
	public static int experienceForTier7 = 1;
}
