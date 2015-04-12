package com.austinv11.dartcraft2.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.reference.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemForceBelt extends ItemDC implements IBauble {
    public ItemForceBelt() {
        super();
        this.setUnlocalizedName("forceBelt");
        this.setMaxDamage(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.openGui(DartCraft2.instance, Reference.GUIs.FORCE_BELT.ordinal(), player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ);
        return stack;
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.BELT;
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {

    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {

    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {

    }
}
