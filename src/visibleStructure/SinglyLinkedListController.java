package visibleStructure;

import visibility.GUILangSupporter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class SinglyLinkedListController extends GeneralLinkedListController {

    public SinglyLinkedListController() {
        super();
    }

    private class SinglyLinkedNodeController extends GeneralLinkedNodeController {

        public SinglyLinkedNodeController(String data) {
            super(data);
        }

        @Override
        public void link(GeneralLinkedNodeController node) {
            this.next = node;
            if(node == null) nextField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
            else nextField.setText(Integer.toString(next.id));
        }

        @Override
        protected int defineID() {
            return ++count;
        }

        @Override
        protected JPanel linkInfoController() {
            return infoControllerPane;
        }

        @Override
        protected JSplitPane linkController() {
            return controller;
        }

        @Override
        protected ArrayList<Shape> getShape() {
            Point from;
            Point to;
            if (next != null) {
                if (self.pos.x < next.pos.x) {
                    from = new Point(self.pos.x + SIZE.width, self.pos.y + (SIZE.height >> 1));
                    to = new Point(next.pos.x, next.pos.y + (SIZE.height >> 1));
                } else if (self.pos.x > next.pos.x) {
                    from = new Point(self.pos.x, self.pos.y + (SIZE.height >> 1));
                    to = new Point(next.pos.x + SIZE.width, self.pos.y + (SIZE.height >> 1));
                } else {
                    from = new Point(self.pos.x + (SIZE.width >> 1), self.pos.y + SIZE.height);
                    to = new Point(next.pos.x + (SIZE.width >> 1), next.pos.y);
                }
            } else {
                from = new Point(self.pos.x + SIZE.width, self.pos.y);
                to = new Point(self.pos.x + (SIZE.width >> 1) * 3, self.pos.y - (SIZE.height >> 2));
            }
            return new ArrowLine(from, to, ArrowLine.Position.SINGLE).getShapeArray();
        }

    }


    @Override
    public void append(String data) {
        GeneralLinkedNodeController node = new SinglyLinkedNodeController(data);
        if(head == null) head = tail = node;
        else {
            tail.link(node);
            tail = node;
        }
        length++;
    }

    @Override
    public void ahead(String data) {
        GeneralLinkedNodeController node = new SinglyLinkedNodeController(data);
        if(head == null) head = tail = node;
        else {
            node.link(head);
            head = node;
        }
        length++;
    }

    @Override
    public void insert(GeneralLinkedNodeController node, String data) {
        if(node == tail) {
            append(data);
        } else {
            GeneralLinkedNodeController newNode = new SinglyLinkedNodeController(data);
            newNode.link(node.next);
            node.link(newNode);
            length++;
        }
    }

    @Override
    public void deleteTail() {
        if(head == tail) head = tail = null;
        else for(GeneralLinkedNodeController node = head; ; node = node.next) {
            if(node.next == tail) {
                tail = node;
                tail.link(null);
                break;
            }
        }
        length--;
    }

    @Override
    public void deleteHead() {
        if(head == tail) head = tail = null;
        else {
            head = head.next;
        }
        length--;
    }

    @Override
    public void deleteNode(GeneralLinkedNodeController node) {
        if(head == tail || node == tail) {
            deleteTail();
        } else if(head == node) {
            deleteHead();
        } else {
            for(GeneralLinkedNodeController pointer = head; ; pointer = pointer.next) {
                if(pointer.next == node) {
                    pointer.link(node.next);
                    break;
                }
            }
            length--;
        }
    }

}
