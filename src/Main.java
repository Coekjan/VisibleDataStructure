import struct.DoubleLinkedList;
import struct.LinkedList;
import visibility.Canvas;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class Main {
    public static void main(String[] args) {
        Canvas<String> cav = new Canvas<>(1000, 500, 20);
        DoubleLinkedList struct = new DoubleLinkedList(LinkedList.listAlign.Horizon);

        cav.setStruct(struct);

        cav.draw();
    }
}
