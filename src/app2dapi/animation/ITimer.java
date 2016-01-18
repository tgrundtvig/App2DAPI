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
public interface ITimer extends IUpdatable
{
    public void setTimer(double seconds, INotifiable listener);
}
