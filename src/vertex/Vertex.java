package vertex;

import java.awt.*;
import java.util.HashSet;

/**
 * @author Yip Coekjan
 * @Date 8/28/2020
 */
public abstract class Vertex {
    private static int count = 0;
    private final int id = count++;
    private Color color;
    private final HashSet<Vertex> vertexes = new HashSet<>();

    public Vertex() {
        this.color = Color.black;
    }

    public Vertex(Color color) {
        this.color = color;
    }

    public void linkTo(Vertex v) {
        vertexes.add(v);
    }

    public void connectWith(Vertex v) {
        this.linkTo(v);
        v.linkTo(this);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String listVer() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id).append(": [ ");
        for(Vertex v : vertexes) {
            sb.append(v.id).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    public abstract void paint(Graphics g, int x, int y);
}
