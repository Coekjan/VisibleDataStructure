package struct;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public abstract class LinkedList extends Struct {

    public enum listAlign {
        Horizon, Vertical
    }

    protected listAlign align;

    protected static final String rightArrow = ">";
    protected static final String leftArrow = "<";
    protected static final String upArrow = "^";
    protected static final String downArrow = "v";

    public void setAlign(listAlign align) {
        this.align = align;
    }

    public listAlign getAlign() {
        return align;
    }

    @Override
    public abstract void draw(Graphics g, int x, int y);

}
