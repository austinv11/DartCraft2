package com.austinv11.dartcraft2.client.gui;

import com.austinv11.dartcraft2.container.ContainerForceBelt;
import com.austinv11.dartcraft2.reference.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiForceBelt extends GuiContainer
{
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/gui/forceBeltGui.png");

    public GuiForceBelt(EntityPlayer player)
    {
        super(new ContainerForceBelt(player, 176, 137));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("gui.forceBelt.name"), 8, this.ySize-160, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", 0), 8, this.ySize-123, 4210752);
    }
}
