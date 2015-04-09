package com.austinv11.dartcraft2.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

public class ItemBlockForceSapling extends ItemBlock {

    public ItemBlockForceSapling(Block block) {
        super(block);
        this.hasSubtypes = true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return this.field_150939_a.getIcon(1, damage);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }
}
