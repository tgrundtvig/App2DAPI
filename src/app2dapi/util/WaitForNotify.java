/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.util;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Tobias Grundtvig
 */
public class WaitForNotify implements INotifiable
{
    private boolean notified = false;
    private final Object monitor;

    public WaitForNotify(Object monitor)
    {
        this.monitor = monitor;
    }
    
    @Override
    public void doNotify()
    {
        synchronized(monitor)
        {
            notified = true;
            monitor.notifyAll();
        }
    }
    
    public void waitForNotify()
    {
        synchronized(monitor)
        {
            while(!notified)
            {
                try
                {
                    monitor.wait();
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(WaitForNotify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
