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
public abstract class AbstractG2D implements G2D
{
    private final Point2D ORIGO = newPoint2D(0,0);
    
    @Override
    public abstract Point2D newPoint2D(float x, float y);
    
    @Override
    public abstract Vector2D newVector2D(float x, float y);
    
    @Override
    public abstract Transformation2D scale(float sx, float sy);
    
    @Override
    public abstract Transformation2D translate(float tx, float ty);
    
    @Override
    public abstract Transformation2D rotate(float angle);
    
    @Override
    public abstract Transformation2D inverse(Transformation2D t);

    @Override
    public abstract Transformation2D combine(Transformation2D t2, Transformation2D t1);
    
    @Override
    public abstract PolygonBuilder getPolygonBuilder();
    
    @Override
    public Point2D origo()
    {
        return ORIGO;
    }
    
    @Override
    public Transformation2D scale(Vector2D s)
    {
        return scale(s.x(), s.y());
    }  

    @Override
    public Transformation2D translate(Vector2D t)
    {
        return translate(t.x(), t.y());
    }

    @Override
    public float angle(Vector2D a, Vector2D b)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public float length(Vector2D v)
    {
        return (float) Math.sqrt(sqrLength(v));
    }
    
    @Override
    public float sqrLength(Vector2D v)
    {
        float x = v.x();
        float y = v.y();
        return x*x + y*y;
    }

    @Override
    public Vector2D normalized(Vector2D v)
    {
        return times(v, 1.0f / length(v));
    }

    @Override
    public Vector2D inverse(Vector2D v)
    {
        return newVector2D(-v.x(), -v.y());
    }

    @Override
    public float dot(Vector2D a, Vector2D b)
    {
        return a.x() * b.x() + a.y() * b.y();
    }

    @Override
    public Vector2D projection(Vector2D a, Vector2D b)
    {
        Vector2D unitB = normalized(b);
        float f = dot(a, unitB);
        return times(a, f);
    }

    @Override
    public Vector2D rejection(Vector2D a, Vector2D b)
    {
        return subtract(a, projection(a, b));
    }

    @Override
    public Vector2D add(Vector2D a, Vector2D b)
    {
        return newVector2D(a.x() + b.x(), a.y() + b.y());
    }

    @Override
    public Vector2D subtract(Vector2D a, Vector2D b)
    {
        return newVector2D(a.x()-b.x(), a.y()-b.y());
    }
    
    @Override
    public Point2D subtract(Point2D p, Vector2D v)
    {
        return newPoint2D(p.x()-v.x(), p.y()-v.y());
    }

    @Override
    public Vector2D fromTo(Point2D a, Point2D b)
    {
        return newVector2D(b.x()-a.x(),b.y()-a.y());
    }

    @Override
    public Point2D add(Point2D p, Vector2D v)
    {
        return newPoint2D(p.x() + v.x(), p.y() + v.y());
    }

    @Override
    public Vector2D times(Vector2D v, float s)
    {
        return newVector2D(v.x()*s, v.y()*s);
    }

    @Override
    public Transformation2D match(Point2D p1Src, Point2D p2Src, Point2D p1Dst, Point2D p2Dst)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    @Override
    public Polygon createRectangle(Point2D lowerLeft, Point2D upperRight)
    {
        Point2D lowerRight = newPoint2D(upperRight.x(), lowerLeft.y());
        Point2D upperLeft = newPoint2D(lowerLeft.x(), upperRight.y());
        PolygonBuilder bld = getPolygonBuilder();
        bld.addPoint(lowerLeft);
        bld.addPoint(lowerRight);
        bld.addPoint(upperRight);
        bld.addPoint(upperLeft);
        return bld.build();
    }
    
    @Override
    public Polygon createRectangle(Point2D center, float width, float height)
    {
        PolygonBuilder bld = getPolygonBuilder();
        float halfWidth = width * 0.5f;
        float halfHeight = height * 0.5f;
        float minX = center.x() - halfWidth;
        float maxX = center.x() + halfWidth;
        float minY = center.y() - halfHeight;
        float maxY = center.y() + halfHeight;
        bld.addPoint(newPoint2D(minX, minY));
        bld.addPoint(newPoint2D(maxX, minY));
        bld.addPoint(newPoint2D(maxX, maxY));
        bld.addPoint(newPoint2D(minX, maxY));
        return bld.build();
    }
    
    @Override
    public Polygon createCircle(Point2D center, float radius, int segments)
    {
        if(segments < 3) throw new RuntimeException("There must be at least 3 segments! There are only " + segments + " segments!");
        PolygonBuilder res = getPolygonBuilder();
        float angle = (float)((Math.PI * 2) / segments);
        Transformation2D r = rotate(angle);
        Vector2D v = newVector2D(radius, 0);
        res.addPoint(add(center, v));
        for(int i = 1; i < segments; ++i)
        {
            v = r.transform(v);
            res.addPoint(add(center, v));
        }
        return res.build();
    }
    
    @Override
    public Polygon createArrow(Point2D begin, Point2D end, float width)
    {
        PolygonBuilder res = getPolygonBuilder();
        Vector2D vx = fromTo(begin, end);
        float xLength = vx.length();
        Vector2D unitX = times(vx, 1.0f / xLength);
        Vector2D unitY = newVector2D(-unitX.y(), unitX.x());
        Vector2D l = times(unitX, xLength - width);
        Vector2D h = times(unitY, width*0.5f);
        Point2D p = subtract(begin, h);
        res.addPoint(p);
        p = add(p, l);
        res.addPoint(p);
        p = subtract(p, h);
        res.addPoint(p);
        res.addPoint(end);
        p = add(p, times(h, 4));
        res.addPoint(p);
        p = subtract(p, h);
        res.addPoint(p);
        p = subtract(p, l);
        res.addPoint(p);
        return res.build();
    }
    
    @Override
    public Polygon createLine(Point2D begin, Point2D end, float width)
    {
        PolygonBuilder res = getPolygonBuilder();
        Vector2D vx = fromTo(begin, end);
        float xLength = vx.length();
        Vector2D unitX = times(vx, 1.0f / xLength);
        Vector2D unitY = newVector2D(-unitX.y(), unitX.x());
        Vector2D l = times(unitX, xLength);
        Vector2D h = times(unitY, width);
        Point2D p = subtract(begin, times(h, 0.5f));
        res.addPoint(p);
        p = add(p, l);
        res.addPoint(p);
        p = add(p, h);
        res.addPoint(p);
        p = subtract(p, l);
        res.addPoint(p);
        return res.build();
    }
    
    @Override
    public Polygon createDoubleArrow(Point2D begin, Point2D end, float width)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
