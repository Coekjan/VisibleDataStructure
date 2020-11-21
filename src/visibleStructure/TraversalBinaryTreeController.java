package visibleStructure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author Yip Coekjan
 * @Date 11/21/2020
 */
public abstract class TraversalBinaryTreeController extends CommonBinaryTreeController {
    protected final HashMap<GeneralBinaryTreeNodeController, GeneralBinaryTreeNodeController> threads = new LinkedHashMap<>();

    private class TraversalBinaryTreeNodeController extends GeneralBinaryTreeNodeController {

        public TraversalBinaryTreeNodeController(String data) {
            super(data);
        }

        @Override
        protected ArrayList<Shape> getShape() {
            ArrayList<Shape> shapes = new ArrayList<>();
            if (threads.containsKey(self)) {
                GeneralBinaryTreeNodeController suc = threads.get(self);
                if (suc.pos.y < self.pos.y) {
                    shapes.addAll(new ArrowLine(
                            new Point(
                                    self.pos.x + SIZE.width,
                                    self.pos.y
                            ),
                            new Point(
                                    suc.pos.x + (SIZE.width >> 1),
                                    suc.pos.y + (SIZE.height)
                            ),
                            ArrowLine.Position.SINGLE
                    ).getShapeArray());
                } else if (suc.pos.y > self.pos.y) {
                    shapes.addAll(new ArrowLine(
                            new Point(
                                    self.pos.x + SIZE.width,
                                    self.pos.y + SIZE.height
                            ),
                            new Point(
                                    suc.pos.x + (SIZE.width >> 1),
                                    suc.pos.y
                            ),
                            ArrowLine.Position.SINGLE
                    ).getShapeArray());
                } else {
                    if (self.pos.x < suc.pos.x) {
                        shapes.addAll(new ArrowLine(
                                new Point(
                                        self.pos.x + SIZE.width,
                                        self.pos.y + (SIZE.height >> 1)
                                ),
                                new Point(
                                        suc.pos.x,
                                        suc.pos.y + (SIZE.height >> 1)
                                ),
                                ArrowLine.Position.SINGLE
                        ).getShapeArray());
                    } else {
                        shapes.addAll(new ArrowLine(
                                new Point(
                                        self.pos.x,
                                        self.pos.y + (SIZE.height >> 1)
                                ),
                                new Point(
                                        suc.pos.x + SIZE.width,
                                        suc.pos.y + (SIZE.height >> 1)
                                ),
                                ArrowLine.Position.SINGLE
                        ).getShapeArray());
                    }
                }
            }
            return shapes;
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
        root = new TraversalBinaryTreeNodeController(data);
        refactorThread();
    }

    @Override
    public GeneralBinaryTreeNodeController insert(GeneralBinaryTreeNodeController node, GeneralBinaryTreeNodeController.Position position, String data) {
        GeneralBinaryTreeNodeController newNode = new TraversalBinaryTreeNodeController(data);
        insertFunc(node, position, newNode);
        refactorThread();
        return newNode;
    }

    @Override
    public HashMap<GeneralBinaryTreeNodeController, InsertAR> delete(GeneralBinaryTreeNodeController node, GeneralBinaryTreeNodeController.Position position) {
        HashMap<GeneralBinaryTreeNodeController, InsertAR> hashMap = super.delete(node, position);
        refactorThread();
        return hashMap;
    }

    @Override
    public HashMap<GeneralBinaryTreeNodeController, InsertAR> leftRotate(GeneralBinaryTreeNodeController node) {
        HashMap<GeneralBinaryTreeNodeController, InsertAR> hashMap = super.leftRotate(node);
        refactorThread();
        return hashMap;
    }

    @Override
    public HashMap<GeneralBinaryTreeNodeController, InsertAR> rightRotate(GeneralBinaryTreeNodeController node) {
        HashMap<GeneralBinaryTreeNodeController, InsertAR> hashMap = super.rightRotate(node);
        refactorThread();
        return hashMap;
    }

    protected abstract void refactorThread();
}
