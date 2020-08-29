package dataStructure.node;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/29/2020
 */
public class RectNode<T> extends DataNode<T> {

    public RectNode(T data, int width, int height) {
        super(data, width, height);
    }

    public RectNode(T data, int width, int height, Color color) {
        super(data, width, height, color);
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(this.getColor());
        g.drawRect(x - this.getWidth() / 2, y - this.getHeight() / 2, this.getWidth(), this.getHeight());
        g.drawString(this.getData().toString(), x - this.getWidth() / 2, y - this.getHeight() / 2);
    }
}
