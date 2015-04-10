package com.austinv11.dartcraft2.api;

/**
 * Implement this in tile entities or blocks in order to have them emit a custom amount of forceful aura
 */
public interface IAuraEmitter extends IPassiveAuraEmitter {
	
	/**
	 * This is called when forceful aura is forcibly drained from the block
	 * @param amount The amount requested
	 * @return The amount of aura actually drained
	 */
	public int suckAura(int amount);
}
