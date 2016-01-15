package org.immunetolerance.trialshare.processingThreeD;

/**
 * Created with IntelliJ IDEA.
 * User: DenW
 * Date: 1/4/13
 * Time: 11:25 AM
 *
 */
public class GeneDataRow
{
    String pid;
    boolean trainOrTest;
    boolean tolOrSi;
    float igkv1d13;
    float igkv41;
    float igll1;

    public GeneDataRow(String pid, boolean trainOrTest, boolean tolOrSi, float igkv1d13, float igkv41, float igll1)
    {
        this.pid = (pid != null) ? pid : "No ID";
        this.trainOrTest = trainOrTest;
        this.tolOrSi = tolOrSi;
        this.igkv1d13 = igkv1d13;
        this.igkv41 = igkv41;
        this.igll1 = igll1;
    }

    public void printMe()
    {
        System.out.println("-------------::gene data row::---------------");
        System.out.println("ParticipantId: " + this.pid);
        System.out.println("trainOrTest: " + this.trainOrTest);
        System.out.println("tolOrSi: " + this.tolOrSi);
        System.out.println("igkv1d13: " + this.igkv1d13);
        System.out.println("igkv41: " + this.igkv41);
        System.out.println("igll1: " + this.igll1);
    }
}
