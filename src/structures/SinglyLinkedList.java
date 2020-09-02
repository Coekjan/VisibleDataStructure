package structures;

import visibility.ChangeableShape;
import visibility.GlobalUserInterfaceLangController;
import visibility.WorkSpacePairController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class SinglyLinkedList extends WorkSpacePairController {

    private static int count = 0;
    private SinglyLinkedNode head = null;
    private SinglyLinkedNode tail = null;
    private final Point nextPoint = new Point(StructureNode.SIZE.width, StructureNode.SIZE.height);
    private final Box nodeAdder = Box.createVerticalBox();
    private final Box nodeDeleter = Box.createVerticalBox();

    public SinglyLinkedList() {
        this.tabbedController.addTab(GlobalUserInterfaceLangController.STRUCT_NODE_ADDER.toString(), nodeAdder);
        this.tabbedController.addTab(GlobalUserInterfaceLangController.STRUCT_NODE_DELETER.toString(), nodeDeleter);
        JButton appendButton = new JButton(GlobalUserInterfaceLangController.STRUCT_NODE_ADDER_APPEND.toString());
        appendButton.addActionListener(e -> {
            String inputContent = JOptionPane.showInputDialog(
                    this.workSpace,
                    GlobalUserInterfaceLangController.STRUCT_NODE_ADD_MESSAGE.toString(),
                    GlobalUserInterfaceLangController.STRUCT_NODE_ADD_TITLE.toString()
            );
            if(inputContent != null) append(inputContent);
        });
        nodeAdder.add(appendButton);
    }

    private class SinglyLinkedNode extends StructureNode {

        private SinglyLinkedNode next = null;
        private final SinglyLinkedNode self = this;
        private final JLabel idText = new JLabel(GlobalUserInterfaceLangController.STRUCT_NODE_ID_TEXT.toString());
        private final JTextField idField = new JTextField();
        private final JLabel dataText = new JLabel(GlobalUserInterfaceLangController.STRUCT_NODE_DATA_TEXT.toString());
        private final JTextField dataField = new JTextField();
        private final JLabel nextText = new JLabel(GlobalUserInterfaceLangController.SINGLY_LINKED_NODE_NEXT_TEXT.toString());
        private final JTextField nextField = new JTextField();

        public SinglyLinkedNode(String data, Point pos) {
            super(data, pos);
            connections = new PointToNodeConnection();
            idField.setText(Integer.toString(id));
            nextField.setText(GlobalUserInterfaceLangController.STRUCT_NODE_POINT_NULL.toString());
            idField.setEditable(false);
            nextField.setEditable(false);
            dataField.setEditable(true);
            dataField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    dataField.selectAll();
                }
                @Override
                public void focusLost(FocusEvent e) {
                    self.data = dataField.getText();
                }
            });
        }

        private class PointToNodeConnection implements ChangeableShape {
            @Override
            public Shape shape() {
                Shape shape = null;
                if(next == null) {
                    shape = new Line2D.Double(
                            pos.x + SIZE.width,
                            pos.y,
                            pos.x + (SIZE.width << 1),
                            pos.y - SIZE.height
                    );
                } else {
                    shape = new QuadCurve2D.Double(
                            pos.x + SIZE.width,
                            pos.y,
                            (pos.x + (next.pos.x << 1)) / 3.0,
                            Math.min(pos.y, next.pos.y) - (SIZE.height >> 1),
                            next.pos.x + (SIZE.width >> 1),
                            next.pos.y
                    );
                }
                return shape;
            }
        }

        public void link(SinglyLinkedNode node) {
            this.next = node;
            nextField.setText(Integer.toString(next.id));
        }

        @Override
        protected int initID() {
            return ++count;
        }

        @Override
        protected void onClickListener() {
            dataField.setText(data);
            infoControllerPane.removeAll();
            infoControllerPane.add(idText);
            infoControllerPane.add(idField);
            infoControllerPane.add(nextText);
            infoControllerPane.add(nextField);
            infoControllerPane.add(dataText);
            infoControllerPane.add(dataField);
            controller.updateUI();
        }
    }

    private void append(String data) {
        SinglyLinkedNode node = new SinglyLinkedNode(data, nextPoint);
        if(head == null) head = tail = node;
        else {
            tail.link(node);
            tail = node;
        }
        nextPoint.x += StructureNode.SIZE.width << 1;
        workSpace.add(node.connections);
        workSpace.add(node.button);
        workSpace.repaint();
    }

}
