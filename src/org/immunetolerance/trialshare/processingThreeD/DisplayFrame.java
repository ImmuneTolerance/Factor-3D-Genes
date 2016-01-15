package org.immunetolerance.trialshare.processingThreeD;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: denw
 * Thanks to Sebastian Oliva for this DisplayFrame class which lets you put a processing sketch in a nice swing frame
 * Date: 1/14/13
 * Time: 4:38 PM
 *
 */
public class DisplayFrame extends JFrame
{
    public DisplayFrame(){
        this.setSize(1000, 850); //The window Dimensions
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(0, 0, 1000, 800);
        processing.core.PApplet sketch = new MainApplet();
        panel.add(sketch);
        this.add(panel);
        sketch.init(); //this is the function used to start the execution of the sketch
        this.setVisible(true);
    }
}

