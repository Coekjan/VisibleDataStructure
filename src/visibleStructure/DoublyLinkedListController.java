package visibleStructure;

import visibility.GUILangSupporter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;

/**
 * @author Yip Coekjan
 * @Date 9/22/2020
 */
public class DoublyLinkedListController extends GeneralLinkedListController {

    public DoublyLinkedListController() {
        super();
    }

    @Override
    public void append(String data) {

    }

    @Override
    public void ahead(String data) {

    }

    @Override
    public void insert(GeneralLinkedNodeController node, String data) {

    }

    @Override
    public void deleteHead() {

    }

    @Override
    public void deleteTail() {

    }

    @Override
    public void deleteNode(GeneralLinkedNodeController node) {

    }

    private class DoublyLinkedNodeController extends GeneralLinkedNodeController {

        public DoublyLinkedNodeController(String data) {
            super(data);
        }

        @Override
        public void link(GeneralLinkedNodeController node) {
            this.next = node;
            if(node == null) nextField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
            else {
                nextField.setText(Integer.toString(next.id));
                node.prev = this;
            }
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
            return new ArrowLine(from, to, ArrowLine.Position.DOUBLE).getShapeSet();
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

    }

    @Override
    protected void updateComponents() {

    }
}
