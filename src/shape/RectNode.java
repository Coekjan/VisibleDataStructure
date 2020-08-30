package shape;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class RectNode extends NodeShape {
    private final static int defaultWidth = 50;
    private final static int defaultHeight = 50;

    protected RectNode() {
        super(defaultWidth,defaultHeight);
    }

    protected RectNode(int width, int height) {
        super(width, height);
    }

    protected RectNode(int width, int height, Color color) {
        super(width, height, color);
    }

    @Override
    public void draw(Graphics graphics, int x, int y) {
        super.draw(graphics, x, y);
        graphics.drawRect(x, y, width, height);
    }
}
