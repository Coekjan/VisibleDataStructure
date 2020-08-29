package dataStructure.node;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/29/2020
 */
public abstract class DataNode<T> implements Node<T> {
    private final int width;
    private final int height;
    private Color color;
    private T data;

    public DataNode(T data, int width, int height) {
        this.color = Color.black;
        this.data = data;
        this.width = width;
        this.height = height;
    }

    public DataNode(T data, int width, int height, Color color) {
        this.color = color;
        this.data = data;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public abstract void draw(Graphics g, int x, int y);
}
