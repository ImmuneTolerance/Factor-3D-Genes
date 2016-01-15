package org.immunetolerance.trialshare.processingThreeD;

import org.javatuples.Triplet;
import processing.core.PVector;
import remixlab.proscene.InteractiveFrame;
import remixlab.proscene.Quaternion;
import remixlab.proscene.Scene;

/**
 * Created with IntelliJ IDEA.
 * User: denw
 * Date: 1/7/13
 * Time: 3:56 PM
 *
 */
public class DataPoint3D implements ColorTriplets
{
    InteractiveFrame iFrame;
    float r=10;
    int c;
    MainApplet applet;
    Scene scene;
    Triplet<Integer, Integer, Integer> color;
    float x;
    float y;
    float z;
    boolean  clicked = false;

    DataPoint3D(float x, float y, float z, Triplet<Integer, Integer, Integer> color, MainApplet applet)
    {
        this.applet = applet;
        this.scene = applet.scene;
        iFrame = new InteractiveFrame(applet.scene);
        setColor(color);
        setPosition(x,y,z);
    }

    public void draw()
    {
        applet.pushMatrix();
        applet.pushStyle();
        iFrame.applyTransformation();
        applet.noStroke();


        if (iFrame.grabsMouse())
        {
            applet.fill(applet.darkTheme ? lightGrey : darkGrey);
            doOnOver();
            if (applet.mousePressed == true)
            {
                if(clicked)
                {
                    applet.fill(applet.darkTheme ? lightGrey : darkGrey);
                    clicked = false;
                }
                else
                {
                    applet.unHitPoints();
                    clicked = true;
                }
            }
        }
        else
        {
            if (clicked)
            {
                applet.fill(applet.darkTheme ? lightGrey : darkGrey);
            }
            else
            {
                applet.fill(getColor());
            }
        }

        //Draw a box
        applet.sphere(2);
        applet.popStyle();
        applet.popMatrix();
    }


    public int getColor()
    {
        return c;
    }

    // sets color randomly
    public void setColor(Triplet<Integer, Integer, Integer> c)
    {
        this.c = applet.color(c.getValue0(), c.getValue1(), c.getValue2());
    }

    public void setColor(int c)
    {
        this.c = c;
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

    protected void doOnOver()
    {

    }
}
