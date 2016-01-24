package app2dapi.geometry;

/**
 *
 * @author tog
 */
public interface G2D
{
    public interface Point2D
    {
        public double x();
        public double y();
    }

    public interface Vector2D
    {
        public double x();
        public double y();
        
        public double sqrLength();
        public double length();
    }

    public interface Transformation2D
    {
        public Point2D transform(Point2D p);
        public Vector2D transform(Vector2D v);
    }
    
    public interface BoundingBox2D
    {
        public Point2D getLowerLeft();
        public Point2D getLowerRight();
        public Point2D getUpperLeft();
        public Point2D getUpperRight();
        public Point2D getCenter();
        public double getWidth();
        public double getHeight();
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
    public Vector2D zeroVector2D();
    public Vector2D unitXVector();
    public Vector2D unitYVector();
    public Point2D newPoint2D(double x, double y);
    public Vector2D newVector2D(double x, double y);
    public Transformation2D identity();
    public Transformation2D scale(double sx, double sy);
    public Transformation2D scale(Vector2D s);
    public Transformation2D translate(double tx, double ty);
    public Transformation2D translate(Vector2D t);
    public Transformation2D translateOrigoTo(Point2D pos);
    public Transformation2D rotate(double angle);
    public Transformation2D rotate(double angle, Point2D pivot);
    public Transformation2D flipX();
    public Transformation2D flipY();
   
    
    //Geometry calculations
    public double angle(Vector2D a, Vector2D b);
    public double distance(Point2D a, Point2D b);
    public double length(Vector2D v);
    public double sqrLength(Vector2D v);
    public Vector2D normalized(Vector2D v);
    public Vector2D inverse(Vector2D v);
    public double dot(Vector2D a, Vector2D b);
    public Vector2D projection(Vector2D a, Vector2D b);
    public Vector2D rejection(Vector2D a, Vector2D b);
    public Vector2D add(Vector2D a, Vector2D b);
    public Vector2D subtract(Vector2D a, Vector2D b);
    public Point2D subtract(Point2D p, Vector2D v);
    public Vector2D fromTo(Point2D a, Point2D b);
    public Point2D add(Point2D p, Vector2D v);
    public Vector2D times(Vector2D v, double s);
    public Transformation2D inverse(Transformation2D t);
    public Transformation2D combine(Transformation2D t2, Transformation2D t1);
    public Transformation2D match(Point2D p1Src, Point2D p2Src, Point2D p1Dst, Point2D p2Dst);
    
    
    //Polygons
    public PolygonBuilder getPolygonBuilder();
    public Polygon createPolygon(Point2D[] points);
    public Polygon createRectangle(Point2D lowerLeft, Point2D upperRight);
    public Polygon createRectangle(Point2D center, double width, double height);
    public Polygon createCircle(Point2D center, double radius, int segments);
    public Polygon createArrow(Point2D begin, Point2D end, double width);
    public Polygon createLine(Point2D begin, Point2D end, double width);
    public Polygon createDoubleArrow(Point2D begin, Point2D end, double width);
    //More to come
}
