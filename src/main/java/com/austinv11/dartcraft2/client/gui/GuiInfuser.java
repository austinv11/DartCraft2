package com.austinv11.dartcraft2.client.gui;

import com.austinv11.dartcraft2.container.ContainerInfuser;
import com.austinv11.dartcraft2.reference.Reference;
import com.austinv11.dartcraft2.tileentities.TileEntityInfuser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiInfuser extends GuiContainer {
	
	private int x, y, z;
	private EntityPlayer player;
	private World world;
	private int sizeX, sizeY;
	private ResourceLocation backgroundimage = new ResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + "textures/gui/infuserGui.png");
	private TileEntityInfuser infuser;
	
	public GuiInfuser(EntityPlayer player, World world, int x, int y, int z) {
		super(new ContainerInfuser((TileEntityInfuser) world.getTileEntity(x, y, z), player, 176, 200));
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
		this.world = world;
		sizeX = 176;
		sizeY = 200;
		infuser = (TileEntityInfuser) world.getTileEntity(x,y,z);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(backgroundimage);
		int x = (width - sizeX) / 2;
		int y = (height - sizeY) / 2;
		drawTexturedModalRect(x, y, 0, 0, sizeX, sizeY);
		if (infuser.liquidForceTank.getFluidAmount() > 0) {
			GL11.glEnable(GL11.GL_BLEND);
			drawFluidTank(infuser.liquidForceTank, x+12, y+48);
			GL11.glDisable(GL11.GL_BLEND);
		}
		this.mc.getTextureManager().bindTexture(backgroundimage);
		drawTexturedModalRect(x+12, y+53, sizeX+14, sizeY+68, 16, 96);
		
		int scaleFactor = new ScaledResolution(Minecraft.getMinecraft(), x, y).getScaleFactor();
		if (mouseX > x+(43*scaleFactor) && mouseX < x+(52*scaleFactor)
				&& mouseY > y+(13*scaleFactor) && mouseY < y+(26*scaleFactor)) {
			List<String> text = new ArrayList<String>();
			text.add(StatCollector.translateToLocal("gui.infuser.help"));
			drawHoveringText(text, mouseX, mouseY, mc.fontRenderer);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			int j1 = x+(43*scaleFactor);
			int k1 = y+(13*scaleFactor);
			GL11.glColorMask(true, true, true, false);
			this.drawGradientRect(j1, k1, j1+9, k1+9, -2130706433, -2130706433);
			GL11.glColorMask(true, true, true, true);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		} else if (mouseX > x+(43*scaleFactor) && mouseX < x+(52*scaleFactor)
				&& mouseY > y+(94*scaleFactor) && mouseY < y+(107*scaleFactor)) {
			List<String> text = new ArrayList<String>();
			text.add(StatCollector.translateToLocal("gui.infuser.start"));
			drawHoveringText(text, mouseX, mouseY, mc.fontRenderer);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			int j1 = x+(43*scaleFactor);
			int k1 = y+(94*scaleFactor);
			GL11.glColorMask(true, true, true, false);
			this.drawGradientRect(j1, k1, j1+9, k1+9, -2130706433, -2130706433);
			GL11.glColorMask(true, true, true, true);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int button) {
		int x = (this.width-this.xSize)/2;
		int y = (this.height-this.ySize)/2;
		int scaleFactor = new ScaledResolution(Minecraft.getMinecraft(), x, y).getScaleFactor();
		if (mouseX > x+(43*scaleFactor) && mouseX < x+(52*scaleFactor)
				&& mouseY > y+(13*scaleFactor) && mouseY < y+(26*scaleFactor)) {
			Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			//TODO: Help (sub-)gui
		} else if (mouseX > x+(43*scaleFactor) && mouseX < x+(52*scaleFactor)
				&& mouseY > y+(94*scaleFactor) && mouseY < y+(107*scaleFactor)) {
			Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			//TODO:Start infusion
		} else {
			super.mouseClicked(mouseX, mouseY, button);
		}
	}
	
	public void drawFluidTank(IFluidTank tank, int x, int y) {
		FluidStack fluid = tank.getFluid();
		TextureManager manager = Minecraft.getMinecraft().renderEngine;
		if (fluid != null) {
			manager.bindTexture(manager.getResourceLocation(0));
			float amount = fluid.amount;
			float capacity = tank.getCapacity();
			float scale = amount / capacity;
			int fluidTankHeight = 60;
			int fluidAmount = (int) (scale * fluidTankHeight);
			drawFluid(x, y + fluidTankHeight - fluidAmount, fluid.getFluid().getIcon(fluid), 16, fluidAmount);
		}
	}
	
	private void drawFluid(int x, int y, IIcon icon, int width, int height) {
		int i = 0;
		int j = 0;
		
		int drawHeight = 0;
		int drawWidth = 0;
		
		for (i = 0; i < width; i += 16) {
			for (j = 0; j < height; j += 16) {
				drawWidth = Math.min(width - i, 16);
				drawHeight = Math.min(height - j, 16);
				drawTexturedModelRectFromIcon(x+i, y+j, icon, drawWidth, drawHeight);
			}
		}
	}
}
