package com.austinv11.dartcraft2.reference;

import com.austinv11.collectiveframework.minecraft.config.Description;

@com.austinv11.collectiveframework.minecraft.config.Config(fileName = "DartCraft2.cfg")
public class Config {
	
	@Description(category = "Liquid Force", comment = "When enabled, liquid force will apply effects to entities submerged in it")
	public static boolean enableExtraLiquidForceEffects = true;
	
	@Description(category = "Aura", comment = "The range at which aura can be passively drained from")
	public static int passiveAuraEmissionRange = 16;
}
