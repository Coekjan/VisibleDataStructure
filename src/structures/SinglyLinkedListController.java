package structures;

import visibility.GUILangSupporter;
import visibility.CanvasPairController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class SinglyLinkedListController extends CanvasPairController {
    private SinglyLinkedNodeController head = null;
    private SinglyLinkedNodeController tail = null;
    private int length = 0;

    public SinglyLinkedListController() {
        JButton appendButton = new JButton(GUILangSupporter.STRUCT_NODE_ADDER_APPEND.toString());
        appendButton.addActionListener(e -> {
            if(length >= 50) {
                dialogNotEnoughSpace();
            } else {
                String inputContent = dialogInputData();
                if(inputContent != null) {
                    append(inputContent);
                    updateComponents();
                }
            }
        });

        JButton aheadButton = new JButton(GUILangSupporter.STRUCT_NODE_ADDER_AHEAD.toString());
        aheadButton.addActionListener(e -> {
            if(length >= 50) {
                dialogNotEnoughSpace();
            } else {
                String inputContent = dialogInputData();
                if(inputContent != null) {
                    ahead(inputContent);
                    updateComponents();
                }
            }
        });

        JButton insertButton = new JButton(GUILangSupporter.STRUCT_NODE_ADDER_INSERT.toString());
        insertButton.addActionListener(e -> {
            if(length < 1) {
                dialogNotEnoughNode();
            } else if(length >= 50) {
                dialogNotEnoughSpace();
            } else {
                SinglyLinkedNodeController selected = dialogSelectNodeByID();
                String inputData = dialogInputData();
                insert(selected, inputData);
                updateComponents();
            }
        });

        JButton deleteTailButton = new JButton(GUILangSupporter.STRUCT_NODE_DELETER_TAIL.toString());
        deleteTailButton.addActionListener(e -> {
            if(length < 1) {
                dialogNotEnoughNode();
            } else {
                deleteTail();
                updateComponents();
            }
        });

        JButton deleteHeadButton = new JButton(GUILangSupporter.STRUCT_NODE_DELETER_HEAD.toString());
        deleteHeadButton.addActionListener(e -> {
            if(length < 1) {
                dialogNotEnoughNode();
            } else {
                deleteHead();
                updateComponents();
            }
        });

        JButton deleteSelectionButton = new JButton(GUILangSupporter.STRUCT_NODE_DELETER_SELECTION.toString());
        deleteSelectionButton.addActionListener(e -> {
            if(length < 1) {
                dialogNotEnoughNode();
            } else {
                SinglyLinkedNodeController selection = dialogSelectNodeByID();
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

    private class SinglyLinkedNodeController extends StructureNodeController {

        private SinglyLinkedNodeController next = null;
        private final SinglyLinkedNodeController self = this;
        private final JLabel idText = new JLabel(GUILangSupporter.STRUCT_NODE_ID_TEXT.toString());
        private final JTextField idField = new JTextField();
        private final JLabel dataText = new JLabel(GUILangSupporter.STRUCT_NODE_DATA_TEXT.toString());
        private final JTextField dataField = new JTextField();
        private final JLabel nextText = new JLabel(GUILangSupporter.SINGLY_LINKED_NODE_NEXT_TEXT.toString());
        private final JTextField nextField = new JTextField();

        public SinglyLinkedNodeController(String data) {
            super(data);
            idField.setText(Integer.toString(id));
            nextField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
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

        public void link(SinglyLinkedNodeController node) {
            this.next = node;
            if(node == null) nextField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
            else nextField.setText(Integer.toString(next.id));
        }

        @Override
        protected int defineID() {
            return ++count;
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

    private SinglyLinkedNodeController dialogSelectNodeByID() {
        Integer[] idArray = new Integer[length];
        int i = 0;
        for(SinglyLinkedNodeController node = head; node != null; node = node.next, i++) {
            idArray[i] = node.id;
        }
        Integer inputContent = (Integer) JOptionPane.showInputDialog(
                getCanvas(),
                GUILangSupporter.STRUCT_NODE_CHOICE_MESSAGE.toString(),
                GUILangSupporter.STRUCT_NODE_CHOICE_TITLE.toString(),
                JOptionPane.QUESTION_MESSAGE,
                null,
                idArray,
                idArray[0]
        );
        for(SinglyLinkedNodeController node = head; node != null; node = node.next) {
            if(inputContent.equals(node.id)) {
                return node;
            }
        }
        return null;
    }

    private void dialogNotEnoughNode() {
        JOptionPane.showMessageDialog(
                null,
                GUILangSupporter.STRUCT_NOT_ENOUGH_NODES_MESSAGE.toString(),
                GUILangSupporter.WARNING.toString(),
                JOptionPane.WARNING_MESSAGE
        );
    }

    private void dialogNotEnoughSpace() {
        JOptionPane.showMessageDialog(
                null,
                GUILangSupporter.STRUCT_NOT_ENOUGH_SPACE_MESSAGE.toString(),
                GUILangSupporter.WARNING.toString(),
                JOptionPane.WARNING_MESSAGE
        );
    }

    private void append(String data) {
        SinglyLinkedNodeController node = new SinglyLinkedNodeController(data);
        if(head == null) head = tail = node;
        else {
            tail.link(node);
            tail = node;
        }
        length++;
    }

    private void ahead(String data) {
        SinglyLinkedNodeController node = new SinglyLinkedNodeController(data);
        if(head == null) head = tail = node;
        else {
            node.link(head);
            head = node;
        }
        length++;
    }

    private void insert(SinglyLinkedNodeController node, String data) {
        if(node == tail) {
            append(data);
        } else {
            SinglyLinkedNodeController newNode = new SinglyLinkedNodeController(data);
            newNode.link(node.next);
            node.link(newNode);
            length++;
        }
    }

    private void deleteTail() {
        if(head == tail) head = tail = null;
        else for(SinglyLinkedNodeController node = head; ; node = node.next) {
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

    private void deleteNode(SinglyLinkedNodeController node) {
        if(head == tail || node == tail) {
            deleteTail();
        } else if(head == node) {
            deleteHead();
        } else {
            for(SinglyLinkedNodeController pointer = head; ; pointer = pointer.next) {
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
        for(SinglyLinkedNodeController pointer = head; pointer != null; pointer = pointer.next) {
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
