package com.austinv11.dartcraft2;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;
import com.austinv11.dartcraft2.client.gui.GuiClipboard;
import com.austinv11.dartcraft2.reference.Reference;

public class NEIDartcraftConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        API.registerGuiOverlay(GuiClipboard.class, "crafting");
        API.registerGuiOverlayHandler(GuiClipboard.class, new DefaultOverlayHandler(), "crafting");
    }

    @Override
    public String getName() {
        return Reference.MOD_NAME + " NEI Plugin";
    }

    @Override
    public String getVersion() {
        return Reference.VERSION;
    }
}
