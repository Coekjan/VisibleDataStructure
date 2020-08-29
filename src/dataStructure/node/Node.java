package dataStructure.node;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/29/2020
 */
public interface Node<T> {
    void draw(Graphics g, int x, int y);

    void setColor(Color color);

    Color getColor();

    void setData(T data);

    T getData();

    int getWidth();

    int getHeight();

}
