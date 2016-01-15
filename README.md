This is a Java application that uses the Processing framework and the LabKey API in order to connect to the Immune Tolerance Network TrialShare clinical trials portal.

After receiving the data from TrialShare, this program will display points in a navigable 3D format.  You will also be able to hover over points and see specific participant information regarding that point.  You can also export images of any given view.

The data and view in this application is based on Figure 4 of the JCI 2010 B-Cell Signature manuscript which was created by Zhong Gao.

This is not intended to be a complete end-user ready application.  Instead, it is intended for developers in order to share with them the possibilities of using TrialShare clinical trials data in ways other than those available through the UI.

The data and application itself are intended for demonstration purposes only and should not be used as the basis of scientific investigation without verifying and cross-checking data against the actual publications.  Please refer to TrialShare's FACTOR ITN507ST:  Investigations of Tolerant Kidney Transplant Recipients which can be found at https://www.itntrialshare.org.  You will need to self-register for a free account.

Enter your user credentials in the TrialshareConnectAndReceive class, or add a properties file or other method of entering your credentials.  Ensure that the 'lib' folder is treated as a library, and use the MainApplet class as your main.  Refer to https://docs.oracle.com/javase/tutorial/deployment/applet/ for more information about applet based programs.  See https://processing.org/ for information and instructions for the Processing framework.

Upon successfully launching the program, you will be presented with a quick camera 'flythrough' of the data.  Upon completion of that, you should be able to use your mouse to rotate and navigate around the data. Note the buttons at the top to switch camera views and provide other functionality.

This source code and program are intended for intermediate to advanced Java programmers.  Please contact us at trialsharescience@immunetolerance.org if you have questions or wish to join this project.
