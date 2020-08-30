package node;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class DoubleLinkedNode extends LinkedNode {
    private DoubleLinkedNode nextNode = null;
    private DoubleLinkedNode preNode = null;

    public DoubleLinkedNode(String data) {
        super(data);
    }

    public DoubleLinkedNode(String data, int width, int height) {
        super(data, width, height);
    }

    public DoubleLinkedNode(String data, int width, int height, Color color) {
        super(data, width, height, color);
    }

    public DoubleLinkedNode next() {
        return nextNode;
    }

    public DoubleLinkedNode linkNext(DoubleLinkedNode node) {
        nextNode = node;
        return node;
    }

    public DoubleLinkedNode pre() {
        return preNode;
    }

    public DoubleLinkedNode linkPre(DoubleLinkedNode node) {
        preNode = node;
        return node;
    }

}
