package struct;

import node.SinglyLinkedNode;

import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class SinglyLinkedList<T> extends LinkedList<T> {
    private SinglyLinkedNode<T> head;
    private SinglyLinkedNode<T> tail;

    public SinglyLinkedList(listAlign align) {
        this.head = this.tail = null;
        this.align = align;
    }

    public SinglyLinkedList<T> append(SinglyLinkedNode<T> node) {
        if(node == null) throw new IllegalArgumentException();
        if(tail == null) head = tail = node;
        else {
            tail.link(node);
            tail = node;
        }
        size++;
        return this;
    }

    public SinglyLinkedList<T> ahead(SinglyLinkedNode<T> node) {
        if(node == null) throw new IllegalArgumentException();
        if(head == null) head = tail = node;
        else {
            node.link(head);
            head = node;
        }
        size++;
        return this;
    }

    public SinglyLinkedList<T> addAfter(SinglyLinkedNode<T> node, SinglyLinkedNode<T> newNode) {
        if(head == null || !contain(node)) throw new IllegalArgumentException();
        if(head == tail && node == head) append(newNode);
        else {
            newNode.link(node.next());
            node.link(newNode);
        }
        size++;
        return this;
    }

    public int size() {
        return size;
    }

    public boolean contain(SinglyLinkedNode<T> node) {
        if(node == null) return false;
        for(SinglyLinkedNode<T> pointer = head; pointer != null ; pointer = pointer.next()) {
            if(pointer == node) return true;
        }
        return false;
    }

    private void drawConnection(Graphics graphics, SinglyLinkedNode<T> node, int x, int y) {
        graphics.setFont(new Font("Default", Font.BOLD, 15));
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
    public void draw(Graphics graphics, int x, int y) {
        for(SinglyLinkedNode<T> pointer = head; pointer != null; pointer = pointer.next()) {
            graphics.setFont(new Font("Times New Roman", Font.ITALIC, 15));
            pointer.draw(graphics, x, y);
            drawConnection(graphics, pointer, x, y);
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
}
