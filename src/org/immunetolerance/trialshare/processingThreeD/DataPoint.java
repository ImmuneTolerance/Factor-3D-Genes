package org.immunetolerance.trialshare.processingThreeD;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: DenW
 * Date: 1/2/13
 * Time: 9:19 AM
 *
 */
public class DataPoint implements Comparable<DataPoint>
{
    protected String visit;
    protected double value;
    protected Date visitDate;
    protected int timeFromFirstVisit;

    public DataPoint(String itnVsnum, Double sumValue, Date vDate)
    {
        visit = itnVsnum;
        value = sumValue;
        visitDate = vDate;
    }

    public void setVDelta(int delta)
    {
        timeFromFirstVisit = delta;
    }

    @Override
    public int compareTo(DataPoint o) {
        return visitDate.compareTo(o.visitDate);
    }
}
