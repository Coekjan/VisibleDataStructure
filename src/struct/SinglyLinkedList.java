package struct;

import node.SinglyLinkedNode;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class SinglyLinkedList extends LinkedList {
    private SinglyLinkedNode head;
    private SinglyLinkedNode tail;

    public SinglyLinkedList(listAlign align) {
        this.head = this.tail = null;
        this.align = align;
    }

    public SinglyLinkedList append(String data) {
        return append(new SinglyLinkedNode(data));
    }

    public SinglyLinkedList append(SinglyLinkedNode node) {
        if(node == null) throw new IllegalArgumentException();
        if(tail == null) head = tail = node;
        else {
            tail.link(node);
            tail = node;
        }
        return this;
    }

    public SinglyLinkedList ahead(String data) {
        return ahead(new SinglyLinkedNode(data));
    }

    public SinglyLinkedList ahead(SinglyLinkedNode node) {
        if(node == null) throw new IllegalArgumentException();
        if(head == null) head = tail = node;
        else {
            node.link(head);
            head = node;
        }
        return this;
    }

    public SinglyLinkedList addAfter(SinglyLinkedNode node, SinglyLinkedNode newNode) {
        if(head == null || !contain(node)) throw new IllegalArgumentException();
        if(head == tail && node == head) append(newNode);
        else {
            newNode.link(node.next());
            node.link(newNode);
        }
        return this;
    }

    public boolean contain(SinglyLinkedNode node) {
        if(node == null) return false;
        for(SinglyLinkedNode pointer = head; pointer != null ; pointer = pointer.next()) {
            if(pointer == node) return true;
        }
        return false;
    }

    private void drawConnection(Graphics graphics, SinglyLinkedNode node, int x, int y) {
        graphics.setFont(edgeFont);
        graphics.setColor(Color.black);
        switch (align) {
            case Horizon:
                if(node == tail) return;
                else {
                    graphics.drawLine(x + node.getWidth(), y + (node.getHeight() >> 1),
                            x + (node.getWidth() << 1), y + (node.next().getHeight() >> 1));
                    graphics.drawString(rightArrow, x + node.getWidth(), y + (node.getHeight() >> 1));
                }
                return;
            case Vertical:
                if(node == tail) return;
                else {
                    graphics.drawLine(x + (node.getWidth() >> 1), y + node.getHeight(),
                            x + (node.next().getWidth() >> 1), y + (node.getHeight() << 1));
                    graphics.drawString(downArrow, x + (node.getWidth() >> 1), y + node.getHeight());
                }
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + align);
        }
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        for(SinglyLinkedNode pointer = head; pointer != null; pointer = pointer.next()) {
            g.setFont(dataFont);
            pointer.draw(g, x, y);
            drawConnection(g, pointer, x, y);
            switch (align) {
                case Horizon:
                    x += pointer.getWidth() << 1;
                    break;
                case Vertical:
                    y += pointer.getHeight() << 1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + align);
            }
        }
    }

    @Override
    public JPanel getController(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(3, 1));

        JTextField input = new JTextField();

        String[] mode = {"Append", "Ahead"};

        JComboBox<String> list = new JComboBox<>(mode);

        JButton submitButton = new JButton("Add");

        SinglyLinkedList that = this;

        panel.add(input);
        panel.add(list);
        panel.add(submitButton);

        submitButton.addActionListener(e -> {
            String inputText = input.getText();
            if(inputText != null) {
                if(list.getSelectedIndex() == 0) {
                    that.append(inputText);
                } else {
                    that.ahead(inputText);
                }
            }
            frame.repaint();
        });
        return panel;
    }
}
