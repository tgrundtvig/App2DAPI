/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation.impl;

/**
 *
 * @author Tobias Grundtvig
 */
public class AccCalc
{

    public static double AccDecValueProgress(double timeProgress)
    {
        if (timeProgress < 0.5)
        {
            return 2.0 * timeProgress * timeProgress;
        } else if(timeProgress < 1.0)
        {
            timeProgress = timeProgress - 0.5;
            return 0.5 + 2 * timeProgress * (1 - timeProgress);
        }
        else
        {
            return 1.0;
        }
    }
    
    public static double AccDecValue(double dist, double duration, double time)
    {
        return AccDecValueProgress(time / duration) * dist;
    }
    
    public static double AccDecValue(double startValue, double endValue, double startTime, double duration, double curTime)
    {
        return AccDecValue(endValue - startValue, duration, curTime-startTime);
    }
    
    
    public static double calcDist(double acc, double time)
    {
        return 0.0;
    }
    
    public static double calcDist(double startSpeed, double acc, double time)
    {
        return 0.0;
    }
    
    public static double calcAcc(double startSpeed, double dist, double time)
    {
        return 0.0;
    }
    
    
    public static double calcTime(double startSpeed, double dist, double acc)
    {
        return 0.0;
    }
    //more to come
}
