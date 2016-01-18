/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation;

/**
 *
 * @author Tobias Grundtvig
 */
public interface IScalarAnimations
{
    public IAnimatedScalar linear(double startValue, double endValue, double duration);
    public IAnimatedScalar accelerationAndDeceleration(double startValue, double endValue, double duration);
    
    
    
    public double calculateAccelerationDecelerationDuration(double distance, double accelleration);
    
    /*
    public double calculateAccelerationDecelerationDistance(double duration, double accelleration);
    public double calculateAccelerationDecelerationAcceleration(double distance, double duration);
    
    public double calculateAccelerationDuration(double distance, double accelleration);
    public double calculateAccelerationDistance(double duration, double accelleration);
    public double calculateAccelerationAcceleration(double distance, double duration);
    */
}
