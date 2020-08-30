package struct;

import shape.RectNode;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public abstract class LinkedNode<T> extends RectNode {
    protected final T data;

    protected LinkedNode(T data, int width, int height) {
        super(width, height);
        this.data = data;
    }

    protected LinkedNode(T data, int width, int height, Color color) {
        super(width, height, color);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public void draw(Graphics graphics, int x, int y) {
        super.draw(graphics, x, y);
        graphics.drawString(data.toString(), x, y);
    }

    public abstract byte getInter();
}
