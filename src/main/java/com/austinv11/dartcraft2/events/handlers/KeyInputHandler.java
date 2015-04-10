package com.austinv11.dartcraft2.events.handlers;

import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.client.KeyBindings;
import com.austinv11.dartcraft2.init.ModItems;
import com.austinv11.dartcraft2.network.OpenGuiContainerPacket;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class KeyInputHandler  {

    private Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.openForceBelt.isPressed()) {
            ItemStack forceBelt = getBeltInBar(mc.thePlayer);
            if (forceBelt != null) {
                DartCraft2.NETWORK.sendToServer(new OpenGuiContainerPacket(Reference.GUIs.FORCE_BELT.ordinal()));
            }
        }
    }

    private ItemStack getBeltInBar(EntityPlayer player) {
        for (int i = 0; i < 9; i++) {
            if (player.inventory.getStackInSlot(i).getItem() == ModItems.forceBelt) {
                return player.inventory.getStackInSlot(i);
            }
        }
        return null;
    }
}
