package com.austinv11.dartcraft2.api;

/**
 * Implement this in tile entities or blocks in order to enable them to receive forceful aura
 */
public interface IAuraAbsorber {
	
	/**
	 * Called when a forceful aura burst is sent to this block
	 * @param amount The amount of aura in the burst
	 * @return The amount of aura taken
	 */
	public int receiveAuraBurst(int amount);
}
