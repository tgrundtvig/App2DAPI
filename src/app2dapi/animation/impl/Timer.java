/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation.impl;

import app2dapi.animation.ITimer;
import app2dapi.util.INotifiable;
import java.util.ArrayList;

/**
 *
 * @author Tobias Grundtvig
 */
public class Timer implements ITimer
{
    private ArrayList<InternalTimer> activeTimers;
    private ArrayList<InternalTimer> doneTimers;
    private double curTime;

    public Timer()
    {
        activeTimers = new ArrayList<>();
        doneTimers = new ArrayList<>();
        curTime = 0.0;
    }
    
    
    @Override
    public void setTimer(double seconds, INotifiable listener)
    {
        activeTimers.add(new InternalTimer(listener, curTime+seconds));
    }

    @Override
    public void update(double time)
    {
        curTime = time;
        for(InternalTimer timer : activeTimers)
        {
            if(time >= timer.dueTime)
            {
                doneTimers.add(timer);
            }
        }
        if(!doneTimers.isEmpty())
        {
            activeTimers.removeAll(doneTimers);
            for(InternalTimer timer : doneTimers)
            {
                timer.listener.doNotify();
            }
            doneTimers.clear();
        }
    }
    
    private class InternalTimer
    {
        public final INotifiable listener;
        public final double dueTime;

        public InternalTimer(INotifiable listener, double dueTime)
        {
            this.listener = listener;
            this.dueTime = dueTime;
        }
    }
    
}
