package com.austinv11.dartcraft2.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBindings {
    public static KeyBinding openForceBelt;

    public static void init() {
        openForceBelt = new KeyBinding("key.forceBelt", Keyboard.KEY_Z, "key.categories.dartcraft2");

        ClientRegistry.registerKeyBinding(openForceBelt);
    }
}
