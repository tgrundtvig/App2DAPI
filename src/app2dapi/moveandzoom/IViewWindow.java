/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.moveandzoom;

import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Transformation2D;

/**
 *
 * @author Tobias Grundtvig
 */
public interface IViewWindow
{
    public void setScreenSize(int width, int height);
    public void setWorldPosition(double x, double y);
    public void setWorldPosition(Point2D worldPos);
    public void setScreenPosition(double x, double y);
    public void setScreenPosition(Point2D screenPos);
    public void setWindowWidth(double width);
    public void setWindowHeight(double height);
    public double getPositionX();
    public double getPositionY();
    public Point2D getWorldPosition();
    public Point2D getScreenPosition();
    public double getWidth();
    public double getHeight();
    public Transformation2D getWorldToScreen();
    public Transformation2D getScreenToWorld();
    public Point2D toWorld(Point2D screenPos);
    public Point2D toScreen(Point2D worldPos);
}
