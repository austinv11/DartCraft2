package com.austinv11.dartcraft2.network;

import com.austinv11.dartcraft2.DartCraft2;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class OpenGuiContainerPacket implements IMessage {
    private int guiID;

    public OpenGuiContainerPacket() {
    }

    public OpenGuiContainerPacket(int guiID) {
        this.guiID = guiID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        guiID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(guiID);
    }

    public static class OpenGuiContainerPacketHandler implements IMessageHandler<OpenGuiContainerPacket, IMessage> {
        @Override
        public IMessage onMessage(OpenGuiContainerPacket message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            player.openGui(DartCraft2.instance, message.guiID, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
            return null;
        }
    }
}
