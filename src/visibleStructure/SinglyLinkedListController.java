package visibleStructure;

import visibility.GUILangSupporter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;

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
        protected Shape getShape() {
            Shape shape;
            if(next == null) {
                shape = new CubicCurve2D.Double(
                        self.pos.x + (SIZE.width >> 1),
                        self.pos.y,
                        self.pos.x + (SIZE.width >> 2),
                        self.pos.y - (SIZE.height >> 3),
                        self.pos.x + 3 * (SIZE.width >> 2),
                        self.pos.y - 3 * (SIZE.height >> 3),
                        self.pos.x + (SIZE.width >> 1),
                        self.pos.y - (SIZE.height >> 2)
                );
            } else {
                if(self.pos.x == next.pos.x) {
                    shape = new Line2D.Double(
                            self.pos.x + (SIZE.width >> 1),
                            self.pos.y + SIZE.height,
                            next.pos.x + (SIZE.width >> 1),
                            next.pos.y
                    );
                } else if(self.pos.x < next.pos.x) {
                    shape = new QuadCurve2D.Double(
                            self.pos.x + SIZE.width,
                            self.pos.y + (SIZE.height >> 1),
                            next.pos.x,
                            self.pos.y - (SIZE.height >> 1),
                            next.pos.x + (SIZE.width >> 1),
                            next.pos.y
                    );
                } else {
                    shape = new QuadCurve2D.Double(
                            self.pos.x,
                            self.pos.y + (SIZE.height >> 1),
                            next.pos.x + SIZE.width,
                            next.pos.y - (SIZE.height >> 1),
                            next.pos.x + (SIZE.width >> 1),
                            next.pos.y
                    );
                }
            }
            return shape;
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

    @Override
    protected void updateComponents() {
        int sign = 1;
        final Point drawPos = new Point(-StructureNodeController.SIZE.width, StructureNodeController.SIZE.height);
        canvas.removeAll();
        for(GeneralLinkedNodeController pointer = head; pointer != null; pointer = pointer.next) {
            drawPos.x += sign * (StructureNodeController.SIZE.width << 1);
            canvas.add(pointer.buttonPairShape, drawPos.x, drawPos.y);
            if(drawPos.x + (sign * StructureNodeController.SIZE.width << 1) < 0 ||
                    drawPos.x + (sign * StructureNodeController.SIZE.width << 2) >= canvas.getWidth()) {
                drawPos.x += sign * (StructureNodeController.SIZE.width << 1);
                drawPos.y += StructureNodeController.SIZE.height << 1;
                sign = -sign;
            }
        }
        canvas.repaint();
    }
}
