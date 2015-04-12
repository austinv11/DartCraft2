package com.austinv11.dartcraft2.items;

import com.austinv11.collectiveframework.minecraft.utils.Colors;
import com.austinv11.collectiveframework.minecraft.utils.NBTHelper;
import com.austinv11.dartcraft2.reference.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemUpgradeTome extends ItemDC {
	
	public ItemUpgradeTome() {
		super();
		this.setUnlocalizedName("upgradeTome");
		this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List information, boolean isAdvanced) {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			information.add(Colors.AQUA+StatCollector.translateToLocal("lore.upgradeTome.tier")+": "+Colors.YELLOW+NBTHelper.getInt(itemStack, "tier"));
			information.add(Colors.AQUA+StatCollector.translateToLocal("lore.upgradeTome.xp")+": "+Colors.YELLOW+NBTHelper.getInt(itemStack, "xp"));
			int xpNeeded = getXpNeededForNextTier(itemStack);
			if (xpNeeded >= 0)
				information.add(Colors.AQUA+StatCollector.translateToLocal("lore.upgradeTome.xpToNextTier")+": "+Colors.YELLOW+xpNeeded);
		} else 
			information.add(Colors.GRAY+StatCollector.translateToLocal("lore.shiftMessage"));
	}
	
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isInHand) {
		int xp = getXpNeededForNextTier(itemStack);
		if (xp <= 0 && xp != Integer.MIN_VALUE) {
			int remaining = Math.abs(xp);
			NBTHelper.setInteger(itemStack, "xp", remaining);
			NBTHelper.setInteger(itemStack, "tier", NBTHelper.getInt(itemStack, "tier")+1);
		}
	}
	
	private int getXpNeededForNextTier(ItemStack stack) {
		try {
			if (NBTHelper.getInt(stack, "tier") != 7) {
				return Config.class.getDeclaredField("experienceForTier"+(NBTHelper.getInt(stack, "tier")+1)).getInt(null)-NBTHelper.getInt(stack, "xp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.MIN_VALUE;
	}
}
