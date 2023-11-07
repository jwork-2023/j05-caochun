package world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Winterstorm
 */
public class Line implements Iterable<Point> {

    private List<Point> points;

    public List<Point> getPoints() {
        return this.points;
    }

    public Line(Point p1, Point p2) {
        this(p1.x, p1.y, p2.x, p2.y);
    }

    public Line(int x0, int y0, int x1, int y1) {
        this.points = new ArrayList<Point>();

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            this.points.add(new Point(x0, y0));
            if (x0 == x1 || y0 == y1) {
                break;
            }

            int e2 = err * 2;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
        ;
    }

    public Iterator<Point> iterator() {
        return points.iterator();
    }
}
