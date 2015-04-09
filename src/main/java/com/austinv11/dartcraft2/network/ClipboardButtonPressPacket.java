package com.austinv11.dartcraft2.network;

import com.austinv11.collectiveframework.minecraft.utils.WorldUtils;
import com.austinv11.dartcraft2.container.ContainerClipboard;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ClipboardButtonPressPacket implements IMessage {

    public String player;
    public EnumButtonType buttonType;
    public World world;

    public ClipboardButtonPressPacket() {

    }

    public ClipboardButtonPressPacket(EntityPlayer player, EnumButtonType buttonType) {
        this.player = player.getCommandSenderName();
        this.buttonType = buttonType;
        world = player.getEntityWorld();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        NBTTagCompound tag = ByteBufUtils.readTag(buf);
        player = tag.getString("player");
        switch (tag.getInteger("type")) {
            case 0:
                buttonType = EnumButtonType.DISTRIBUTE;
                break;
            case 1:
                buttonType = EnumButtonType.SMART_ASSIST;
                break;
            case 2:
                buttonType = EnumButtonType.REMOVE;
                break;
        }
        world = WorldUtils.getWorldFromDimensionId(tag.getInteger("dim"));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("player", player);
        tag.setInteger("type", buttonType.ordinal());
        tag.setInteger("dim", world.provider.dimensionId);
        ByteBufUtils.writeTag(buf, tag);
    }

    public static class ClipboardButtonPressPacketHandler implements IMessageHandler<ClipboardButtonPressPacket, IMessage> {

        @Override
        public IMessage onMessage(ClipboardButtonPressPacket message, MessageContext ctx) {
            EntityPlayer player = WorldUtils.getPlayerForWorld(message.player, message.world);
            if (player != null) {
                if (player.openContainer instanceof ContainerClipboard)
                    switch (message.buttonType) {
                        case DISTRIBUTE:
                            ((ContainerClipboard) player.openContainer).doItemDistribution();
                            break;
                        case SMART_ASSIST:
                            ((ContainerClipboard) player.openContainer).doSmartAssist();
                            break;
                        case REMOVE:
                            ((ContainerClipboard) player.openContainer).doRemoveItems();
                            break;
                    }
            }
            return null;
        }
    }

    public enum EnumButtonType {
        DISTRIBUTE, SMART_ASSIST, REMOVE
    }
}
