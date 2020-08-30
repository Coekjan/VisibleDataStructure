package node;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class SinglyLinkedNode extends LinkedNode {
    private SinglyLinkedNode nextNode = null;

    public SinglyLinkedNode(String data) {
        super(data);
    }

    public SinglyLinkedNode(String data, int width, int height) {
        super(data, width, height);
    }

    public SinglyLinkedNode(String data, int width, int height, Color color) {
        super(data, width, height, color);
    }

    public SinglyLinkedNode next() {
        return nextNode;
    }

    public SinglyLinkedNode link(SinglyLinkedNode node) {
        nextNode = node;
        return node;
    }

}
