package org.immunetolerance.trialshare.processingThreeD;

import org.javatuples.Triplet;
import processing.core.PApplet;

/**
 * Created with IntelliJ IDEA.
 * User: DenW
 * Date: 1/5/13
 * Time: 2:11 PM
 *
 */
public class PAppletA1 extends PApplet
{
    public void stroke(Triplet<Integer, Integer, Integer> rgbTriplet)
    {
        super.stroke(rgbTriplet.getValue0(), rgbTriplet.getValue1(), rgbTriplet.getValue2());
    }

    public void stroke(Triplet<Integer, Integer, Integer> rgbTriplet, float alpha)
    {
        super.stroke(rgbTriplet.getValue0(), rgbTriplet.getValue1(), rgbTriplet.getValue2(), alpha);
    }

    public void fill(Triplet<Integer, Integer, Integer> rgbTriplet)
    {
        super.fill(rgbTriplet.getValue0(), rgbTriplet.getValue1(), rgbTriplet.getValue2());
    }

    public void fill(Triplet<Integer, Integer, Integer> rgbTriplet, float alpha)
    {
        super.fill(rgbTriplet.getValue0(), rgbTriplet.getValue1(), rgbTriplet.getValue2(), alpha);
    }

    public void background(Triplet<Integer, Integer, Integer> rgbTriplet)
    {
        super.background(rgbTriplet.getValue0(), rgbTriplet.getValue1(), rgbTriplet.getValue2());
    }

    public void background(Triplet<Integer, Integer, Integer> rgbTriplet, float alpha)
    {
        super.background(rgbTriplet.getValue0(), rgbTriplet.getValue1(), rgbTriplet.getValue2(), alpha);
    }


}
