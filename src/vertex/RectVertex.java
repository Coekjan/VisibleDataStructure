package vertex;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/28/2020
 */
public class RectVertex extends Vertex {
    private int width;
    private int height;

    @Override
    public void paint(Graphics g, int x, int y) {
        g.drawRect(x, y, width, height);
    }
}
