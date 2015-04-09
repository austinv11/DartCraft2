package com.austinv11.dartcraft2.blocks;

import com.austinv11.collectiveframework.minecraft.blocks.BlockBase;
import com.austinv11.dartcraft2.creativetab.CreativeTabDC;
import com.austinv11.dartcraft2.particles.BreakEffect;
import com.austinv11.dartcraft2.proxy.ClientProxy;
import com.austinv11.dartcraft2.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BlockDC extends BlockBase {

    private Random rng = new Random();

    public BlockDC() {
        super();
    }

    public BlockDC(Material material) {
        super(material);
    }

    @Override
    public CreativeTabs getTab() {
        return CreativeTabDC.DC_TAB;
    }

    @Override
    public String getModId() {
        return Reference.MOD_ID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        if (world.getBlock(x, y, z) instanceof BlockDC) {
            ClientProxy.addBlockEffects(world, x, y, z, effectRenderer, rng);
            return true;
        }
        return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
        Double[] coords = getOffsetCoordsForSide(target.blockX + .5, target.blockY + .5, target.blockZ + .5, target.sideHit);
        effectRenderer.addEffect(new BreakEffect(worldObj, coords[0], coords[1], coords[2], rng.nextGaussian(), rng.nextGaussian(), rng.nextGaussian()));
        return true;
    }

    private Double[] getOffsetCoordsForSide(double x, double y, double z, int side) {
        List<Double> coords = new ArrayList<Double>();
        switch (side) {
            case 0: //Bottom
                y -= .75;
                x += rng.nextGaussian() / 3;
                z += rng.nextGaussian() / 3;
                break;
            case 1: //Top
                y += .75;
                x += rng.nextGaussian() / 3;
                z += rng.nextGaussian() / 3;
                break;
            case 2: //East
                z -= .75;
                x += rng.nextGaussian() / 3;
                y += rng.nextGaussian() / 3;
                break;
            case 3: //West
                z += .75;
                x += rng.nextGaussian() / 3;
                y += rng.nextGaussian() / 3;
                break;
            case 4: //North
                x -= .75;
                y += rng.nextGaussian() / 3;
                z += rng.nextGaussian() / 3;
                break;
            case 5: //South
                x += .75;
                y += rng.nextGaussian() / 3;
                z += rng.nextGaussian() / 3;
                break;
        }
        coords.add(x);
        coords.add(y);
        coords.add(z);
        return coords.toArray(new Double[3]);
    }
}
