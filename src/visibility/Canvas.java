package visibility;

import struct.Struct;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 8/30/2020
 */
public class Canvas<T> extends JFrame {
    private final int width;
    private final int height;
    private final int offset;
    public static final int defaultOffset = 20;

    private Struct struct = null;

    private class CanvasPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            struct.draw(g, offset, offset);
        }
    }

    public Canvas(int width, int height) {
        add(new CanvasPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.width = width;
        this.height = height;
        this.offset = defaultOffset;
    }

    public Canvas(int width, int height, Struct struct) {
        add(new CanvasPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.width = width;
        this.height = height;
        this.struct = struct;
        this.offset = defaultOffset;
    }

    public Canvas(int width, int height, int offset) {
        this.width = width;
        this.height = height;
        this.offset = offset;
    }

    public Canvas(int width, int height, int offset, Struct struct) {
        this.width = width;
        this.height = height;
        this.struct = struct;
        this.offset = offset;
    }

    public Canvas<T> setStruct(Struct struct) {
        this.struct = struct;
        return this;
    }

    public void draw() {
        add(new CanvasPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(width, height);
        setVisible(true);
    }

}
