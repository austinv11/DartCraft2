package com.austinv11.dartcraft2.items;

import com.austinv11.collectiveframework.minecraft.items.ItemBase;
import com.austinv11.dartcraft2.creativetab.CreativeTabDC;
import com.austinv11.dartcraft2.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;

public class ItemDC extends ItemBase {

    public ItemDC() {
        super();
    }

    @Override
    public CreativeTabs getTab() {
        return CreativeTabDC.DC_TAB;
    }

    @Override
    public String getModId() {
        return Reference.MOD_ID;
    }
}
