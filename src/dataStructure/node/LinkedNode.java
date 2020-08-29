package dataStructure.node;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/29/2020
 */
public class LinkedNode<T> extends RectNode<T> {

    public final int maxDegree;

    public LinkedNode(T data, int width, int height, int maxDegree) {
        super(data, width, height);
        this.maxDegree = maxDegree;
    }

    public LinkedNode(T data, int width, int height, int maxDegree, Color color) {
        super(data, width, height, color);
        this.maxDegree = maxDegree;
    }

}
