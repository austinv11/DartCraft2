package com.austinv11.dartcraft2.api;

/**
 * Implement this in tile entities or blocks to have the block passively emit aura
 */
public interface IPassiveAuraEmitter {
	
	/**
	 * Gets the forceful aura passively emitted per tick by this block 
	 * @return The aura emitted
	 */
	public int getAuraEmitted();
	
	/**
	 * Called to get the strength of the forceful aura emitted, essentially the range
	 * @return The percentage, 0.0F-1.0F, 1.0F being as strong as possible
	 */
	public float getPotency();
}
