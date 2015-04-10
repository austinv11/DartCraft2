package com.austinv11.dartcraft2.api.implementations;

import com.austinv11.dartcraft2.api.*;
import com.austinv11.dartcraft2.reference.Config;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PassiveAuraController implements IAuraController {
	
	private World world;
	private int x, y, z, range;
	
	public PassiveAuraController(World world, int x, int y, int z) {
		this(world, x, y, z, Config.passiveAuraEmissionRange);
	}
	
	public PassiveAuraController(World world, int x, int y, int z, int range) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.range = range;
	}
	
	@Override
	public void burst() {
		int aura = 0;
		for (AuraLocation<IPassiveAuraEmitter> emitter : DartCraft2API.findAllPassiveEmittersWithinRange(world, x, y, z, range)) {
			double maxRange = emitter.getAuraObject().getPotency() * range;
			Vec3 from = Vec3.createVectorHelper(x, y, z);
			Vec3 to = Vec3.createVectorHelper(emitter.getX(), emitter.getY(), emitter.getZ());
			if (from.distanceTo(to) <= maxRange) {
				aura += emitter.getAuraObject().getAuraEmitted();
			}
		}
		for (AuraLocation<IAuraAbsorber> absorber : DartCraft2API.findAllAbsorbersWithinRange(world, x, y, z, range)) {
			if (aura <= 0)
				break;
			aura -= absorber.getAuraObject().receiveAuraBurst(aura);
		}
	}
}
