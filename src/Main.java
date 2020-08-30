import node.SinglyLinkedNode;
import struct.LinkedList;
import struct.SinglyLinkedList;
import visibility.Canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class Main {
    public static void main(String[] args) {
        Canvas<String> cav = new Canvas<>(1000, 500, 20);
        JButton button = new JButton("Add");
        SinglyLinkedList<String> struct = new SinglyLinkedList<>(LinkedList.listAlign.Vertical);
        cav.add(button, BorderLayout.EAST);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                struct.append(new SinglyLinkedNode<>("The End"));
                cav.repaint();
            }
        });
        struct.append(new SinglyLinkedNode<>("August 30th"))
                .ahead(new SinglyLinkedNode<>("My Diary",
                        70,
                        90,
                        Color.blue))
                .append(new SinglyLinkedNode<>("It is a nice day!!"))
                .append(new SinglyLinkedNode<>("Yip Coekjan"));
        cav.setStruct(struct).draw();
        cav.draw();
    }
}
