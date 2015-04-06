package com.austinv11.dartcraft2.particles;

import com.austinv11.dartcraft2.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class BreakEffect extends EntityFX {
	
	protected BreakEffect(World world, double x, double y, double z) {
		super(world, x, y, z);
		this.particleGravity = .15F;
		this.setParticleIcon(ClientProxy.BREAK_EFFECT_TEXTURE);
	}
	
	public BreakEffect(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		this.motionX = motionX*.055;
		this.motionY = motionY*.055;
		this.motionZ = motionZ*.055;
		this.particleGravity = .15F;
		this.setParticleIcon(ClientProxy.BREAK_EFFECT_TEXTURE);
	}
	
	@Override
	public int getFXLayer() {
		return 1;
	}
}
