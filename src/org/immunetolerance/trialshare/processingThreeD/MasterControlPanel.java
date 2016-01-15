package org.immunetolerance.trialshare.processingThreeD;

import controlP5.Button;
import controlP5.ControlP5;
import controlP5.Tooltip;
import remixlab.proscene.Scene;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: denw
 * Date: 1/11/13
 * Time: 2:29 PM
 *
 */
public class MasterControlPanel implements MasterControlInterface, ColorTriplets
{
    MainApplet applet;
    protected int id;
    Scene scene;
    Date d;

    Button[] controlSuite = new Button[11];
    Tooltip[] controlTips = new Tooltip[11];
    ControlP5 cp5;

    public MasterControlPanel(MainApplet mainApplet, ControlP5 cp5)
    {
        applet = mainApplet;
        this.cp5 = cp5;

        controlSuite[XY] = new Button(cp5, "IGKV1D-13/IGKV41").setPosition(0, 0).setSize(88, 25).setId(0);
        controlSuite[YZ] = new Button(cp5, "IGKV41/IGLL1").setPosition(90, 0).setSize(88, 25).setId(1);
        controlSuite[XZ] = new Button(cp5, "IGKV1D-13/IGLL1").setPosition(180, 0).setSize(88, 25).setId(2);
        controlSuite[TOGGLE_THEME] = new Button(cp5, "TOGGLE THEME").setPosition(270, 0).setSize(88, 25).setId(3);
        controlSuite[TOGGLE_TRAIN] = new Button(cp5, "TOGGLE TRAIN").setPosition(360, 0).setSize(88, 25).setId(4);
        controlSuite[TOGGLE_TEST] = new Button(cp5, "TOGGLE TEST").setPosition(450, 0).setSize(88, 25).setId(5);
        controlSuite[TOGGLE_GRID] = new Button(cp5, "TOGGLE GRID").setPosition(540, 0).setSize(88, 25).setId(6);
        controlSuite[TOGGLE_AXES] = new Button(cp5, "TOGGLE AXES").setPosition(630, 0).setSize(88, 25).setId(7);
        controlSuite[TOGGLE_TEXT] = new Button(cp5, "TOGGLE TEXT").setPosition(720, 0).setSize(88, 25).setId(8);
        controlSuite[SAVE_PNG] = new Button(cp5, "SAVE PNG").setPosition(810, 0).setSize(88, 25).setId(9);
        controlSuite[HELP] = new Button(cp5, "HOVER HELP").setPosition(900, 0).setSize(88, 25).setId(10);

        cp5.getTooltip().setDelay(500);
        cp5.getTooltip().setAlpha(255);
        controlTips[XY] = cp5.getTooltip().register(controlSuite[XY], "Change view to a top view with IGKV1D-13/IGKV41 as X/Y.").setAlpha(255);
        controlTips[YZ] = cp5.getTooltip().register(controlSuite[YZ], "Change view to a side view with IGKV41/IGLL1 as Y/Z.").setAlpha(255);
        controlTips[XZ] = cp5.getTooltip().register(controlSuite[XZ], "Change view to a end-on view with IGKV1D-13/IGLL1 as X/Z.").setAlpha(255);
        controlTips[TOGGLE_THEME] = cp5.getTooltip().register(controlSuite[TOGGLE_THEME], "Toggle between black theme and white theme.").setAlpha(255);
        controlTips[TOGGLE_TRAIN] = cp5.getTooltip().register(controlSuite[TOGGLE_TRAIN], "Toggle display of Training chart.").setAlpha(255);
        controlTips[TOGGLE_TEST] = cp5.getTooltip().register(controlSuite[TOGGLE_TEST], "Toggle display of Test chart.").setAlpha(255);
        controlTips[TOGGLE_GRID] = cp5.getTooltip().register(controlSuite[TOGGLE_GRID], "Toggle display of background grid.").setAlpha(255);
        controlTips[TOGGLE_AXES] = cp5.getTooltip().register(controlSuite[TOGGLE_AXES], "Toggle visible XYZ axes.").setAlpha(255);
        controlTips[TOGGLE_TEXT] = cp5.getTooltip().register(controlSuite[TOGGLE_TEXT], "Toggle point informational text.").setAlpha(255);
        controlTips[SAVE_PNG] = cp5.getTooltip().register(controlSuite[SAVE_PNG], "Save the current view as a PNG image (saves to directory C:\\FACTORcharts).").setAlpha(255);
        controlTips[HELP] = cp5.getTooltip().register(controlSuite[HELP], "Left click and drag to rotate.  Right click and hold to move.  Hold center wheel and move forward or back to zoom.  Space bar toggles walkthrough mode.").setAlpha(255);
        scene = applet.scene;
    }

    public void fireEvent(int id)
    {
        this.id = id;
        switch(id)
        {
            case XY:
                applet.overrideCamera = true;
                break;
            case YZ:
                applet.overrideCamera = true;
                break;
            case XZ:
                applet.overrideCamera = true;
                break;
            case TOGGLE_THEME:
                applet.darkTheme = !applet.darkTheme;
                break;
            case TOGGLE_TRAIN:
                applet.showTrain = !applet.showTrain;
                break;
            case TOGGLE_TEST:
                applet.showTest = !applet.showTest;
                break;
            case TOGGLE_GRID:
                applet.scene.toggleGridIsDrawn();
                break;
            case TOGGLE_AXES:
                applet.scene.toggleAxisIsDrawn();
                break;
            case TOGGLE_TEXT:
                applet.hideText = !applet.hideText;
                break;
            case SAVE_PNG:
                d = new Date();
                cp5.hide();
                applet.save("C:\\FACTORcharts\\" + String.valueOf(d.getTime()) + ".png");
                cp5.show();
                break;
            case HELP:
                break;
        }
    }

}
