package dataStructure.view;

import dataStructure.node.Node;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * @author Yip Coekjan
 * @Date 8/29/2020
 */
public abstract class Canvas<T> extends JFrame {
    private final int width;
    private final int height;

    private final HashMap<Node<T>, Position> nodes = new HashMap<>();

    private class NPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Position pos;
            for(Node<T> node : nodes.keySet()) {
                pos = nodes.get(node);
                node.draw(g, pos.getX(), pos.getY());
            }
        }
    }

    protected abstract Position calculatePosition(Node<T> node);

    public void add(Node<T> node) {
        nodes.put(node, this.calculatePosition(node));
    }

    public int getNumberOfNode() {
        return nodes.size();
    }

    public Canvas(int width, int height) {
        add(new NPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.width = width;
        this.height = height;
    }

    public void draw() {
        setLocationRelativeTo(null);
        setSize(width, height);
        setVisible(true);
    }

}
