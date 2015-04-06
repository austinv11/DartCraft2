package com.austinv11.dartcraft2.proxy;

import com.austinv11.collectiveframework.minecraft.utils.IconManager;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

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
}
