/**
 * A brief explanation of the aura system:
 * The aura system in dartcraft 2 centers around a magical force called "forceful aura". All blocks and items have some
 * aura within, but it is unnoticeable because the traces of it are so weak. Other more mystical blocks are imbued with
 * greater, stronger aura. These passively emit very small amounts of aura. In order to do anything useful with aura, 
 * you need to forcibly drain aura out of these mystical blocks and items. However, aura is both a rare and finite 
 * resource. So aura must be drained with frugality and with caution. Aura is also not like most power systems, aura is
 * an "on-demand" resource. That means there is no such thing as internal buffers. Aura is only emitted when needed. 
 * This means that the player must make difficult choices about how their aura system works. Whenever a 
 * crafting/infusion operation is done, aura is required as well as liquid force. A little liquid force is a catalyst 
 * for all mystical reactions.
 * 
 * A brief, programmatic explanation of the aura system:
 * All mystical blocks implement {@link com.austinv11.dartcraft2.api.IPassiveAuraEmitter} which allows them to slowly 
 * and passively emit aura. Some blocks can be forcibly drained of its aura, these implement {@link com.austinv11.dartcraft2.api.IAuraEmitter}.
 * Finally, blocks who require aura to do anything implement {@link com.austinv11.dartcraft2.api.IAuraAbsorber} (<b>Note, 
 * please be responsible and not take more aura from a burst than exists</b>). The aura system is centered around
 * {@link com.austinv11.dartcraft2.api.IAuraController}s, these control the flow of aura, whether passive or forced.
 * Blocks should only ever interact with one aura controller, the closest one or the passive aura controller, never more
 * than one. In order to retrieve the proper aura controller, utilize the {@link com.austinv11.dartcraft2.api.DartCraft2API#getControllerForLocation(net.minecraft.world.World, int, int, int, int)}
 * method.
 * 
 * Phew, that was a lot.
 */
@API(owner = "DartCraft2", provides = "DartCraft2|API", apiVersion = "@VERSION@")
package com.austinv11.dartcraft2.api;

import cpw.mods.fml.common.API;
