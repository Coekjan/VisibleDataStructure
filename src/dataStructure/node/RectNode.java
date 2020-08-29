package dataStructure.node;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/29/2020
 */
public class RectNode<T> extends DataNode<T> {

    private boolean isDrawn = false;

    public RectNode(T data, int width, int height) {
        super(data, width, height);
    }

    public RectNode(T data, int width, int height, Color color) {
        super(data, width, height, color);
    }

    public void setDrawn(boolean drawn) {
        this.isDrawn = drawn;
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(this.getColor());
        g.drawRect(x - (this.getWidth() >> 1), y - (this.getHeight() >> 1), this.getWidth(), this.getHeight());
        g.drawString(this.getData().toString(), x - (this.getWidth() >> 1), y - (this.getHeight() >> 1));
    }
}
