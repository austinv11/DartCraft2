package com.austinv11.dartcraft2.api;

/**
 * This interface represents an aura controller, this is essentially the brain of the aura system.
 * This is implemented on tile entities only.
 */
public interface IAuraController {
	
	/**
	 * Called to request an aura burst
	 */
	public void burst();
}
