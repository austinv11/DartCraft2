package com.austinv11.dartcraft2.client.gui.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Infuser - TeeJay
 * Created using Tabula 4.1.1
 */
public class ModelInfuser extends ModelBase {
    public ModelRenderer Body1;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Body4;
    public ModelRenderer Body5;
    public ModelRenderer Body6;
    public ModelRenderer Body7;
    public ModelRenderer Body8;
    public ModelRenderer Body9;
    public ModelRenderer Body10;
    public ModelRenderer Leg7;
    public ModelRenderer Leg3;
    public ModelRenderer Leg6;
    public ModelRenderer Leg5;
    public ModelRenderer Leg1;
    public ModelRenderer Leg4;
    public ModelRenderer Leg8;
    public ModelRenderer Leg2;

    public ModelInfuser() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Leg6 = new ModelRenderer(this, 14, 27);
        this.Leg6.setRotationPoint(-6.0F, 22.0F, 4.0F);
        this.Leg6.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.Leg1 = new ModelRenderer(this, 16, 25);
        this.Leg1.setRotationPoint(5.0F, 22.0F, 4.0F);
        this.Leg1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.Body4 = new ModelRenderer(this, 52, 0);
        this.Body4.setRotationPoint(5.0F, 21.0F, 1.0F);
        this.Body4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
        this.Leg2 = new ModelRenderer(this, 15, 27);
        this.Leg2.setRotationPoint(5.0F, 22.0F, -5.0F);
        this.Leg2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.Body5 = new ModelRenderer(this, 52, 0);
        this.Body5.setRotationPoint(5.0F, 21.0F, -6.0F);
        this.Body5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
        this.Leg4 = new ModelRenderer(this, 17, 25);
        this.Leg4.setRotationPoint(4.0F, 22.0F, -6.0F);
        this.Leg4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.Leg5 = new ModelRenderer(this, 16, 27);
        this.Leg5.setRotationPoint(-6.0F, 22.0F, 5.0F);
        this.Leg5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.Leg3 = new ModelRenderer(this, 13, 27);
        this.Leg3.setRotationPoint(-6.0F, 22.0F, -6.0F);
        this.Leg3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.Leg8 = new ModelRenderer(this, 18, 23);
        this.Leg8.setRotationPoint(4.0F, 22.0F, 5.0F);
        this.Leg8.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.Body10 = new ModelRenderer(this, 4, 7);
        this.Body10.setRotationPoint(-5.0F, 18.6F, -5.0F);
        this.Body10.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10, 0.0F);
        this.Body6 = new ModelRenderer(this, 19, 23);
        this.Body6.setRotationPoint(-5.0F, 21.0F, -6.0F);
        this.Body6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.Body2 = new ModelRenderer(this, 52, 0);
        this.Body2.setRotationPoint(-6.0F, 21.0F, -6.0F);
        this.Body2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
        this.Leg7 = new ModelRenderer(this, 11, 27);
        this.Leg7.setRotationPoint(-6.0F, 22.0F, -5.0F);
        this.Leg7.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.Body7 = new ModelRenderer(this, 19, 23);
        this.Body7.setRotationPoint(1.0F, 21.0F, -6.0F);
        this.Body7.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.Body8 = new ModelRenderer(this, 19, 23);
        this.Body8.setRotationPoint(-5.0F, 21.0F, 5.0F);
        this.Body8.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.Body9 = new ModelRenderer(this, 19, 23);
        this.Body9.setRotationPoint(1.0F, 21.0F, 5.0F);
        this.Body9.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.Body1 = new ModelRenderer(this, 0, 18);
        this.Body1.setRotationPoint(-6.0F, 19.0F, -6.0F);
        this.Body1.addBox(0.0F, 0.0F, 0.0F, 12, 2, 12, 0.0F);
        this.Body3 = new ModelRenderer(this, 52, 0);
        this.Body3.setRotationPoint(-6.0F, 21.0F, 1.0F);
        this.Body3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Leg6.render(f5);
        this.Leg1.render(f5);
        this.Body4.render(f5);
        this.Leg2.render(f5);
        this.Body5.render(f5);
        this.Leg4.render(f5);
        this.Leg5.render(f5);
        this.Leg3.render(f5);
        this.Leg8.render(f5);
        this.Body10.render(f5);
        this.Body6.render(f5);
        this.Body2.render(f5);
        this.Leg7.render(f5);
        this.Body7.render(f5);
        this.Body8.render(f5);
        this.Body9.render(f5);
        this.Body1.render(f5);
        this.Body3.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
