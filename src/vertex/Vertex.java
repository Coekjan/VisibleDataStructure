package vertex;

import edge.Edge;

import java.awt.*;
import java.util.HashSet;

/**
 * @author Yip Coekjan
 * @Date 8/28/2020
 */
public abstract class Vertex {
    private HashSet<Edge> edges = new HashSet<Edge>();
    public int getDegree() {
        return edges.size();
    }
    public abstract void paint(Graphics g, int x, int y);
}
