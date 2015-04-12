package com.austinv11.dartcraft2.items;

import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemForcePack extends ItemDC{
    public ItemForcePack() {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName("forcePack");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.isSneaking()) {
            if (stack.getItemDamage() == 4) {
                stack.setItemDamage(0);
            } else {
                stack.setItemDamage(stack.getItemDamage() + 1);
            }
        } else {
            player.openGui(DartCraft2.instance, Reference.GUIs.FORCE_PACK.ordinal(), player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ);
        }

        return stack;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
        if (world.getBlock(x, y, z) == Blocks.cauldron) {
            BlockCauldron cauldron = (BlockCauldron) world.getBlock(x, y, z);
            ItemForcePack forcePack = (ItemForcePack) stack.getItem();
            forcePack.removeColor(stack);
            cauldron.func_150024_a(world, x, y, z, world.getBlockMetadata(x, y, z) - 1);
            return true;
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int color)
    {
        if (color > 0)
        {
            return 16777215;
        }
        else
        {
            int j = this.getColor(stack);

            if (j < 0)
            {
                j = 16777215;
            }

            return j;
        }
    }

    /**
     * Return whether the specified armor ItemStack has a color.
     */
    public boolean hasColor(ItemStack stack)
    {
        return !stack.hasTagCompound() ? false : (!stack.getTagCompound().hasKey("display", 10) ? false : stack.getTagCompound().getCompoundTag("display").hasKey("color", 3));
    }


    /**
     * Return the color for the specified armor ItemStack.
     */
    public int getColor(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound == null)
        {
            return 10511680;
        }
        else
        {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
            return nbttagcompound1 == null ? 10511680 : (nbttagcompound1.hasKey("color", 3) ? nbttagcompound1.getInteger("color") : 10511680);
        }
    }

    public void setNBTTagColor(ItemStack stack, int color)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound == null)
        {
            nbttagcompound = new NBTTagCompound();
            stack.setTagCompound(nbttagcompound);
        }

        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

        if (!nbttagcompound.hasKey("display", 10))
        {
            nbttagcompound.setTag("display", nbttagcompound1);
        }

        nbttagcompound1.setInteger("color", color);
    }

    public void removeColor(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null)
        {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (nbttagcompound1.hasKey("color"))
            {
                nbttagcompound1.removeTag("color");
            }
        }
    }
}
