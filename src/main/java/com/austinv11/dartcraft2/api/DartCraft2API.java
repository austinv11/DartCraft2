package com.austinv11.dartcraft2.api;

/**
 * The class used to interface with DartCraft 2
 */
public class DartCraft2API {
	
	/**
	 * Gets the {@link ITransmutationRecipeHandler} to interface with
	 * @return The instance of the handler
	 * @throws FailedAPIRequest
	 */
	public static ITransmutationRecipeHandler getTransmutationRecipeHandler() throws FailedAPIRequest {
		try {
			return (ITransmutationRecipeHandler) Class.forName("com.austinv11.dartcraft2.DartCraft2").getDeclaredField("TRANSMUTATION_HANDLER").get(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedAPIRequest("Unknown exception retrieving the transmutation recipe handler");
		}
	}
}
