package node;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class SinglyLinkedNode<T> extends LinkedNode<T> {
    private SinglyLinkedNode<T> nextNode = null;
    private final byte align;
    public final static byte up_down = 0;
    public final static byte left_right = 1;

    public SinglyLinkedNode(T data) {
        super(data);
        this.align = left_right;
    }

    public SinglyLinkedNode(T data, byte inter) {
        super(data);
        this.align = inter;
    }

    public SinglyLinkedNode(T data, int width, int height) {
        super(data, width, height);
        this.align = left_right;
    }

    public SinglyLinkedNode(T data, int width, int height, Color color) {
        super(data, width, height, color);
        this.align = left_right;
    }

    public SinglyLinkedNode(T data, int width, int height, byte inter) {
        super(data, width, height);
        if(notAlign(inter)) throw new IllegalArgumentException();
        this.align = inter;
    }

    public SinglyLinkedNode(T data, int width, int height, byte inter, Color color) {
        super(data, width, height, color);
        if(notAlign(inter)) throw new IllegalArgumentException();
        this.align = inter;
    }

    public static boolean notAlign(byte inter) {
        if(inter == left_right || inter == up_down) return false;
        else return true;
    }

    public SinglyLinkedNode<T> next() {
        return nextNode;
    }

    public SinglyLinkedNode<T> link(SinglyLinkedNode<T> node) {
        nextNode = node;
        return node;
    }

    @Override
    public byte getAlign() {
        return align;
    }
}
