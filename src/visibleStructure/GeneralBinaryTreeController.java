package visibleStructure;

import visibility.CanvasPairController;
import visibility.GUILangSupporter;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Yip Coekjan
 * @Date 10/26/2020
 */
public abstract class GeneralBinaryTreeController extends CanvasPairController {
    protected GeneralBinaryTreeNodeController root = null;
    protected ArrayList<GeneralBinaryTreeNodeController> nodes = new ArrayList<>();
    protected ArrayList<GeneralBinaryTreeNodeController> insertableNodes = new ArrayList<>();
    protected ArrayList<GeneralBinaryTreeNodeController> rightChildNodes = new ArrayList<>();
    protected ArrayList<GeneralBinaryTreeNodeController> leftChildNodes = new ArrayList<>();
    public static final int MAX_HEIGHT = 5;

    protected enum InsertAR {
        ADD, REMOVE
    }

    public GeneralBinaryTreeController() {
        super();

        JButton insertButton = new JButton(GUILangSupporter.STRUCT_NODE_ADDER_INSERT_POS.toString());
        insertButton.addActionListener(e -> {
            if (nodes.size() == 0) {
                String inputData = dialogInputData();
                if (inputData != null) {
                    initial(inputData);
                    nodes.add(root);
                    insertableNodes.add(root);
                    updateComponents();
                }
            } else {
                GeneralBinaryTreeNodeController selectedNode = dialogSelectInsertableNodeByID();
                if (selectedNode != null) {
                    if (selectedNode.layer == MAX_HEIGHT - 1) {
                        dialogNotEnoughSpace();
                    } else {
                        GeneralBinaryTreeNodeController.Position selectedPosition;
                        if (selectedNode.left == null && selectedNode.right == null) {
                            selectedPosition = dialogInputPosition();
                        } else if (selectedNode.left == null) {
                            selectedPosition = GeneralBinaryTreeNodeController.Position.LEFT;
                            if (!dialogConfirmPosition(selectedPosition))
                                selectedPosition = null;
                        } else {
                            selectedPosition = GeneralBinaryTreeNodeController.Position.RIGHT;
                            if (!dialogConfirmPosition(selectedPosition))
                                selectedPosition = null;
                        }
                        if (selectedPosition != null) {
                            String inputData = dialogInputData();
                            if (inputData != null) {
                                GeneralBinaryTreeNodeController newNode = insert(selectedNode, selectedPosition, inputData);
                                updateComponents();
                                nodes.add(newNode);
                                insertableNodes.add(newNode);
                                if (selectedPosition == GeneralBinaryTreeNodeController.Position.LEFT) {
                                    leftChildNodes.add(newNode);
                                } else {
                                    rightChildNodes.add(newNode);
                                }
                                if (selectedNode.left != null && selectedNode.right != null)
                                    insertableNodes.remove(selectedNode);
                            }
                        }
                    }
                }
            }
        });

        JButton deleteButton = new JButton(GUILangSupporter.STRUCT_NODE_DELETER_SELECTION.toString());
        deleteButton.addActionListener(e -> {
            if (nodes.size() <= 1) {
                dialogNotEnoughNode();
            } else {
                GeneralBinaryTreeNodeController selectedNode = dialogSelectNodeByID();
                if (selectedNode != null) {
                    GeneralBinaryTreeNodeController.Position selectedPosition;
                    if (selectedNode.left != null && selectedNode.right != null) {
                        selectedPosition = dialogInputPosition();
                    } else if (selectedNode.left != null) {
                        selectedPosition = GeneralBinaryTreeNodeController.Position.LEFT;
                        if (!dialogConfirmPosition(selectedPosition))
                            selectedPosition = null;
                    } else if (selectedNode.right != null) {
                        selectedPosition = GeneralBinaryTreeNodeController.Position.RIGHT;
                        if (!dialogConfirmPosition(selectedPosition))
                            selectedPosition = null;
                    } else { // This poor guy does not have any child!
                        selectedPosition = GeneralBinaryTreeNodeController.Position.NONE;
                    }
                    if (selectedPosition != null) {
                        HashMap<GeneralBinaryTreeNodeController, InsertAR> markNodes = delete(selectedNode, selectedPosition);
                        resetArgs();
                        updateComponents();
                        nodes.remove(selectedNode);
                        insertableNodes.remove(selectedNode);
                        findChildPos();
                        for (GeneralBinaryTreeNodeController node : markNodes.keySet()) {
                            if (markNodes.get(node) == InsertAR.ADD) {
                                if (!insertableNodes.contains(node)) insertableNodes.add(node);
                            } else {
                                insertableNodes.remove(node);
                            }
                        }
                    }
                }
            }
        });

        JButton leftRotateButton = new JButton(GUILangSupporter.STRUCT_TREE_NODE_LEFT_ROTATION.toString());
        leftRotateButton.addActionListener(e -> {
            if (nodes.size() < 2) {
                dialogNotEnoughNode();
            } else {
                GeneralBinaryTreeNodeController selectedNode = dialogSelectByIDFromArrayList(rightChildNodes);
                if (selectedNode != null) {
                    if (selectedNode.parent.left != null && selectedNode.parent.left.height >= MAX_HEIGHT - 1) {
                        dialogNotEnoughSpace();
                    } else {
                        HashMap<GeneralBinaryTreeNodeController, InsertAR> markNodes = leftRotate(selectedNode);
                        resetArgs();
                        findChildPos();
                        for (GeneralBinaryTreeNodeController node : markNodes.keySet()) {
                            if (markNodes.get(node) == InsertAR.ADD) {
                                if (!insertableNodes.contains(node)) insertableNodes.add(node);
                            } else {
                                insertableNodes.remove(node);
                            }
                        }
                        updateComponents();
                    }
                }
            }
        });

        JButton rightRotateButton = new JButton(GUILangSupporter.STRUCT_TREE_NODE_RIGHT_ROTATION.toString());
        rightRotateButton.addActionListener(e -> {
            if (nodes.size() < 2) {
                dialogNotEnoughNode();
            } else {
                GeneralBinaryTreeNodeController selectedNode = dialogSelectByIDFromArrayList(leftChildNodes);
                if (selectedNode != null) {
                    if (selectedNode.parent.right != null && selectedNode.parent.right.height >= MAX_HEIGHT - 1) {
                        dialogNotEnoughSpace();
                    } else {
                        HashMap<GeneralBinaryTreeNodeController, InsertAR> markNodes = rightRotate(selectedNode);
                        resetArgs();
                        findChildPos();
                        for (GeneralBinaryTreeNodeController node : markNodes.keySet()) {
                            if (markNodes.get(node) == InsertAR.ADD) {
                                if (!insertableNodes.contains(node)) insertableNodes.add(node);
                            } else {
                                insertableNodes.remove(node);
                            }
                        }
                        updateComponents();
                    }
                }
            }
        });
        controlPanel.add(insertButton);
        controlPanel.add(deleteButton);
        controlPanel.add(leftRotateButton);
        controlPanel.add(rightRotateButton);
    }

    private GeneralBinaryTreeNodeController dialogSelectNodeByID() {
        return dialogSelectByIDFromArrayList(nodes);
    }

    private GeneralBinaryTreeNodeController dialogSelectInsertableNodeByID() {
        return dialogSelectByIDFromArrayList(insertableNodes);
    }

    private GeneralBinaryTreeNodeController dialogSelectByIDFromArrayList(
            ArrayList<GeneralBinaryTreeNodeController> list) {
        Integer[] idArray = new Integer[list.size()];
        int i = 0;
        for (GeneralBinaryTreeNodeController node : list) {
            idArray[i] = node.id;
            i++;
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
        if (inputContent == null) return null;
        for (GeneralBinaryTreeNodeController node : list) {
            if (inputContent.equals(node.id)) return node;
        }
        return null;
    }

    private GeneralBinaryTreeNodeController.Position dialogInputPosition() {
        GeneralBinaryTreeNodeController.Position pos = null;
        Object[] options = new Object[]{
                GUILangSupporter.STRUCT_NODE_LEFT.toString(),
                GUILangSupporter.STRUCT_NODE_RIGHT.toString()
        };
        int selection = JOptionPane.showOptionDialog(
                getCanvas(),
                GUILangSupporter.STRUCT_NODE_INSERT_POSITION_SELECTION_TEXT.toString(),
                GUILangSupporter.STRUCT_NODE_INSERT_POSITION_SELECTION_TITLE.toString(),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
        if (selection >= 0) {
            pos = selection == 0 ?
                    GeneralBinaryTreeNodeController.Position.LEFT :
                    GeneralBinaryTreeNodeController.Position.RIGHT;
        }
        return pos;
    }

    private boolean dialogConfirmPosition(GeneralBinaryTreeNodeController.Position pos) {
        int res = JOptionPane.showConfirmDialog(
                getCanvas(),
                pos == GeneralBinaryTreeNodeController.Position.LEFT ?
                        GUILangSupporter.BINARY_TREE_POSITION_CONFIRM_LEFT.toString() :
                        GUILangSupporter.BINARY_TREE_POSITION_CONFIRM_RIGHT.toString(),
                GUILangSupporter.BINARY_TREE_POSITION_CONFIRM_TEXT.toString(),
                JOptionPane.YES_NO_OPTION
        );
        return res == JOptionPane.YES_OPTION;
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

    public abstract void initial(String data);

    public abstract GeneralBinaryTreeNodeController insert(
            GeneralBinaryTreeNodeController node,
            GeneralBinaryTreeNodeController.Position position,
            String data
    );

    public abstract HashMap<GeneralBinaryTreeNodeController, InsertAR> delete(
            GeneralBinaryTreeNodeController node,
            GeneralBinaryTreeNodeController.Position position
    );

    public abstract HashMap<GeneralBinaryTreeNodeController, InsertAR> leftRotate(GeneralBinaryTreeNodeController node);

    public abstract HashMap<GeneralBinaryTreeNodeController, InsertAR> rightRotate(GeneralBinaryTreeNodeController node);

    protected void resetArgs() {
        root.resetSize();
        root.resetHeight();
        root.resetLayer(0);
    }

    private void findChildPos() {
        leftChildNodes.clear();
        rightChildNodes.clear();
        findNodeChildPos(root);
    }

    private void findNodeChildPos(GeneralBinaryTreeNodeController node) {
        if (node.left != null) {
            leftChildNodes.add(node.left);
            findNodeChildPos(node.left);
        }
        if (node.right != null) {
            rightChildNodes.add(node.right);
            findNodeChildPos(node.right);
        }
    }

    protected void setRoot(GeneralBinaryTreeNodeController node) {
        root = node;
        root.parent = null;
        root.parentField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
    }

    protected class LayerNode {
        public GeneralBinaryTreeNodeController node;
        public int layer;
        public int leftBound;
        public int rightBound;

        public LayerNode(GeneralBinaryTreeNodeController node, int layer, int leftBound, int rightBound) {
            this.node = node;
            this.layer = layer;
            this.leftBound = leftBound;
            this.rightBound = rightBound;
        }

        public LayerNode(GeneralBinaryTreeNodeController node, int layer) {
            this(node, layer, 0, canvas.getWidth() - StructureNodeController.SIZE.width);
        }
    }

    @Override
    protected void updateComponents() {
        final Queue<LayerNode> queue = new LinkedList<>();
        canvas.removeAll();
        if (root != null) {
            queue.offer(new LayerNode(root, 0));
            while (!queue.isEmpty()) {
                LayerNode p = queue.poll();
                GeneralBinaryTreeNodeController node = p.node;
                double rate = ((double) node.getLeftSize()) / node.getRightSize();
                canvas.add(
                        p.node.buttonPairShape,
                        (int) ((p.rightBound * rate + p.leftBound) / (1 + rate)),
                        ((p.layer << 1) + 1) * StructureNodeController.SIZE.height
                );
                if (p.node.left != null) queue.offer(
                        new LayerNode(p.node.left, p.layer + 1, p.leftBound, p.node.pos.x)
                );
                if (p.node.right != null) queue.offer(
                        new LayerNode(p.node.right, p.layer + 1, p.node.pos.x, p.rightBound)
                );
            }
        }
        canvas.repaint();
    }
}
