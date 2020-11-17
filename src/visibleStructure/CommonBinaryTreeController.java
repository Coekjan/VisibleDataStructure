package visibleStructure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Yip Coekjan
 * @Date 11/10/2020
 */
public class CommonBinaryTreeController extends GeneralBinaryTreeController {

    public CommonBinaryTreeController() {
        super();
    }

    private class CommonBinaryTreeNodeController extends GeneralBinaryTreeNodeController {

        public CommonBinaryTreeNodeController(String data) {
            super(data);
        }

        @Override
        protected ArrayList<Shape> getShape() {
            ArrayList<Shape> shapeArrayList = new ArrayList<>();
            if (left != null) {
                shapeArrayList.addAll(
                        new ArrowLine(
                                new Point(self.pos.x + (SIZE.width >> 1), self.pos.y + SIZE.height),
                                new Point(left.pos.x + (SIZE.width >> 1), left.pos.y),
                                ArrowLine.Position.DOUBLE
                        ).getShapeArray()
                );
            }
            if (right != null) {
                shapeArrayList.addAll(
                        new ArrowLine(
                                new Point(self.pos.x + (SIZE.width >> 1), self.pos.y + SIZE.height),
                                new Point(right.pos.x + (SIZE.width >> 1), right.pos.y),
                                ArrowLine.Position.DOUBLE
                        ).getShapeArray()
                );
            }
            return shapeArrayList;
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
    public void initial(String data) {
        root = new CommonBinaryTreeNodeController(data);
    }

    @Override
    public GeneralBinaryTreeNodeController insert(GeneralBinaryTreeNodeController node, GeneralBinaryTreeNodeController.Position position, String data) {
        GeneralBinaryTreeNodeController newNode = new CommonBinaryTreeNodeController(data);
        switch (position) {
            case LEFT:
                node.leftLinkWithUpdate(newNode);
                break;
            case RIGHT:
                node.rightLinkWithUpdate(newNode);
                break;
            default:
                break;
        }
        return newNode;
    }

    @Override
    public HashMap<GeneralBinaryTreeNodeController, InsertAR> delete(GeneralBinaryTreeNodeController node, GeneralBinaryTreeNodeController.Position position) {
        HashMap<GeneralBinaryTreeNodeController, InsertAR> res = new HashMap<>();
        switch (position) {
            case LEFT:
                if (node.left.right == null) {
                    node.left.rightLink(node.right);
                    if (node == root) {
                        setRoot(node.left);
                    } else {
                        if (node == node.parent.left) {
                            node.parent.leftLink(node.left);
                        } else {
                            node.parent.rightLink(node.left);
                        }
                    }
                    if (node.left.left != null) {
                        res.put(node.left, InsertAR.REMOVE);
                    }
                } else {
                    GeneralBinaryTreeNodeController pointer = node.left.right;
                    while (pointer.right != null) pointer = pointer.right;
                    pointer.parent.rightLink(pointer.left);
                    if (pointer.left == null) {
                        res.put(pointer.parent, InsertAR.ADD);
                    }
                    if (node.left != null && node.right != null) {
                        res.put(pointer, InsertAR.REMOVE);
                    }
                    pointer.leftLink(node.left);
                    pointer.rightLink(node.right);
                    if (node == root) {
                        setRoot(pointer);
                    } else {
                        if (node == node.parent.left) {
                            node.parent.leftLink(pointer);
                        } else {
                            node.parent.rightLink(pointer);
                        }
                    }
                }
                break;
            case RIGHT:
                if (node.right.left == null) {
                    node.right.leftLink(node.left);
                    if (node == root) {
                        setRoot(node.right);
                    } else {
                        if (node == node.parent.right) {
                            node.parent.rightLink(node.right);
                        } else {
                            node.parent.leftLink(node.right);
                        }
                    }
                    if (node.right.right != null) {
                        res.put(node.right, InsertAR.REMOVE);
                    }
                } else {
                    GeneralBinaryTreeNodeController pointer = node.right.left;
                    while (pointer.left != null) pointer = pointer.left;
                    pointer.parent.leftLink(pointer.right);
                    if (pointer.right == null) {
                        res.put(pointer.parent, InsertAR.ADD);
                    }
                    if (node.left != null && node.right != null) {
                        res.put(pointer, InsertAR.REMOVE);
                    }
                    pointer.rightLink(node.right);
                    pointer.leftLink(node.left);
                    if (node == root) {
                        setRoot(pointer);
                    } else {
                        if (node == node.parent.right) {
                            node.parent.rightLink(pointer);
                        } else {
                            node.parent.leftLink(pointer);
                        }
                    }
                }
                break;
            default:
                if (node == node.parent.left) {
                    node.parent.leftLink(null);
                } else {
                    node.parent.rightLink(null);
                }
                res.put(node.parent, InsertAR.ADD);
        }
        return res;
    }

    @Override
    public HashMap<GeneralBinaryTreeNodeController, InsertAR> leftRotate(GeneralBinaryTreeNodeController node) {
        HashMap<GeneralBinaryTreeNodeController, InsertAR> res = new HashMap<>();
        GeneralBinaryTreeNodeController parent = node.parent;
        GeneralBinaryTreeNodeController leftChild = node.left;
        if (node.right != null && node.left == null) {
            res.put(node, InsertAR.REMOVE);
        }
        if (parent.left != null && node.left == null) {
            res.put(node, InsertAR.ADD);
        }
        if (node.parent == root) {
            setRoot(node);
            root.leftLink(parent);
        } else {
            if (parent == parent.parent.right) {
                parent.parent.rightLink(node);
            } else {
                parent.parent.leftLink(node);
            }
            node.leftLink(parent);
        }
        parent.rightLink(leftChild);
        return res;
    }

    @Override
    public HashMap<GeneralBinaryTreeNodeController, InsertAR> rightRotate(GeneralBinaryTreeNodeController node) {
        HashMap<GeneralBinaryTreeNodeController, InsertAR> res = new HashMap<>();
        GeneralBinaryTreeNodeController parent = node.parent;
        GeneralBinaryTreeNodeController rightChild = node.right;
        if (node.left != null && node.right == null) {
            res.put(node, InsertAR.REMOVE);
        }
        if (parent.right != null && node.right == null) {
            res.put(node, InsertAR.ADD);
        }
        if (node.parent == root) {
            setRoot(node);
            root.rightLink(parent);
        } else {
            if (parent == parent.parent.left) {
                parent.parent.leftLink(node);
            } else {
                parent.parent.rightLink(node);
            }
            node.rightLink(parent);
        }
        parent.leftLink(rightChild);
        return res;
    }
}
