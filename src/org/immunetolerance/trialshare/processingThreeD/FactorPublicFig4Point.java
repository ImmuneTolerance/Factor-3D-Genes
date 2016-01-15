package org.immunetolerance.trialshare.processingThreeD;

import org.javatuples.Triplet;
import processing.core.PVector;
import remixlab.proscene.Quaternion;

/**
 * Created with IntelliJ IDEA.
 * User: DenW
 * Date: 1/8/13
 * Time: 9:24 AM
 *
 */
public class FactorPublicFig4Point extends DataPoint3D
{
    //points will either be from the training group or the actual test, and should generally be displayed in two different charts
    boolean trainOrTest;
    //each point will either represent data from either a tolerant or 'si' (standard immunotherapy) participant
    boolean tolOrSi;
    //participant id of the point
    String pid;
    String tolOrSiString = "";
    String testOrTrainString = "";
    String xString, yString, zString;


    MainApplet applet;

    Triplet<Integer, Integer, Integer> color;

    FactorPublicFig4Point(float x, float y, float z, boolean trainOrTest, boolean tolOrSi,  String pid, MainApplet applet)
    {
        super((trainOrTest ? (applet.xTrainFactor * x)+applet.xTrainTranslate : (applet.xTestFactor * x)+applet.xTestTranslate), trainOrTest ? (applet.yTrainFactor * y)+applet.yTrainTranslate : (applet.yTestFactor * y) + applet.yTrainTranslate, trainOrTest ?  (applet.zTrainFactor * z)+applet.zTrainTranslate : (applet.zTestFactor * z) + applet.zTestTranslate, (tolOrSi ? MainApplet.colorTol : MainApplet.colorSi), applet);

        this.trainOrTest = trainOrTest;
        this.tolOrSi = tolOrSi;
        this.pid = pid;
        this.applet = applet;
        tolOrSiString = this.tolOrSi ? "Tolerant" : "Standard Immunotherapy";
        testOrTrainString = this.trainOrTest ? "Training" : "Test";

        //these are unadjusted and hold the actual values from the dataset
        xString = String.valueOf(x);
        yString = String.valueOf(y);
        zString = String.valueOf(z);

    }

    @Override
    protected void doOnOver()
    {
        applet.pidString="Participant: " + pid;
        applet.tolOrSiString="Status: " + tolOrSiString;
        applet.testOrTrainString="Data Type: " + testOrTrainString;
        applet.xString="IGKV1D-13: " + xString;
        applet.yString="IGKV4-1: " + yString;
        applet.zString="IGLL1: " + zString;

        //in debug mode, clicking on a point will trigger the upper right to display the current camera coordinates and where it's looking
        //you may wish to add/change this to show you the xyz of the actual point
        if(MainApplet.DEBUG)
        {
            if(this.getPosition()!=null)
            {
                PVector cameraPosition = applet.scene.camera().at();
                Quaternion lookingAt = applet.scene.camera().orientation();
                applet.xString = String.valueOf("x at: " + cameraPosition.x +  " orientW: " + lookingAt.w + " orientX: " + lookingAt.x);
                applet.yString = String.valueOf("y at: " + cameraPosition.y+ " orientY: " + lookingAt.y);
                applet.zString = String.valueOf("z at: " + cameraPosition.z)+ " orientZ: " + lookingAt.z;
            }

        }
    }
}
