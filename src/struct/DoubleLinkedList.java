package struct;

import node.DoubleLinkedNode;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class DoubleLinkedList extends LinkedList {
    private DoubleLinkedNode head;
    private DoubleLinkedNode tail;

    public DoubleLinkedList(listAlign align) {
        this.head = this.tail = null;
        this.align = align;
    }

    public DoubleLinkedList append(String data) {
        return append(new DoubleLinkedNode(data));
    }

    public DoubleLinkedList append(DoubleLinkedNode node) {
        if(node == null) throw new IllegalArgumentException();
        if(tail == null) head = tail = node;
        else {
            tail.linkNext(node);
            node.linkPre(tail);
            tail = node;
        }
        return this;
    }

    public DoubleLinkedList ahead(String data) {
        return ahead(new DoubleLinkedNode(data));
    }

    public DoubleLinkedList ahead(DoubleLinkedNode node) {
        if(node == null) throw new IllegalArgumentException();
        if(head == null) head = tail = node;
        else {
            node.linkNext(head);
            head.linkPre(node);
            head = node;
        }
        return this;
    }

    public DoubleLinkedList addAfter(DoubleLinkedNode node, DoubleLinkedNode newNode) {
        if(head == null || !contain(node)) throw new IllegalArgumentException();
        if(head == tail && node == head) append(newNode);
        else {
            newNode.linkNext(node.next());
            newNode.linkPre(node);
            node.next().linkPre(newNode);
            node.linkNext(newNode);
        }
        return this;
    }

    public boolean contain(DoubleLinkedNode node) {
        if(node == null) return false;
        for(DoubleLinkedNode pointer = head; pointer != null; pointer = pointer.next()) {
            if(pointer == node) return true;
        }
        return false;
    }

    private void drawConnection(Graphics graphics, DoubleLinkedNode node, int x, int y) {
        graphics.setFont(edgeFont);
        graphics.setColor(Color.black);
        switch (align) {
            case Horizon:
                if(node == tail) return;
                else {
                    graphics.drawLine(x + node.getWidth(), y + (node.getHeight() >> 2),
                            x + (node.getWidth() << 1), y + (node.next().getHeight() >> 2));
                    graphics.drawLine(x + node.getWidth(), y + (node.getHeight() >> 2) * 3,
                            x + (node.getWidth() << 1), y + (node.next().getHeight() >> 2) * 3);
                    graphics.drawString(rightArrow, x + node.getWidth(), y + (node.getHeight() >> 2));
                    graphics.drawString(leftArrow, x + node.getWidth(), y + (node.getHeight() >> 2) * 3);
                }
                return;
            case Vertical:
                if(node == tail) return;
                else {
                    graphics.drawLine(x + (node.getWidth() >> 2), y + node.getHeight(),
                            x + (node.next().getWidth() >> 2), y + (node.getHeight() << 1));
                    graphics.drawLine(x + (node.getWidth() >> 2) * 3, y + node.getHeight(),
                            x + (node.next().getWidth() >> 2) * 3, y + (node.getHeight() << 1));
                    graphics.drawString(downArrow, x + (node.getWidth() >> 2), y + node.getHeight());
                    graphics.drawString(upArrow, x + (node.getWidth() >> 2) * 3, y + node.getHeight());
                }
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + align);
        }
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        for(DoubleLinkedNode pointer = head; pointer != null; pointer = pointer.next()) {
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

        DoubleLinkedList that = this;

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
