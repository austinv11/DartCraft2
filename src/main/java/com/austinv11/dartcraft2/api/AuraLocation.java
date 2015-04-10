package com.austinv11.dartcraft2.api;

import net.minecraft.world.World;

/**
 * An immutable object holding either an {@link IAuraAbsorber}, {@link IAuraEmitter}, or an {@link IPassiveAuraEmitter}
 * as well as its location
 */
public class AuraLocation <T>{
	
	private T object;
	private World world;
	private int x, y, z;
	
	/**
	 * Constructor for the aura object location
	 * @param object The aura object
	 * @param world The world the object is located in
	 * @param x The x coord of the object
	 * @param y The y coord of the object
	 * @param z The z coord of the object
	 */
	public AuraLocation(T object, World world, int x, int y, int z) {
		this.object = object;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Gets the aura object
	 * @return The object
	 */
	public T getAuraObject() {
		return object;
	}
	
	/**
	 * Gets the world for the aura object
	 * @return The world
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * Gets the x coord for the aura object
	 * @return The coord
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y coord for the aura object
	 * @return The coord
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gets the z coord for the aura object
	 * @return The coord
	 */
	public int getZ() {
		return z;
	}
}
