package app2dapi.graphics;

import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Polygon;
import app2dapi.geometry.G2D.Transformation2D;

/**
 *
 * @author tog
 */
public interface Canvas
{
    public void clear(Color c);
    public void setColor(Color c);
    public Color getColor();
    public void setTransformation(Transformation2D t);
    public Transformation2D getTransformation();
    public void drawLine(Point2D p1, Point2D p2);
    public void drawFilledPolygon(Polygon polygon);
    public void drawOutlinedPolygon(Polygon polygon);
    public void drawPoint(Point2D p, float size);
    public void drawPoint(Point2D p);
}
