package com.austinv11.dartcraft2.network;

import com.austinv11.collectiveframework.minecraft.utils.WorldUtils;
import com.austinv11.dartcraft2.proxy.ClientProxy;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Random;

public class DartCraftEffectPacket implements IMessage {

    public World world;
    public int x, y, z;

    public DartCraftEffectPacket() {

    }

    public DartCraftEffectPacket(World world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        NBTTagCompound tag = ByteBufUtils.readTag(buf);
        world = WorldUtils.getWorldFromDimensionId(tag.getInteger("dim"));
        x = tag.getInteger("x");
        y = tag.getInteger("y");
        z = tag.getInteger("z");
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("dim", world.provider.dimensionId);
        tag.setInteger("x", x);
        tag.setInteger("y", y);
        tag.setInteger("z", z);
        ByteBufUtils.writeTag(buf, tag);
    }

    public static class DartCraftEffectPacketHandler implements IMessageHandler<DartCraftEffectPacket, IMessage> {

        private Random rng = new Random();

        @Override
        public IMessage onMessage(DartCraftEffectPacket message, MessageContext ctx) {
            ClientProxy.addBlockEffects(message.world, message.x, message.y, message.z, Minecraft.getMinecraft().effectRenderer, rng);
            return null;
        }
    }
}
