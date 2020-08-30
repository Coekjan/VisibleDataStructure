package struct;

import visibility.Controller;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public abstract class Struct implements Controller {
    protected static final Font dataFont = new Font("Times New Roman", Font.ITALIC, 15);
    protected static final Font edgeFont = new Font("Default", Font.BOLD, 15);

    public abstract void draw(Graphics g, int x, int y);
}
