/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation.impl;

import app2dapi.animation.IScalarCtrl;
import app2dapi.util.INotifiable;

/**
 *
 * @author Tobias Grundtvig
 */
public class LinearScalarCtrl implements IScalarCtrl
{
    private final double maxSpeed;
    private double curSpeed;
    private double curPos;
    private double curTime;
    private double goal;
    private boolean atGoal;
    private INotifiable listener;

    public LinearScalarCtrl(double maxSpeed)
    {
        this.maxSpeed = maxSpeed;
        curSpeed = maxSpeed;
        curPos = 0.0;
        curTime = 0.0;
        goal = 0.0;
        atGoal = true;
        listener = null;
    }
    
    
    @Override
    public void Initialize(double pos, double speed)
    {
        curPos = pos;
        goal = pos;
        atGoal = true;  
    }

    @Override
    public void setGoal(double pos, double speed)
    {
        goal = pos;
        curSpeed = maxSpeed;
        atGoal = false;
    }
    
     @Override
    public void setGoal(double pos, double speed, double duration)
    {
        goal = pos;
        double dist = Math.abs(goal - curPos);
        curSpeed = Math.min(maxSpeed, dist / duration);
        atGoal = false;
    }

    @Override
    public void setGoalListener(INotifiable listener)
    {
        this.listener = listener;
    }

    @Override
    public double calculateMinTimeToGoal(double pos, double speed)
    {
        double dist = Math.abs(goal-curPos);
        return dist / maxSpeed;
    }

    @Override
    public double timeToGoal()
    {
        double dist = Math.abs(goal-curPos);
        return dist / curSpeed;
    }

    @Override
    public boolean atGoal()
    {
        return atGoal;
    }

    @Override
    public double value()
    {
        return curPos;
    }

    @Override
    public void update(double time)
    {  
        double oldCur = curTime;
        curTime = time;
        if(atGoal) return;
        double moveDist = (time - oldCur) * curSpeed;
        double dist = Math.abs(goal-curPos);
        if(moveDist <= dist)
        {
            if(goal > curPos)
            {
                curPos += moveDist;
            }
            else
            {
                curPos -= moveDist;
            }
        }
        else
        {
            curPos = goal;
            atGoal = true;
            if(listener != null) listener.doNotify();
        }
        
    } 
}
