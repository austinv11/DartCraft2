package com.austinv11.dartcraft2.tileentities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

/**
 * Liquid force sets air to max, glows in the dark, provides regen and if an entity is a mob weakness as well.
 * If the entity is a zombie, it will drop its gear and it will be turned to a child zombie.
 */
public class TileEntityLiquidForce extends TileEntity {
	
	public static final String publicName = "liquidForce"; 
	
	@Override
	public void updateEntity() {
		List<Entity> entities = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+2, yCoord+2, zCoord+2));
		for (Entity entity : entities) {
			if (entity instanceof EntityLivingBase && entity.isInWater()) {
				if (!worldObj.isRemote) {
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 20, 0, true));
					entity.setAir(300);
					if (entity instanceof EntityMob) {
						((EntityMob) entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 20, 0, true));
						for (int i = entity instanceof EntitySkeleton ? 1 : 0; i < 5; i++) {
							ItemStack equipment = ((EntityMob) entity).getEquipmentInSlot(i);
							if (equipment != null) {
								worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord+1, zCoord, equipment));
								entity.setCurrentItemOrArmor(i, null);
							}
						}
						if (!((EntityMob) entity).isChild())
							if (entity instanceof EntityZombie) {
								((EntityZombie) entity).setChild(true);
							}
					}
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
	}
}
