package visibility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class DrawablePane extends JPanel {
    protected final ArrayList<Shape> shapes = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Shape shape : shapes) {
            ((Graphics2D)g).draw(shape);
        }
    }

    public void add(Shape shape) {
        if (shape != null) {
            this.shapes.add(shape);
        }
    }
}
