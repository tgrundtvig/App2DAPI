/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.geometry;

/**
 *
 * @author tog
 */
public interface G2D
{
    public interface Point2D
    {
        public float x();
        public float y();
    }

    public interface Vector2D
    {
        public float x();
        public float y();
        
        public float sqrLength();
        public float length();
    }

    public interface Transformation2D
    {
        public Point2D transform(Point2D p);
        public Vector2D transform(Vector2D v);
    }
    
    public interface Polygon extends Iterable<Point2D>
    {
        public int numberOfPoints();
        public Point2D getPoint(int index);
    }
    
    public interface PolygonBuilder
    {
        public void addPoint(Point2D p);
        public Polygon build();
    }
    
    //Creation:
    public Point2D origo();
    public Point2D newPoint2D(float x, float y);
    public Vector2D newVector2D(float x, float y);
    public Transformation2D identity();
    public Transformation2D scale(float sx, float sy);
    public Transformation2D scale(Vector2D s);
    public Transformation2D translate(float tx, float ty);
    public Transformation2D translate(Vector2D t);
    public Transformation2D rotate(float angle);
    public Transformation2D rotate(float angle, Point2D pivot);
    public Transformation2D flipX();
    public Transformation2D flipY();
   
    
    //Geometry calculations
    public float angle(Vector2D a, Vector2D b);
    public float length(Vector2D v);
    public float sqrLength(Vector2D v);
    public Vector2D normalized(Vector2D v);
    public Vector2D inverse(Vector2D v);
    public float dot(Vector2D a, Vector2D b);
    public Vector2D projection(Vector2D a, Vector2D b);
    public Vector2D rejection(Vector2D a, Vector2D b);
    public Vector2D add(Vector2D a, Vector2D b);
    public Vector2D subtract(Vector2D a, Vector2D b);
    public Point2D subtract(Point2D p, Vector2D v);
    public Vector2D fromTo(Point2D a, Point2D b);
    public Point2D add(Point2D p, Vector2D v);
    public Vector2D times(Vector2D v, float s);
    public Transformation2D inverse(Transformation2D t);
    public Transformation2D combine(Transformation2D t2, Transformation2D t1);
    public Transformation2D match(Point2D p1Src, Point2D p2Src, Point2D p1Dst, Point2D p2Dst);
    
    
    //Polygons
    public PolygonBuilder getPolygonBuilder();
    public Polygon createRectangle(Point2D lowerLeft, Point2D upperRight);
    public Polygon createRectangle(Point2D center, float width, float height);
    public Polygon createCircle(Point2D center, float radius, int segments);
    public Polygon createArrow(Point2D begin, Point2D end, float width);
    public Polygon createLine(Point2D begin, Point2D end, float width);
    public Polygon createDoubleArrow(Point2D begin, Point2D end, float width);
    //More to come
}
