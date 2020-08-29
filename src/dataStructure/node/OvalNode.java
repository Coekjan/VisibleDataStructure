package dataStructure.node;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/29/2020
 */
public abstract class OvalNode<T> extends DataNode<T> {

    public OvalNode(T data, int majorAxis, int minorAxis) {
        super(data, majorAxis, minorAxis);
    }

    public OvalNode(T data, int majorAxis, int minorAxis, Color color) {
        super(data, majorAxis, minorAxis, color);
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(this.getColor());
        g.drawOval(x - (this.getWidth() >> 1), y - (this.getHeight() >> 1), this.getWidth(), this.getHeight());
        g.drawString(this.getData().toString(), x - (this.getWidth() >> 1), y - (this.getHeight() >> 1));
    }
}
