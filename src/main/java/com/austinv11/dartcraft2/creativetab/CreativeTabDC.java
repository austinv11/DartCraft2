package com.austinv11.dartcraft2.creativetab;

import com.austinv11.dartcraft2.init.ModItems;
import com.austinv11.dartcraft2.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabDC {

    public static final CreativeTabs DC_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

        @Override
        public Item getTabIconItem() {
            return ModItems.forceGem;
        }
    };
}
