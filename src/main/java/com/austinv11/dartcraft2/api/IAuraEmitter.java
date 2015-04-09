package com.austinv11.dartcraft2.api;

import net.minecraft.world.World;

/**
 * Implement this in blocks in order to have them emit a custom amount of forceful aura
 */
public interface IAuraEmitter {

    /**
     * Gets the forceful aura passively emitted per tick by this block
     *
     * @return The aura emitted
     */
    public int getAuraEmitted();

    /**
     * This is called when forceful aura is forcibly drained from the block
     *
     * @param world  The world the block is located in
     * @param x      The x coord of the block
     * @param y      The y coord of the block
     * @param z      The z coord of the block
     * @param amount The amount drained
     * @return The amount of aura drained
     */
    public int suckAura(World world, int x, int y, int z, int amount);

    /**
     * Called to get the strength of the forceful aura emitted, essentially the range
     *
     * @return The percentage, 0.0F-1.0F, 1.0F being as strong as possible
     */
    public float getPotency();
}
