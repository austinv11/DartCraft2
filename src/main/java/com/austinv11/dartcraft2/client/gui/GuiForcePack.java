package com.austinv11.dartcraft2.client.gui;

import com.austinv11.dartcraft2.container.ContainerForcePack;
import com.austinv11.dartcraft2.reference.Reference;
import com.austinv11.dartcraft2.utils.DartCraftUtils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiForcePack extends GuiContainer {

    public ResourceLocation GUI_TEXTURE;
    private EntityPlayer player;
    private int meta;

    public GuiForcePack(EntityPlayer player) {
        super(new ContainerForcePack(player, 176, DartCraftUtils.getForcePackGuiYSize(player.getHeldItem().getItemDamage())));
        this.player = player;
        this.meta = player.getHeldItem().getItemDamage();
        this.ySize = DartCraftUtils.getForcePackGuiYSize(player.getHeldItem().getItemDamage());
        this.GUI_TEXTURE = getResourceLocation(this.meta);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("gui.forcePack.name"), 8, this.ySize - (131 + (player.getHeldItem().getItemDamage() * 18)), 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", 0), 8, this.ySize - 94, 4210752);
    }

    private ResourceLocation getResourceLocation(int meta) {
        return new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/gui/forcePackGui" + meta + ".png");
    }
}
