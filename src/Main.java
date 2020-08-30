import node.SinglyLinkedNode;
import struct.LinkedList;
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
        SinglyLinkedList<String> struct = new SinglyLinkedList<>(LinkedList.listAlign.Vertical);
        struct.append(new SinglyLinkedNode<>("August 30th"))
                .ahead(new SinglyLinkedNode<>("My Diary",
                        70,
                        90,
                        Color.blue))
                .append(new SinglyLinkedNode<>("It is a nice day!!"))
                .append(new SinglyLinkedNode<>("Yip Coekjan"));
        cav.setStruct(struct).draw();
    }
}
