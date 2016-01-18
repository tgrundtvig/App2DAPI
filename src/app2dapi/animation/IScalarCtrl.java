/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation;

import app2dapi.util.INotifiable;

/**
 *
 * @author Tobias Grundtvig
 */
public interface IScalarCtrl extends IScalar, IUpdatable
{
    public void Initialize(double pos, double speed);
    public void setGoal(double pos, double speed);
    public void setGoal(double pos, double speed, double duration);
    public void setGoalListener(INotifiable listener);
    public double calculateMinTimeToGoal(double pos, double speed);
    public double timeToGoal();
    public boolean atGoal();
}
