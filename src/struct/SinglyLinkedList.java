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
    private final byte inter;

    public SinglyLinkedList(byte inter) {
        this.head = this.tail = null;
        if(SinglyLinkedNode.notAlign(inter)) throw new IllegalArgumentException();
        this.inter = inter;
    }

    public SinglyLinkedList(SinglyLinkedNode<T> node) {
        if(node == null) throw new IllegalArgumentException();
        this.head = this.tail = node;
        inter = node.getAlign();
        size = 1;
    }

    public SinglyLinkedList(SinglyLinkedNode<T> firstNode, SinglyLinkedNode<T> nextNode) {
        if(firstNode == null || nextNode == null || firstNode.getAlign() != nextNode.getAlign()) throw new IllegalArgumentException();
        this.head = firstNode;
        this.tail = nextNode;
        inter = firstNode.getAlign();
        head.link(tail);
        size = 2;
    }

    public SinglyLinkedList<T> append(SinglyLinkedNode<T> node) {
        if(node == null || node.getAlign() != inter) throw new IllegalArgumentException();
        if(tail == null) head = tail = node;
        else {
            tail.link(node);
            tail = node;
        }
        size++;
        return this;
    }

    public SinglyLinkedList<T> ahead(SinglyLinkedNode<T> node) {
        if(node == null || node.getAlign() != inter) throw new IllegalArgumentException();
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
        if(newNode.getAlign() != inter) throw new IllegalArgumentException();
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
        if(node == null || node.getAlign() != inter) return false;
        for(SinglyLinkedNode<T> pointer = head; pointer != null ; pointer = pointer.next()) {
            if(pointer == node) return true;
        }
        return false;
    }

    private void drawConnection(Graphics graphics, SinglyLinkedNode<T> node, int x, int y) {
        graphics.setFont(new Font("Default", Font.BOLD, 15));
        graphics.setColor(Color.black);
        switch (inter) {
            case SinglyLinkedNode.left_right:
                if(node == tail) return;
                else {
                    graphics.drawLine(x + node.getWidth(), y + (node.getHeight() >> 1),
                            x + (node.getWidth() << 1), y + (node.next().getHeight() >> 1));
                    graphics.drawString(rightArrow, x + node.getWidth(), y + (node.getHeight() >> 1));
                }
                return;
            case SinglyLinkedNode.up_down:
                if(node == tail) return;
                else {
                    graphics.drawLine(x + (node.getWidth() >> 1), y + node.getHeight(),
                            x + (node.next().getWidth() >> 1), y + (node.getHeight() << 1));
                    graphics.drawString(downArrow, x + (node.getWidth() >> 1), y + node.getHeight());
                }
                return;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public void draw(Graphics graphics, int x, int y) {
        for(SinglyLinkedNode<T> pointer = head; pointer != null; pointer = pointer.next()) {
            graphics.setFont(new Font("Times New Roman", Font.ITALIC, 15));
            pointer.draw(graphics, x, y);
            drawConnection(graphics, pointer, x, y);
            switch (inter) {
                case SinglyLinkedNode.left_right:
                    x += pointer.getWidth() << 1;
                    break;
                case SinglyLinkedNode.up_down:
                    y += pointer.getHeight() << 1;
                    break;
                default: throw new IllegalArgumentException();
            }
        }
    }
}
