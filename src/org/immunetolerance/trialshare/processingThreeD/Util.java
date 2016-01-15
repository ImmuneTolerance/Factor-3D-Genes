package org.immunetolerance.trialshare.processingThreeD;

import processing.core.PApplet;
import remixlab.proscene.Quaternion;

/**
 * Created with IntelliJ IDEA.
 * User: denw
 * Date: 1/18/13
 * Time: 1:41 PM
 *
 *
 * @author adapted from http://content.gpwiki.org/index.php/OpenGL:Tutorials:Using_Quaternions_to_represent_rotation
 */
public class Util
{
    public static Quaternion convertPitchYawRoll(float pitch, float yaw, float roll)
    {
        float piOver180 = PApplet.PI/180f;
        float ph = pitch * piOver180 / 2.0f;
        float yw = yaw * piOver180 / 2.0f;
        float rl = roll * piOver180 / 2.0f;

        float sinp = (float) Math.sin(ph);
        float siny = (float) Math.sin(yw);
        float sinr = (float) Math.sin(rl);
        float cosp = (float) Math.cos(ph);
        float cosy = (float) Math.cos(yw);
        float cosr = (float) Math.cos(rl);

        float x = sinr * cosp * cosy - cosr * sinp * siny;
        float y = cosr * sinp * cosy + sinr * cosp * siny;
        float z = cosr * cosp * siny - sinr * sinp * cosy;
        float w = cosr * cosp * cosy + sinr * sinp * siny;

        return new Quaternion(x,y,z,w);
    }

    public static Quaternion normalize(Quaternion q)
    {
        float w = q.w;
        float x = q.x;
        float y = q.y;
        float z = q.z;
        float tolerance = 0.00001f;

        float mag2 = w * w + x * x + y * y + z * z;
        if (Math.abs(mag2) > tolerance && Math.abs(mag2 - 1.0f) > tolerance)
        {
            float mag = (float) Math.sqrt(mag2);
            w /= mag;
            x /= mag;
            y /= mag;
            z /= mag;
        }

        return new Quaternion(x,y,z,w);
    }

}
