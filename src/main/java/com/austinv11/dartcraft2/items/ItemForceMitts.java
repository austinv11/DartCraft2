package com.austinv11.dartcraft2.items;

import com.austinv11.dartcraft2.creativetab.CreativeTabDC;
import com.austinv11.dartcraft2.reference.Reference;
import com.google.common.collect.ImmutableSet;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ItemForceMitts extends ItemTool {
	
	public ItemForceMitts() {
		this(1.0F, ToolMaterial.GOLD, null);
	}
	
	protected ItemForceMitts(float damage, ToolMaterial toolMaterial, Set blocksForTool) {
		super(damage, toolMaterial, blocksForTool);
		this.setUnlocalizedName("forceMitts");
		this.setCreativeTab(CreativeTabDC.DC_TAB);
		this.setMaxDamage(50);
		this.setHarvestLevel("pickaxe", 1);
	}
	
	@Override
	public boolean hitEntity(ItemStack toolStack, EntityLivingBase attacker, EntityLivingBase attacked) {
		return true;
	}
	
	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return ImmutableSet.of("pickaxe", "axe", "shovel");
	}
	
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		return 12.0F;
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
		Material material = player.getEntityWorld().getBlock(x, y, z).getMaterial();
		if (material == Material.leaves || material == Material.plants || material == Material.vine) {
			break3x3(player, player.getEntityWorld(), x, y, z, material);
			return true;
		}
		return false;
	}
	
	private void break3x3(EntityPlayer player, World world, int x, int y, int z, Material material) {
		if (raytraceFromEntity(world, player, false, 7D) != null) {
			switch (ForgeDirection.getOrientation(raytraceFromEntity(world, player, false, 7D).sideHit)) {
				case NORTH:
				case SOUTH:
					for (int i = -1; i < 2; i++)
						for (int j = -1; j < 2; j++)
							breakBlockForMaterial(material, world, x+j, y+i, z, player);
					break;
				case EAST:
				case WEST:
					for (int i = -1; i < 2; i++)
						for (int j = -1; j < 2; j++)
							breakBlockForMaterial(material, world, x, y+i, z+j, player);
					break;
				case UP:
				case DOWN:
					for (int i = -1; i < 2; i++)
						for (int j = -1; j < 2; j++)
							breakBlockForMaterial(material, world, x+i, y, z+j, player);
					break;
			}
		}
	}
	
	private void breakBlockForMaterial(Material material, World world, int x, int y, int z, EntityPlayer player) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		if (material == block.getMaterial() && block.getBlockHardness(world, x, y, z) >= 0) {
			if (player instanceof EntityPlayerMP) {
				BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent(world, ((EntityPlayerMP) player).theItemInWorldManager.getGameType(), (EntityPlayerMP) player, x, y, z);
				if (event.isCanceled())
					return;
				if (!world.isRemote) {
					ArrayList<ItemStack> drops = block.getDrops(world, x, y, z, meta, 0);
					world.setBlockToAir(x, y, z);
					block.onBlockDestroyedByPlayer(world, x, y, z, meta);
					Random rand = new Random();
					for (ItemStack i : drops) {
						float f = 0.7F;
						double d = (double) (rand.nextFloat()*f)+(double) (1.0F-f)*0.5D;
						double d1 = (double) (rand.nextFloat()*f)+(double) (1.0F-f)*0.5D;
						double d2 = (double) (rand.nextFloat()*f)+(double) (1.0F-f)*0.5D;
						EntityItem entityitem = new EntityItem(world, (double) x+d, (double) y+d1, (double) z+d2, i);
						entityitem.delayBeforeCanPickup = 5;
						world.spawnEntityInWorld(entityitem);
					}
				} else { //FIXME
					block.addDestroyEffects(world, x, y, z, meta, Minecraft.getMinecraft().effectRenderer);
					world.playSoundEffect((double) x+0.5D, (double) y+0.5D, (double) z+0.5D, block.stepSound.getBreakSound(), 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
					block.onBlockDestroyedByPlayer(world, x,y,z, meta);
				}
			}
		}
	}
	
	private MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range) {
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f;
		if (!world.isRemote && player instanceof EntityPlayer)
			d1 += 1.62D;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2*0.017453292F-(float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = range;
//		if (player instanceof EntityPlayerMP) {
//			d3 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
//		}
		Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
		return world.func_147447_a(vec3, vec31, par3, !par3, par3);
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int blockMeta, float blockX, float blockY, float blockZ) { //Taken from vanilla code
		if (!player.canPlayerEdit(x, y, z, blockMeta, itemstack)) {
			return false;
		}
		else {
			UseHoeEvent event = new UseHoeEvent(player, itemstack, world, x, y, z);
			if (MinecraftForge.EVENT_BUS.post(event)) {
				return false;
			}
			
			if (event.getResult() == Event.Result.ALLOW) {
				itemstack.damageItem(1, player);
				return true;
			}
			
			Block block = world.getBlock(x, y, z);
			
			if (blockMeta != 0 && world.getBlock(x, y + 1, z).isAir(world, x, y + 1, z) && (block == Blocks.grass || block == Blocks.dirt)) {
				Block block1 = Blocks.farmland;
				world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);
				
				if (world.isRemote) {
					return true;
				}
				else {
					world.setBlock(x, y, z, block1);
					itemstack.damageItem(1, player);
					return true;
				}
			}
			else {
				return false;
			}
		}
	}
	
	@Override
	public String getUnlocalizedName(){//Formats the name
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase()+":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack item){//Formats the name
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase()+":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){//Sets the icon
		itemIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){//Removes the "item." from the item name
		return unlocalizedName.substring(unlocalizedName.indexOf(".")+1);
	}
}
