package visibleStructure;

import visibility.CanvasPairController;
import visibility.GUILangSupporter;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 9/22/2020
 */
public abstract class GeneralLinkedListController extends CanvasPairController {
    protected GeneralLinkedNodeController head = null;
    protected GeneralLinkedNodeController tail = null;
    protected int length = 0;
    public static final int LEN_MAX = 50;

    public GeneralLinkedListController() {
        JButton appendButton = new JButton(GUILangSupporter.STRUCT_NODE_ADDER_APPEND.toString());
        appendButton.addActionListener(e -> {
            if(length >= LEN_MAX) {
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
            if(length >= LEN_MAX) {
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
            } else if(length >= LEN_MAX) {
                dialogNotEnoughSpace();
            } else {
                GeneralLinkedNodeController selected = dialogSelectNodeByID();
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
                GeneralLinkedNodeController selection = dialogSelectNodeByID();
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
    private GeneralLinkedNodeController dialogSelectNodeByID() {
        Integer[] idArray = new Integer[length];
        int i = 0;
        for(GeneralLinkedNodeController node = head; node != null; node = node.next, i++) {
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
        for(GeneralLinkedNodeController node = head; node != null; node = node.next) {
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

    public abstract void append(String data);

    public abstract void ahead(String data);

    public abstract void insert(GeneralLinkedNodeController node, String data);

    public abstract void deleteHead();

    public abstract void deleteTail();

    public abstract void deleteNode(GeneralLinkedNodeController node);

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
