package visibleStructure;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * @author Yip Coekjan
 * @Date 10/15/2020
 */
public class ArrowLine {
    public static final int ANGLE = 15; // degree
    Point from, to;
    Position pos;

    public static enum Position {SINGLE, DOUBLE};

    public ArrowLine(Point from, Point to, Position pos) {
        this.from = from;
        this.to = to;
        this.pos = pos;
    }

    public ArrayList<Shape> getShapeSet() {
        ArrayList<Shape> shapes = new ArrayList<>();
        Point[] point = calculateArrowPoint();
        shapes.add(new Line2D.Double(
                from.x,
                from.y,
                to.x,
                to.y
        ));
        shapes.add(new Line2D.Double(
                to.x,
                to.y,
                point[0].x,
                point[0].y
        ));
        shapes.add(new Line2D.Double(
                to.x,
                to.y,
                point[1].x,
                point[1].y
        ));
        return shapes;
    }

    private Point[] calculateArrowPoint() {
        final Point[] points = new Point[2];
        points[0] = new Point();
        points[1] = new Point();
        final double TAN_ALPHA_DIV_2 = Math.tan(Math.toRadians(ANGLE) / 2);
        final double K = (double) (from.y - to.y) / (from.x - to.x);
        final double POS_KV = Math.tan(Math.atan(K) + Math.toRadians(ANGLE) / 2);
        final double NEG_KV = Math.tan(Math.atan(K) - Math.toRadians(ANGLE) / 2);
        final double POS_MK = (K * (from.y - to.y) + from.x - to.x) / (4 + 4 * K * POS_KV);
        final double NEG_MK = (K * (from.y - to.y) + from.x - to.x) / (4 + 4 * K * NEG_KV);
        if (from.y == to.y) {
            points[0].x = points[1].x = (from.x + 3 * to.x) >> 2;
            points[0].y = (int) (to.y + TAN_ALPHA_DIV_2 * ((from.x - to.x) >> 2));
            points[1].y = (int) (to.y - TAN_ALPHA_DIV_2 * ((from.x - to.x) >> 2));
        } else if (from.x == to.x) {
            points[0].x = (int) (to.x - ((from.y - to.y) >> 2) * TAN_ALPHA_DIV_2);
            points[1].x = (int) (to.x + ((from.y - to.y) >> 2) * TAN_ALPHA_DIV_2);
            points[0].y = points[1].y = (from.y + 3 * to.y) >> 2;
        } else {
            points[0].x = (int) (to.x + POS_MK);
            points[1].x = (int) (to.x + NEG_MK);
            points[0].y = (int) (to.y + POS_MK * POS_KV);
            points[1].y = (int) (to.y + NEG_MK * NEG_KV);
        }
        return points;
    }

}
