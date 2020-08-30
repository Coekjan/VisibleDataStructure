package shape;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public interface Shape {
    Color getColor();
    void draw(Graphics graphics, int x, int y);
}
