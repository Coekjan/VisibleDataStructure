package shape;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public abstract class NodeShape implements Shape {

    protected final int width;
    protected final int height;
    protected Color color;

    protected NodeShape(int width, int height) {
        this.width = width;
        this.height = height;
        color = Color.black;
    }

    protected NodeShape(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void draw(Graphics graphics, int x, int y) {
        graphics.setColor(color);
    }
}
