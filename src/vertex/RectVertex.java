package vertex;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/28/2020
 */
public class RectVertex extends Vertex {
    private final int width;
    private final int height;

    public RectVertex(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    public RectVertex(int width, int height, Color color) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics g, int x, int y) {
        g.drawRect(x, y, width, height);
        g.setColor(getColor());
    }
}
