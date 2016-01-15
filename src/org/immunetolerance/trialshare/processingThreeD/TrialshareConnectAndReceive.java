package org.immunetolerance.trialshare.processingThreeD;

import org.labkey.remoteapi.Connection;
import org.labkey.remoteapi.query.SelectRowsCommand;
import org.labkey.remoteapi.query.SelectRowsResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrialshareConnectAndReceive
{
    // refer to https://www.labkey.org/download/clientapi_docs/java-api/doc/ especially SelectRowsCommand

    protected Connection cn;
    protected SelectRowsCommand cmd;
    protected SelectRowsResponse response;
    protected ArrayList<GeneDataRow> geneDataRows;
    protected ArrayList<FactorPublicFig4Point> pointList;

    protected TrialshareConnectAndReceive()
    {
        //you'll need the email and password you used when you registered for TrialShare
        cn = new Connection("https://www.itntrialshare.org/", "itntester@gmail.com", "1mmune!!");
    }

    protected void getGeneRows()
    {
        geneDataRows = new ArrayList<>();
        try
        {
            cmd = new SelectRowsCommand("study", "3D Genes");

            //try to get the data from the server. otherwise, use the .ser file (see below on how to generate it).
            List<Map<String,Object>> responseList = new ArrayList<>();
            try
            {
                response = cmd.execute(cn, "Studies/ITN507STJCI/Study Data");
                responseList = response.getRows();
            }
            catch(Exception e)
            {
                System.out.println("Caught exception getParticipantDetails.  Check username and password above.");

                ObjectInputStream ois = null;
                try
                {
                     FileInputStream fis = new FileInputStream("genelist.ser");
                     ois = new ObjectInputStream(fis);
                     Object obj = null;
                     while ((obj = ois.readObject()) != null)
                     {
                          if (obj instanceof ArrayList)
                          {
                               responseList = (ArrayList)obj;
                          }
                     }
                }
                catch(FileNotFoundException fnfe)
                {
                     System.out.println("No genelist.ser was found.  This means that you did not connnect to TrialShare, and then the fallback genelist.ser was not found.");
                }
                finally
                {
                     if(ois != null)
                     {
                          ois.close();
                     }
                }
            }

            //Example to generate a genelist.ser file.  put in the root of your project and make sure to add it to your zip.
            //having the serialized version of the data will allow the program to run even if you can't connect to the server.
            /*
            FileOutputStream fos = new FileOutputStream("C:\\FACTORcharts\\genelist.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(response.getRows());
            oos.close();
            */

            for(Map<String,Object> row : responseList)
            {
                boolean trainOrTest;
                boolean tolOrSi;


                //we'll use continue if we fail on an assignment for train/test and tol/si to discard the row
                String pid = (String)row.get("ParticipantId");
                if(((String)row.get("dataType")).equals("train"))
                    trainOrTest = true;
                else if(((String)row.get("dataType")).equals("test"))
                    trainOrTest = false;
                else
                    continue;

                if(((String)row.get("dataType")).equals("train"))
                    trainOrTest = true;
                else if(((String)row.get("dataType")).equals("test"))
                    trainOrTest = false;
                else
                    continue;

                if(((String)row.get("Status")).equals("TOL"))
                    tolOrSi = true;
                else if(((String)row.get("Status")).equals("SI"))
                    tolOrSi = false;
                else
                    continue;

                float igkv1d13 = ((Double)row.get("IGKV1D_13")).floatValue();
                float igkv41 = ((Double)row.get("IGKV4_1")).floatValue();
                float igll1 = ((Double)row.get("IGLL1")).floatValue();


                GeneDataRow thisGeneDataRow = new GeneDataRow(pid, trainOrTest, tolOrSi, igkv1d13, igkv41, igll1);
                //quick way to see if things are working ... make sure the debug variable in the Main class is set to true
                //set to false for deployment
                if(Main.debug)thisGeneDataRow.printMe();
                geneDataRows.add(thisGeneDataRow);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //turns the GeneDataRow ArrayList into data points for the graphs(s)
    protected ArrayList<FactorPublicFig4Point> getDataPoints(MainApplet applet)
    {
        pointList = new ArrayList<>();
        for(GeneDataRow gdr: geneDataRows)
        {
            pointList.add(new FactorPublicFig4Point(gdr.igkv1d13,  gdr.igkv41, gdr.igll1,  gdr.trainOrTest, gdr.tolOrSi, gdr.pid, applet));

        }
        return pointList;
    }

}
