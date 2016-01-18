/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation.impl;

import app2dapi.animation.IAnimatedScalar;
import app2dapi.animation.IAnimationFinishedListener;
import app2dapi.animation.IScalarAnimations;
import java.util.ArrayList;

/**
 *
 * @author Tobias Grundtvig
 */
public class ScalarAnimations implements IScalarAnimations
{    

    @Override
    public IAnimatedScalar linear(double startValue, double endValue, double duration)
    {
        return new Linear(startValue, endValue, duration);
    }
    
    @Override
    public IAnimatedScalar accelerationAndDeceleration(double startValue, double endValue, double duration)
    {
        return new AccelerationDeceleration(startValue, endValue, duration);
    }

    @Override
    public double calculateAccelerationDecelerationDuration(double distance, double accelleration)
    {
        return Math.sqrt(distance/accelleration)*2.0;
    }

    
    private abstract static class AAnimatedScalar implements IAnimatedScalar
    {
        private final ArrayList<IAnimationFinishedListener> listeners;
        private double startTime;
        private final double duration;
        private final double startValue;
        private final double dist;
        
        private double valueProgress;
        private double timeProgress;

        public AAnimatedScalar(double startValue, double endValue, double duration)
        {
            listeners = new ArrayList<>();
            this.startValue = startValue;
            this.dist = endValue - startValue;
            this.duration = duration;
        }
        
        @Override
        public void addFinishedListener(IAnimationFinishedListener listener)
        {
            listeners.add(listener);
        }
        
        @Override
        public void removeFinishedListener(IAnimationFinishedListener listener)
        {
            listeners.remove(listener);
        }
        
        protected void notifyListeners()
        {
            for(IAnimationFinishedListener listener : listeners)
            {
                listener.onFinished();
            }
        }

        @Override
        public double value()
        {
            return startValue + valueProgress * dist;
        }

        @Override
        public void start(double time)
        {
            startTime = time;
            valueProgress = 0.0;
            timeProgress = 0.0;
        }
        
        @Override
        public double timeProgress()
        {
            return timeProgress;
        }

        @Override
        public double valueProgress()
        {
            return valueProgress;
        }

        @Override
        public boolean isFinished()
        {
            return timeProgress >= 1.0;
        }

        @Override
        public void update(double time)
        {
            timeProgress = (time - startTime) / duration;
            if(timeProgress >= 1.0)
            {
                timeProgress = 1.0;
                valueProgress = getValueProgress(timeProgress);
                notifyListeners();
            }
            else
            {
                valueProgress = getValueProgress(timeProgress);
            }
        }
        
        protected abstract double getValueProgress(double timeProgress);
    }
    
    private static class Linear extends AAnimatedScalar
    {
        public Linear(double startValue, double endValue, double duration)
        {
            super(startValue, endValue, duration);
        }

        @Override
        protected double getValueProgress(double timeProgress)
        {
            return timeProgress;
        }        
    }
    
    private static class AccelerationDeceleration extends AAnimatedScalar
    {
        public AccelerationDeceleration(double startValue, double endValue, double duration)
        {
            super(startValue, endValue, duration);
        }

        @Override
        protected double getValueProgress(double t)
        {
            if(t < 0.5)
            {
                return 2.0 * t * t;
            }
            else
            {
                t = t - 0.5;
                return 0.5 + 2 * t *( 1 - t );
            }
        }        
    }
}