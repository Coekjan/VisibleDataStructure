package node;

import shape.RectNode;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public abstract class LinkedNode extends RectNode {
    protected final String data;

    protected LinkedNode(String data) {
        super();
        this.data = data;
    }

    protected LinkedNode(String data, int width, int height) {
        super(width, height);
        this.data = data;
    }

    protected LinkedNode(String data, int width, int height, Color color) {
        super(width, height, color);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public void draw(Graphics graphics, int x, int y) {
        super.draw(graphics, x, y);
        graphics.drawString(data, x, y);
    }

}
