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
public interface IAnimation extends IUpdatable
{
    public void addFinishedListener(IAnimationFinishedListener listener);
    public void removeFinishedListener(IAnimationFinishedListener listener);
    public void start(double time);
    public double timeProgress();
    public double valueProgress();
    public boolean isFinished();
}
