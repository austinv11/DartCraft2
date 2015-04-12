package com.austinv11.dartcraft2.events.handlers;

import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.client.KeyBindings;
import com.austinv11.dartcraft2.network.OpenGuiContainerPacket;
import com.austinv11.dartcraft2.reference.Reference;
import com.austinv11.dartcraft2.utils.DartCraftUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class KeyInputHandler {

    private Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.openForceBelt.isPressed()) {
            ItemStack forceBelt = DartCraftUtils.getCorrectForceBelt(mc.thePlayer);
            if (forceBelt != null) {
                DartCraft2.NETWORK.sendToServer(new OpenGuiContainerPacket(Reference.GUIs.FORCE_BELT.ordinal()));
            }
        }
    }
}
