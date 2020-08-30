package struct;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class SinglyLinkedNode<T> extends LinkedNode<T> {
    private SinglyLinkedNode<T> nextNode = null;
    private final byte inter;
    public final static byte up = 1;    // 0001
    public final static byte right = 2; // 0010
    public final static byte down = 4;  // 0100
    public final static byte left = 8;  // 1000

    public SinglyLinkedNode(T data, int width, int height) {
        super(data, width, height);
        this.inter = right | left;
    }

    public SinglyLinkedNode(T data, int width, int height, Color color) {
        super(data, width, height, color);
        this.inter = right | left;
    }

    public SinglyLinkedNode(T data, int width, int height, byte inter) {
        super(data, width, height);
        this.inter = inter;
    }

    public SinglyLinkedNode(T data, int width, int height, byte inter, Color color) {
        super(data, width, height, color);
        this.inter = inter;
    }

    public SinglyLinkedNode<T> next() {
        return nextNode;
    }

    public SinglyLinkedNode<T> link(SinglyLinkedNode<T> node) {
        nextNode = node;
        return node;
    }

    @Override
    public byte getInter() {
        return inter;
    }
}
