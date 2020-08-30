import node.SinglyLinkedNode;
import shape.RectNode;
import struct.SinglyLinkedList;
import visibility.Canvas;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class Main {
    public static void main(String[] args) {
        Canvas<String> cav = new Canvas<>(1000, 500, 20);
        SinglyLinkedList<String> struct = new SinglyLinkedList<>(SinglyLinkedNode.up_down);
        struct.append(new SinglyLinkedNode<>("August 30th", SinglyLinkedNode.up_down))
                .ahead(new SinglyLinkedNode<>("My Diary",
                        70,
                        90,
                        SinglyLinkedNode.up_down,
                        Color.blue))
                .append(new SinglyLinkedNode<>("It is a nice day!!", SinglyLinkedNode.up_down))
                .append(new SinglyLinkedNode<>("Yip Coekjan", SinglyLinkedNode.up_down));
        cav.setStruct(struct).draw();
    }
}
