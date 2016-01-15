package org.immunetolerance.trialshare.processingThreeD;

import org.javatuples.Triplet;

/**
 * Created with IntelliJ IDEA.
 * User: denw
 * Date: 1/5/13
 * Time: 3:19 PM
 *
 */
public interface ColorTriplets
{
    Triplet<Integer,Integer,Integer> black = new Triplet<Integer, Integer, Integer>(0,0,0);
    Triplet<Integer,Integer,Integer> darkGrey = new Triplet<Integer, Integer, Integer>(24,24,24);
    Triplet<Integer,Integer,Integer> white = new Triplet<Integer, Integer, Integer>(255,255,255);
    Triplet<Integer,Integer,Integer> dullWhite = new Triplet<Integer, Integer, Integer>(232,232,232);
    Triplet<Integer,Integer,Integer> gridGreen = new Triplet<Integer, Integer, Integer>(96,128,96);
    Triplet<Integer,Integer,Integer> darkGreen = new Triplet<Integer, Integer, Integer>(32,164,32);
    Triplet<Integer,Integer,Integer> brightBlue = new Triplet<Integer, Integer, Integer>(64, 64, 255);
    Triplet<Integer,Integer,Integer> graphBlue = new Triplet<Integer, Integer, Integer>(96,128,96);
    Triplet<Integer,Integer,Integer> brightGreen = new Triplet<Integer, Integer, Integer>(64,255,64);
    Triplet<Integer,Integer,Integer> darkBlue = new Triplet<Integer, Integer, Integer>(32,32,164);
    Triplet<Integer,Integer,Integer> medBlue = new Triplet<Integer, Integer, Integer>(128, 128, 202);
    Triplet<Integer,Integer,Integer> lightGrey = new Triplet<Integer, Integer, Integer>(196, 196, 196);
    Triplet<Integer,Integer,Integer> lightGreen = new Triplet<Integer, Integer, Integer>(96, 196, 96);
}
