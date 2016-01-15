package org.immunetolerance.trialshare.processingThreeD;

import controlP5.ControlEvent;
import controlP5.ControlP5;
import org.javatuples.Triplet;
import processing.core.PFont;
import processing.core.PVector;
import remixlab.proscene.Quaternion;
import remixlab.proscene.Scene;

import java.util.ArrayList;

public class MainApplet extends PAppletA1 implements ColorTriplets, MasterControlInterface
{
    public static boolean DEBUG = false;
    protected boolean loading = true;
    protected boolean illuminate = false;
    protected boolean hideText = false;
    public Scene scene;
    ArrayList<FactorPublicFig4Point> dataPoints;
    ArrayList points;

    BoundsBox testBox;
    BoundsBox trainingBox;

    PFont font;
    boolean onScreen = false;
    boolean additionalInstructions = false;
    public static Triplet<Integer, Integer, Integer> colorTol;
    public static Triplet<Integer, Integer, Integer> colorSi;

    //size of training set box
    // x ~ IGKVD-13 distance, y ~ IGKV4-1, z ~ IGLL1 - take from data with no scaling
    int xBoxTrainWidth = 10;
    int yBoxTrainDepth = 50;
    int zBoxTrainHeight = 10;

    // adjustments (start at...) - take from data min values on axes with no scaling
    // https://www.itntrialshare.org/study/Studies/ITN507STPUBLIC/Study%20Data/dataset.view?Dataset.reportId=db%3A401&datasetId=4001
    int xBoxTrainStart = 14;
    int yBoxTrainStart = -20;
    int zBoxTrainStart = 12;

    //same as above for test set box
    // x ~ IGKVD-13 distance, y ~ IGKV4-1, z ~ IGLL1 - take from data with no scaling
    int xBoxTestWidth = 12;
    int yBoxTestDepth = 50;
    int zBoxTestHeight = 6;

    // adjustments (start at...) - take from data with no scaling
    int xBoxTestStart = 14;
    int yBoxTestStart = -20;
    int zBoxTestStart = 13;

    public float xTrainFactor=20;
    public float yTrainFactor=-4;
    public float zTrainFactor=20;

    public float xTestFactor=16.7f;
    public float yTestFactor=-4;
    public float zTestFactor=33.33f;



    //an adjustment to separate the test points from the training points so they don't end up on one graph
    public int xTrainTranslate = -550;
    public int xTestTranslate = -165;

    public int yTrainTranslate = 0;
    public int yTestTranslate = 0;

    public int zTrainTranslate = -200;
    public int zTestTranslate = -395;


    protected boolean overrideCamera = false;
    protected boolean darkTheme = false;
    protected  boolean showTrain = true;
    protected boolean showTest = true;

    protected MasterControlPanel mcp;


    String pidString="", tolOrSiString="", testOrTrainString="", xString="", yString="", zString="";

    protected ControlP5 cp5;

    public void setup()
    {
        colorTol = new Triplet<Integer, Integer, Integer>(232,32,32);
        colorSi = new Triplet<Integer, Integer, Integer>(32,32,232);

        cp5 = new ControlP5(this);
        mcp = new MasterControlPanel(this, cp5);
        cp5.setAutoDraw(false);
        size(1000, 800, OPENGL);
        font = createFont("Arial", 16, true);
        textFont(font);
        scene = new Scene(this);

        scene.setRadius(350);


        /////////////////////////
        //create a camera path and add some key frames:
        scene.camera().setPosition(new PVector(0, 1000, -500));
        scene.camera().lookAt( new PVector(0, 0, -500) );
        scene.camera().addKeyFrameToPath(1);

        scene.camera().setPosition(new PVector(-160, 400, 150));
        scene.camera().lookAt( new PVector(-160, 0, 150) );
        scene.camera().addKeyFrameToPath(1);


        scene.camera().setPosition(new PVector(-140,-200,150));
        scene.camera().lookAt( new PVector(200, -300, 150) );
        scene.camera().addKeyFrameToPath(1);

        scene.camera().setPosition(new PVector(300,-200,300));
        scene.camera().lookAt( new PVector(0, 0, 0) );
        scene.camera().addKeyFrameToPath(1);

        //re-position the camera so we see a view similar to the TrialShare figure
        //we recorded these by setting debug=true and clicking on a point
        //see FactorPublicFig4Point
        //btw pitch, yaw, roll 0,0,0 is looking straight down along the z-axis
        //scene.camera().setPosition(new PVector(100, 0, 500));
        scene.camera().setPosition(new PVector(300, 629, 359));
        Quaternion finalOrient = Util.convertPitchYawRoll(0f,-25f,-70f);

        scene.camera().setOrientation(finalOrient);

        scene.camera().addKeyFrameToPath(1);

        scene.setCameraPathsAreDrawn(false);



        //show grid don't show axis
        if(scene.axisIsDrawn())
            scene.toggleAxisIsDrawn();
        if(!scene.gridIsDrawn())
            scene.toggleGridIsDrawn();

        TrialshareConnectAndReceive tcar = new TrialshareConnectAndReceive();
        tcar.getGeneRows();
        dataPoints = tcar.getDataPoints(this);

        //instantiate the boxes for the two charts.
        trainingBox = new BoundsBox((int) ((xTrainFactor * (xBoxTrainStart  + (xBoxTrainWidth/2)))+ xTrainTranslate), (int)((yTrainFactor * (yBoxTrainStart + (yBoxTrainDepth/2)))+ yTrainTranslate), (int)((zTrainFactor * (zBoxTrainStart  + (zBoxTrainHeight/2)))+ zTrainTranslate), this);
        testBox = new BoundsBox((int) ((xTestFactor * (xBoxTestStart  + (xBoxTestWidth/2)))+ xTestTranslate), (int)((yTestFactor * (yBoxTestStart + (yBoxTestDepth/2)))+ yTestTranslate), (int)((zTestFactor * (zBoxTestStart  + (zBoxTestHeight/2)))+ zTestTranslate), this);
        trainingBox.setDimensions((int)(xBoxTrainWidth * xTrainFactor), (int)(yBoxTrainDepth * yTrainFactor), (int)(zBoxTrainHeight * zTrainFactor));
        testBox.setDimensions((int)(xBoxTestWidth * xTestFactor), (int)(yBoxTestDepth * yTestFactor), (int)(zBoxTestHeight * zTestFactor));
        trainingBox.setLegends("TRAINING SET", "14.0 <-- IGKV1D-13 --> 24.0", "-20.0 <-- IGKV4-1 --> 30.0", "12.0 <-- IGLL1 --> 22.0");
        testBox.setLegends("TEST SET", "14.0 <-- IGKV1D-13 --> 26.0", "-20.0 <-- IGKV4-1 --> 30.0", "13.0 <-- IGLL1 --> 19.0");
        trainingBox.setLabelTransforms();
        testBox.setLabelTransforms();
    }

    public void draw()
    {
        background(darkTheme ? black:white);
        smooth();
        gui();
        PVector camPos = scene.camera().at();
        if(illuminate)
        {
            ambientLight(32, 32, 32);
            //make a light that always points towards what the camera is looking at
            directionalLight(255,255,255,-camPos.x, -camPos.y, -camPos.z);
        }

        /*
            Interesting: try directionalLight(camPos.x, camPos.y, camPos.z+50, 1, -1, -1); which will change the color of light depending on where the camera is
         */

        // A. 3D drawing

        //graph bounds boxes
        if(showTrain)
            trainingBox.draw();
        if(showTest)
            testBox.draw();

        pushMatrix();
        shininess(255);
        for (FactorPublicFig4Point f4p: dataPoints)
        {
            if(showTest && !f4p.trainOrTest)
            {
                f4p.draw();
            }
            if(showTrain && f4p.trainOrTest)
            {
                f4p.draw();
            }
        }
        popMatrix();

        if(loading)
        {
            doLoadSequence();
        }
        else
        {
            // B. 2D drawing on top of the 3d scene
            // All screen drawing should be enclosed between Scene.beginScreenDrawing() and
            // Scene.endScreenDrawing().

            scene.beginScreenDrawing();

            //point data
            if(!hideText)
            {
                pushStyle();
                textAlign(RIGHT);
                fill(darkTheme ? white : black);
                text(pidString, getWidth() - 25, 70);
                text(tolOrSiString, getWidth() - 25, 90);
                text(testOrTrainString, getWidth() - 25, 110);
                text(xString, getWidth() - 25, 130);
                text(yString, getWidth() - 25, 150);
                text(zString, getWidth() - 25, 170);
                popStyle();
            }
            scene.endScreenDrawing();
        }
    }


    public MainApplet getApplet()
    {
        return this;
    }

    void gui()
    {
        pushStyle();
        saveState();
         try
         {
              cp5.draw();
         }
         catch(Exception e)
         {
              System.out.println("cp5.draw exception: "+e.getMessage());
         }

        restoreState();
        popStyle();
    }

    // properly hack to make it work
    void saveState()
    {
        //  Set processing projection and modelview matrices to draw in 2D:
        // 1. projection matrix:
        float cameraZ = ((height/2.0f) / tan(PI*60.0f/360.0f));
        scene.pg3d.perspective(PI/3.0f, scene.camera().aspectRatio(), cameraZ/10.0f, cameraZ*10.0f);
        // 2 model view matrix
        scene.pg3d.camera();
    }

    void restoreState()
    {
        // result = testCondition ? value1 : value2
        // 1. Restore processing projection matrix

        //
        if(overrideCamera)
        {
            switch (mcp.id)
            {
                case XY:
                    scene.camera().setOrientation(0, 0);
                    scene.camera().setPosition(new PVector(-4f, 0f, 800f));
                    break;
                case YZ:
                    scene.camera().setOrientation(PI/2, (3*PI)/2);
                    scene.camera().setPosition(new PVector(800f, 0f, 119.33362f));
                    break;
                case XZ:
                    scene.camera().setOrientation(0, PI/2);
                    scene.camera().setPosition(new PVector(1.7447035f, 773.8136f, 100f));
                    break;
            }

            overrideCamera = false;
        }
        else
        {

            switch (scene.camera().type())
            {
                case PERSPECTIVE:
                    scene.pg3d.perspective(scene.camera().fieldOfView(), scene.camera().aspectRatio(),scene.camera().zNear(), scene.camera().zFar());
                    break;
                case ORTHOGRAPHIC:
                    float[] wh = scene.camera().getOrthoWidthHeight();
                    scene.pg3d.ortho(-wh[0], wh[0], -wh[1], wh[1], scene.camera().zNear(), scene.camera().zFar());
                    break;
            }

            scene.pg3d.camera(scene.camera().position().x, scene.camera().position().y, scene.camera().position().z, scene.camera().at().x, scene.camera().at().y, scene.camera().at().z, scene.camera().upVector().x, scene.camera().upVector().y, scene.camera().upVector().z);
        }

    }

    public void controlEvent(ControlEvent event)
    {
        mcp.fireEvent(event.getId());
    }

    private int setPerspectiveOverride()
    {
        scene.pg3d.perspective(scene.camera().fieldOfView(), scene.camera().aspectRatio(),scene.camera().zNear(), scene.camera().zFar());
        return 1;
    }

    private int setPerspectiveNoOverride()
    {
        scene.pg3d.perspective(scene.camera().fieldOfView(), scene.camera().aspectRatio(),scene.camera().zNear(), scene.camera().zFar());
        return 1;
    }

    //iterates through the data points and
    protected void unHitPoints()
    {
        for(DataPoint3D point: dataPoints)
        {
            point.clicked = false;
        }
    }

    protected void doLoadSequence()
    {
        illuminate = true;
        scene.camera().playPath(1);
        loading = false;
    }



}
