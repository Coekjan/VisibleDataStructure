package structures;

import visibility.GlobalUserInterfaceLangController;
import visibility.WorkSpaceConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.QuadCurve2D;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class SinglyLinkedList extends WorkSpaceConfig {

    private int count = 0;
    private SinglyLinkedNode head = null;
    private SinglyLinkedNode tail = null;

    public SinglyLinkedList() {
        canvasHandlerLayout.setRows(2);
        canvasHandlerLayout.setColumns(3);

        JButton appendButton = new JButton(GlobalUserInterfaceLangController.STRUCT_NODE_ADDER_APPEND.toString());
        appendButton.addActionListener(e -> {
            String inputContent = JOptionPane.showInputDialog(
                    this.canvas,
                    GlobalUserInterfaceLangController.STRUCT_NODE_ADD_MESSAGE.toString(),
                    GlobalUserInterfaceLangController.STRUCT_NODE_ADD_HINT.toString() + (count + 1)
            );
            if(inputContent != null) {
                append(inputContent);
                updateCanvasComponents();
            }
        });

        JButton aheadButton = new JButton(GlobalUserInterfaceLangController.STRUCT_NODE_ADDER_AHEAD.toString());
        aheadButton.addActionListener(e -> {
            String inputContent = JOptionPane.showInputDialog(
                    this.canvas,
                    GlobalUserInterfaceLangController.STRUCT_NODE_ADD_MESSAGE.toString(),
                    GlobalUserInterfaceLangController.STRUCT_NODE_ADD_HINT.toString() + (count + 1)
            );
            if(inputContent != null) {
                ahead(inputContent);
                updateCanvasComponents();
            }
        });

        canvasHandler.add(appendButton);
        canvasHandler.add(aheadButton);

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

        public SinglyLinkedNode(String data) {
            super(data);
            button = (pos) -> {
                this.pos.x = pos.x;
                this.pos.y = pos.y;
                JButton button = new JButton();
                button.setText(Integer.toString(id));
                button.setLocation(this.pos.x, this.pos.y);
                button.setSize(SIZE);
                button.addActionListener(e -> onClickListener());
                return button;
            };
            connections = () -> {
                Shape shape;
                if(next == null) {
                    shape = new QuadCurve2D.Double(
                            this.pos.x + SIZE.width,
                            this.pos.y,
                            this.pos.x + SIZE.width * 1.5,
                            this.pos.y - (SIZE.height >> 1),
                            this.pos.x + (SIZE.width << 1),
                            this.pos.y
                    );
                } else {
                    shape = new QuadCurve2D.Double(
                            this.pos.x + SIZE.width,
                            this.pos.y,
                            (this.pos.x + (next.pos.x << 1)) / 3.0,
                            Math.min(this.pos.y, next.pos.y) - (SIZE.height >> 1),
                            next.pos.x + (SIZE.width >> 1),
                            next.pos.y
                    );
                }
                return shape;
            };
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
            infoControllerLayout.setRows(3);
            infoControllerLayout.setColumns(2);
            infoController.removeAll();
            infoController.add(idText);
            infoController.add(idField);
            infoController.add(nextText);
            infoController.add(nextField);
            infoController.add(dataText);
            infoController.add(dataField);
            controller.updateUI();
        }
    }

    private void append(String data) {
        SinglyLinkedNode node = new SinglyLinkedNode(data);
        if(head == null) head = tail = node;
        else {
            tail.link(node);
            tail = node;
        }
    }

    private void ahead(String data) {
        SinglyLinkedNode node = new SinglyLinkedNode(data);
        if(head == null) head = tail = node;
        else {
            node.link(head);
            head = node;
        }
    }

    @Override
    public void config(JScrollPane workSpace) {

    }

    @Override
    protected void updateCanvasComponents() {
        final Point pos = new Point(StructureNode.SIZE.width, StructureNode.SIZE.height);
        canvas.removeAll();
        for(SinglyLinkedNode pointer = head; pointer != null; pointer = pointer.next) {
            canvas.add(pointer.button.button(pos));
            canvas.add(pointer.connections);
            pos.x += StructureNode.SIZE.width << 1;
            if(pos.x >= canvas.getWidth() - StructureNode.SIZE.width) {
                pos.y += StructureNode.SIZE.height << 1;
                pos.x = StructureNode.SIZE.width;
            }
        }
        canvas.repaint();
    }

}
