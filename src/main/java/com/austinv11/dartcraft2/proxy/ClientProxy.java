package com.austinv11.dartcraft2.proxy;

import com.austinv11.collectiveframework.minecraft.utils.IconManager;
import com.austinv11.dartcraft2.client.KeyBindings;
import com.austinv11.dartcraft2.events.handlers.KeyInputHandler;
import com.austinv11.dartcraft2.particles.BreakEffect;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy implements IconManager.IIconNeeded {
	
	public static IIcon BREAK_EFFECT_TEXTURE;
	
	@Override
	public void registerClient() {
		IconManager.register(this);
	}
	
	@Override
	public void registerIcons(IIconRegister register) {
		BREAK_EFFECT_TEXTURE = register.registerIcon(Reference.MOD_ID.toLowerCase()+":particles/breakEffect");
	}
	
	public static void addBlockEffects(World world, int x, int y, int z, EffectRenderer renderer, Random random) {
		if (world.blockExists(x, y, z))
			for (int i = 0; i < 7; i++)
				renderer.addEffect(new BreakEffect(world, x+.5+(random.nextGaussian()/3), y+.5+(random.nextGaussian()/3), 
						z+.5+(random.nextGaussian()/3), random.nextGaussian(), random.nextGaussian(), random.nextGaussian()));
	}

    @Override
    public void handleKeyBindings() {
        KeyBindings.init();
        FMLCommonHandler.instance().bus().register(new KeyInputHandler());
    }
}
