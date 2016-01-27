/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.moveandzoom;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Transformation2D;

/**
 *
 * @author Tobias Grundtvig
 */
public class ViewWindow implements IViewWindow
{
    private final G2D g2d;
    private boolean changed;
    private boolean screenToWorldCalculated;
    
    private double screenX;
    private double screenY;
    private double aspect;
    
    private Point2D worldPos;
    private Point2D screenPos;
    private double width;
    private double height;
    
    private Transformation2D worldToScreen;
    private Transformation2D screenToWorld;

    public ViewWindow(G2D g2d, int screenX, int screenY)
    {
        this.g2d = g2d;
        this.screenX = screenX;
        this.screenY = screenY;
        this.aspect = screenX / screenY;
        this.width = screenX;
        this.height = screenY;
        this.changed = true;
        this.screenToWorldCalculated = false;
        this.worldPos = g2d.origo();
        this.screenPos = g2d.origo();
    }
    
    
    @Override
    public void setScreenSize(int width, int height)
    {
        screenX = width;
        screenY = height;
        aspect = screenX / screenY;
        changed = true;
        screenToWorldCalculated = false;
    }

    @Override
    public void setWorldPosition(double x, double y)
    {
        worldPos = g2d.newPoint2D(x, y);
        changed = true;
        screenToWorldCalculated = false;
    }

    @Override
    public void setWorldPosition(G2D.Point2D pos)
    {
        this.worldPos = pos;
        changed = true;
        screenToWorldCalculated = false;
    }
    
    @Override
    public void setScreenPosition(double x, double y)
    {
        this.screenPos = g2d.newPoint2D(x, y);
        changed = true;
        screenToWorldCalculated = false;
    }

    @Override
    public void setScreenPosition(Point2D screenPos)
    {
        this.screenPos = screenPos;
        changed = true;
        screenToWorldCalculated = false;
    }

    @Override
    public void setWindowWidth(double width)
    {
        this.width = width;
        this.height = width / aspect;
        changed = true;
        screenToWorldCalculated = false;
    }

    @Override
    public void setWindowHeight(double height)
    {
        this.width = height * aspect;
        this.height = height;
        changed = true;
        screenToWorldCalculated = false;
    }

    @Override
    public double getPositionX()
    {
        return worldPos.x();
    }

    @Override
    public double getPositionY()
    {
        return worldPos.y();
    }

    @Override
    public Point2D getWorldPosition()
    {
        return worldPos;
    }
    
    @Override
    public Point2D getScreenPosition()
    {
        return screenPos;
    }


    @Override
    public double getWidth()
    {
        return width;
    }

    @Override
    public double getHeight()
    {
        return height;
    }

    @Override
    public Transformation2D getWorldToScreen()
    {
        if(changed)
        {
            calculate();
        }
        return worldToScreen;
    }

    @Override
    public Transformation2D getScreenToWorld()
    {
        if(changed)
        {
            calculate();
            screenToWorld = g2d.inverse(worldToScreen);
            screenToWorldCalculated = true;
        }
        else if(!screenToWorldCalculated)
        {
            screenToWorld = g2d.inverse(worldToScreen);
            screenToWorldCalculated = true;
        }
        return screenToWorld;
    }
    
    private void calculate()
    {
        Transformation2D t1 = g2d.translate(-worldPos.x(), -worldPos.y());
        double tmp = screenX / width;
        Transformation2D t2 = g2d.scale(tmp, tmp);
        Transformation2D t3 = g2d.combine(t2, t1);
        Transformation2D t4 = g2d.translate(screenPos.x(), screenPos.y());
        worldToScreen = g2d.combine(t4, t3);
        screenToWorldCalculated = false;
        changed = false;
    }

    @Override
    public Point2D toWorld(Point2D pos)
    {
        Transformation2D stw = getScreenToWorld();
        return stw.transform(pos);
    }

    @Override
    public Point2D toScreen(Point2D pos)
    {
        return getWorldToScreen().transform(pos);
    }
    
}
