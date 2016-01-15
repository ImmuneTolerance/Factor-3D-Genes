package org.immunetolerance.trialshare.processingThreeD;

import org.javatuples.Triplet;
import processing.core.PVector;
import remixlab.proscene.InteractiveFrame;
import remixlab.proscene.Quaternion;
import remixlab.proscene.Scene;

/**
 * Created with IntelliJ IDEA.
 * User: DenW
 * Date: 1/7/13
 * Time: 3:56 PM
 *
 */
public class BoundsBox implements ColorTriplets
{
    InteractiveFrame iFrame;
    float r=10;
    int c;
    MainApplet applet;
    Scene scene;
    Triplet<Integer, Integer, Integer> strokeColor;
    float x,y,z;
    int w,h,d;
    int s0,s1,s2,s3;
    protected String title;
    protected String xLabel;
    protected String yLabel;
    protected String zLabel;

    protected float titleTX;
    protected float titleTY;
    protected float titleTZ;

    protected float xAxesLabelTX;
    protected float xAxesLabelTY;
    protected float xAxesLabelTZ;

    protected float yAxesLabelTX;
    protected float yAxesLabelTY;
    protected float yAxesLabelTZ;

    protected float zAxesLabelTX;
    protected float zAxesLabelTY;
    protected float zAxesLabelTZ;

    public BoundsBox(int x, int y, int z, MainApplet applet)
    {
        this.applet = applet;
        this.scene = applet.scene;
        iFrame = new InteractiveFrame(applet.scene);
        setPosition(x,y,z);
    }

    public void setDimensions(int w, int h, int d)
    {
        this.w = w;
        this.h = h;
        this.d = d;
    }

    public void setLabelTransforms()
    {
        //
        titleTX = 0;
        titleTY = (h/2) - 40;
        titleTZ = -(d/2 + 20);

        xAxesLabelTX = 0;
        xAxesLabelTY = d + 80;
        xAxesLabelTZ = -h;

        yAxesLabelTX = -(h/2);
        yAxesLabelTY = 0;
        yAxesLabelTZ = -(w/2 + 10);

        zAxesLabelTX = 130;
        zAxesLabelTY = -(d/2 + 20);
        zAxesLabelTZ = 0;
    }

    public void draw()
    {
        applet.pushMatrix();
        applet.pushStyle();
        iFrame.applyTransformation();
        applet.noFill();
        applet.stroke((applet.darkTheme ? white : darkGreen),196f);
        applet.strokeWeight(2);
        applet.box(w, h, d);
        applet.fill(applet.darkTheme ? white : black);
        applet.textAlign(applet.CENTER);
        applet.rotateX(-1.570f);
        applet.translate(titleTX, titleTY, titleTZ);
        applet.text(title, 0, 0);

        applet.translate(xAxesLabelTX,xAxesLabelTY,xAxesLabelTZ);
        applet.text(xLabel, 0, 0);

        applet.rotateY(1.570f);
        applet.translate(yAxesLabelTX,yAxesLabelTY,yAxesLabelTZ);
        applet.text(yLabel, 0, 0);

        applet.rotateZ(-1.570f);
        applet.translate(zAxesLabelTX,zAxesLabelTY,zAxesLabelTZ);
        applet.text(zLabel, 0, 0);

        applet.popStyle();
        applet.popMatrix();
    }



    public PVector getPosition()
    {
        return iFrame.position();
    }

    public void setPosition(float x, float y, float z)
    {
        iFrame.setPosition(new PVector(x,y,z));
    }

    public void setPosition(PVector pos)
    {
        iFrame.setPosition(pos);
    }

    public Quaternion getOrientation()
    {
        return iFrame.orientation();
    }

    public void setOrientation(PVector v)
    {
        PVector to = PVector.sub(v, iFrame.position());
        iFrame.setOrientation(new Quaternion(new PVector(0,1,0), to));
    }

    public void setLegends(String title, String xLabel, String yLabel, String zLabel)
    {
        this.title = title;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.zLabel = zLabel;
    }
}
