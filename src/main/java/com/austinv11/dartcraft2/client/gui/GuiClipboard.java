package com.austinv11.dartcraft2.client.gui;

import com.austinv11.dartcraft2.DartCraft2;
import com.austinv11.dartcraft2.container.ContainerClipboard;
import com.austinv11.dartcraft2.network.ClipboardButtonPressPacket;
import com.austinv11.dartcraft2.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiClipboard extends GuiContainer {

    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/gui/clipboardGui.png");

    public GuiClipboard(EntityPlayer player) {
        super(new ContainerClipboard(player));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 28, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int scaleFactor = new ScaledResolution(Minecraft.getMinecraft(), k, l).getScaleFactor();

        if (mouseX > k + (96 * scaleFactor) && mouseX < k + (105 * scaleFactor)
                && mouseY > l + (15 * scaleFactor) && mouseY < l + (28 * scaleFactor)) {
            List<String> text = new ArrayList<String>();
            text.add(StatCollector.translateToLocal("gui.clipboard.itemDistribution"));
            drawHoveringText(text, mouseX, mouseY, mc.fontRenderer);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int j1 = k + (96 * scaleFactor);
            int k1 = l + (15 * scaleFactor);
            GL11.glColorMask(true, true, true, false);
            this.drawGradientRect(j1, k1, j1 + 9, k1 + 9, -2130706433, -2130706433);
            GL11.glColorMask(true, true, true, true);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        } else if (mouseX > k + (89 * scaleFactor) && mouseX < k + (98 * scaleFactor)
                && mouseY > l + (54 * scaleFactor) && mouseY < l + (67 * scaleFactor)) {
            List<String> text = new ArrayList<String>();
            text.add(StatCollector.translateToLocal("gui.clipboard.smartAssist"));
            drawHoveringText(text, mouseX, mouseY, mc.fontRenderer);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int j1 = k + (89 * scaleFactor);
            int k1 = l + (54 * scaleFactor);
            GL11.glColorMask(true, true, true, false);
            this.drawGradientRect(j1, k1, j1 + 9, k1 + 9, -2130706433, -2130706433);
            GL11.glColorMask(true, true, true, true);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        } else if (mouseX > k + (103 * scaleFactor) && mouseX < k + (112 * scaleFactor)
                && mouseY > l + (56 * scaleFactor) && mouseY < l + (67 * scaleFactor)) {
            List<String> text = new ArrayList<String>();
            text.add(StatCollector.translateToLocal("gui.clipboard.removeItems"));
            drawHoveringText(text, mouseX, mouseY, mc.fontRenderer);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int j1 = k + (103 * scaleFactor);
            int k1 = l + (54 * scaleFactor);
            GL11.glColorMask(true, true, true, false);
            this.drawGradientRect(j1, k1, j1 + 9, k1 + 9, -2130706433, -2130706433);
            GL11.glColorMask(true, true, true, true);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        int scaleFactor = new ScaledResolution(Minecraft.getMinecraft(), k, l).getScaleFactor();
        if (mouseX > k + (96 * scaleFactor) && mouseX < k + (105 * scaleFactor)
                && mouseY > l + (15 * scaleFactor) && mouseY < l + (28 * scaleFactor)) {
            doItemDistribution();
        } else if (mouseX > k + (89 * scaleFactor) && mouseX < k + (98 * scaleFactor)
                && mouseY > l + (54 * scaleFactor) && mouseY < l + (67 * scaleFactor)) {
            doSmartAssist();
        } else if (mouseX > k + (103 * scaleFactor) && mouseX < k + (112 * scaleFactor)
                && mouseY > l + (56 * scaleFactor) && mouseY < l + (67 * scaleFactor)) {
            doRemoveItems();
        } else {
            super.mouseClicked(mouseX, mouseY, button);
        }
    }

    private void doItemDistribution() {
        ((ContainerClipboard) this.inventorySlots).doItemDistribution();
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
        DartCraft2.NETWORK.sendToServer(new ClipboardButtonPressPacket(Minecraft.getMinecraft().thePlayer, ClipboardButtonPressPacket.EnumButtonType.DISTRIBUTE));
    }

    private void doSmartAssist() {
        ((ContainerClipboard) this.inventorySlots).doSmartAssist();
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
        DartCraft2.NETWORK.sendToServer(new ClipboardButtonPressPacket(Minecraft.getMinecraft().thePlayer, ClipboardButtonPressPacket.EnumButtonType.SMART_ASSIST));
    }

    private void doRemoveItems() {
        ((ContainerClipboard) this.inventorySlots).doRemoveItems();
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
        DartCraft2.NETWORK.sendToServer(new ClipboardButtonPressPacket(Minecraft.getMinecraft().thePlayer, ClipboardButtonPressPacket.EnumButtonType.REMOVE));
    }
}
