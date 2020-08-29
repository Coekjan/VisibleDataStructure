package dataStructure.view;

import dataStructure.node.Node;

/**
 * @author Yip Coekjan
 * @Date 8/29/2020
 */
public class LinearCanvas<T> extends Canvas<T> {

    private final int separation;

    private int maxX;
    private final int maxY;

    public LinearCanvas(int separation, int width, int height) {
        super(width, height);
        this.maxX = this.maxY = this.separation = separation;
    }

    @Override
    protected Position calculatePosition(Node<T> node) {
        Position position = new Position(maxX + (node.getWidth() >> 1), maxY + (node.getHeight() >> 1));
        maxX += node.getWidth() + separation;
        return position;
    }
}
