package structures;

import visibility.GlobalUserInterfaceLangController;
import visibility.CanvasPairController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.QuadCurve2D;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class SinglyLinkedList extends CanvasPairController {
    private SinglyLinkedNode head = null;
    private SinglyLinkedNode tail = null;
    private int length = 0;

    public SinglyLinkedList() {
        JButton appendButton = new JButton(GlobalUserInterfaceLangController.STRUCT_NODE_ADDER_APPEND.toString());
        appendButton.addActionListener(e -> {
            String inputContent = dialogInputDataForNode();
            if(inputContent != null) {
                append(inputContent);
                updateComponents();
            }
        });

        JButton aheadButton = new JButton(GlobalUserInterfaceLangController.STRUCT_NODE_ADDER_AHEAD.toString());
        aheadButton.addActionListener(e -> {
            String inputContent = dialogInputDataForNode();
            if(inputContent != null) {
                ahead(inputContent);
                updateComponents();
            }
        });

        JButton insertButton = new JButton(GlobalUserInterfaceLangController.STRUCT_NODE_ADDER_INSERT.toString());
        insertButton.addActionListener(e -> {
            if(length < 2) {
                dialogNotEnoughNode();
            } else {
                SinglyLinkedNode selected = dialogSelectNodeByID();
                String inputData = dialogInputDataForNode();
                insert(selected, inputData);
                updateComponents();
            }
        });

        JButton deleteTailButton = new JButton(GlobalUserInterfaceLangController.STRUCT_NODE_DELETER_TAIL.toString());
        deleteTailButton.addActionListener(e -> {
            if(length < 1) {
                dialogNotEnoughNode();
            } else {
                deleteTail();
                updateComponents();
            }
        });

        JButton deleteHeadButton = new JButton(GlobalUserInterfaceLangController.STRUCT_NODE_DELETER_HEAD.toString());
        deleteHeadButton.addActionListener(e -> {
            if(length < 1) {
                dialogNotEnoughNode();
            } else {
                deleteHead();
                updateComponents();
            }
        });

        JButton deleteSelectionButton = new JButton(GlobalUserInterfaceLangController.STRUCT_NODE_DELETER_SELECTION.toString());
        deleteSelectionButton.addActionListener(e -> {
            if(length < 1) {
                dialogNotEnoughNode();
            } else {
                SinglyLinkedNode selection = dialogSelectNodeByID();
                deleteNode(selection);
                updateComponents();
            }
        });

        adderAndDeleter.add(appendButton);
        adderAndDeleter.add(aheadButton);
        adderAndDeleter.add(insertButton);
        adderAndDeleter.add(deleteTailButton);
        adderAndDeleter.add(deleteHeadButton);
        adderAndDeleter.add(deleteSelectionButton);
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
            button = pos -> {
                JButton button = new JButton();
                this.pos.x = pos.x;
                this.pos.y = pos.y;
                button.setText(Integer.toString(this.id));
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
                            this.pos.x + 1.5 * SIZE.width,
                            this.pos.y - 0.5 * SIZE.height,
                            this.pos.x + (SIZE.width << 1),
                            this.pos.y
                    );
                } else {
                    shape = new QuadCurve2D.Double(
                            this.pos.x + SIZE.width,
                            this.pos.y,
                            (this.pos.x + (next.pos.x << 1)) / 3.0,
                            Math.min(pos.y, next.pos.y) - (SIZE.height >> 1),
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
            if(node == null) nextField.setText(GlobalUserInterfaceLangController.STRUCT_NODE_POINT_NULL.toString());
            else nextField.setText(Integer.toString(next.id));
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

    private SinglyLinkedNode dialogSelectNodeByID() {
        Integer[] idArray = new Integer[length];
        int i = 0;
        for(SinglyLinkedNode node = head; node != null; node = node.next, i++) {
            idArray[i] = node.id;
        }
        Integer inputContent = (Integer) JOptionPane.showInputDialog(
                getCanvas(),
                GlobalUserInterfaceLangController.STRUCT_NODE_CHOICE_MESSAGE.toString(),
                GlobalUserInterfaceLangController.STRUCT_NODE_CHOICE_TITLE.toString(),
                JOptionPane.QUESTION_MESSAGE,
                null,
                idArray,
                idArray[0]
        );
        for(SinglyLinkedNode node = head; node != null; node = node.next) {
            if(inputContent.equals(node.id)) {
                return node;
            }
        }
        return null;
    }

    private void dialogNotEnoughNode() {
        JOptionPane.showMessageDialog(
                null,
                GlobalUserInterfaceLangController.STRUCT_NOT_ENOUGH_NODES_MESSAGE.toString(),
                GlobalUserInterfaceLangController.WARNING.toString(),
                JOptionPane.WARNING_MESSAGE
        );
    }

    private void append(String data) {
        SinglyLinkedNode node = new SinglyLinkedNode(data);
        if(head == null) head = tail = node;
        else {
            tail.link(node);
            tail = node;
        }
        length++;
    }

    private void ahead(String data) {
        SinglyLinkedNode node = new SinglyLinkedNode(data);
        if(head == null) head = tail = node;
        else {
            node.link(head);
            head = node;
        }
        length++;
    }

    private void insert(SinglyLinkedNode node, String data) {
        if(node == tail) {
            append(data);
        } else {
            SinglyLinkedNode newNode = new SinglyLinkedNode(data);
            newNode.link(node.next);
            node.link(newNode);
            length++;
        }
    }

    private void deleteTail() {
        if(head == tail) head = tail = null;
        else for(SinglyLinkedNode node = head; ; node = node.next) {
            if(node.next == tail) {
                tail = node;
                tail.link(null);
                break;
            }
        }
        length--;
    }

    private void deleteHead() {
        if(head == tail) head = tail = null;
        else {
            head = head.next;
        }
        length--;
    }

    private void deleteNode(SinglyLinkedNode node) {
        if(head == tail || node == tail) {
            deleteTail();
        } else if(head == node) {
            deleteHead();
        } else {
            for(SinglyLinkedNode pointer = head; ; pointer = pointer.next) {
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
        final Point nextPoint = new Point(StructureNode.SIZE.width, StructureNode.SIZE.height);
        canvas.removeAll();
        for(SinglyLinkedNode pointer = head; pointer != null; pointer = pointer.next) {
            canvas.add(pointer.button.button(nextPoint));
            canvas.add(pointer.connections);
            nextPoint.x += StructureNode.SIZE.width << 1;
            if(nextPoint.x + StructureNode.SIZE.width >= canvas.getWidth()) {
                nextPoint.x = StructureNode.SIZE.width;
                nextPoint.y += StructureNode.SIZE.height << 1;
                if(nextPoint.y + StructureNode.SIZE.height >= canvas.getHeight()) {
                    break;
                }
            }
        }
        canvas.repaint();
    }
}
