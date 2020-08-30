package node;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class SinglyLinkedNode<T> extends LinkedNode<T> {
    private SinglyLinkedNode<T> nextNode = null;

    public SinglyLinkedNode(T data) {
        super(data);
    }

    public SinglyLinkedNode(T data, int width, int height) {
        super(data, width, height);
    }

    public SinglyLinkedNode(T data, int width, int height, Color color) {
        super(data, width, height, color);
    }

    public SinglyLinkedNode<T> next() {
        return nextNode;
    }

    public SinglyLinkedNode<T> link(SinglyLinkedNode<T> node) {
        nextNode = node;
        return node;
    }

}
