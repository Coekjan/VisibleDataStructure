package visibility;

import structures.ChangeableShape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class DrawablePane extends JPanel {
    protected final ArrayList<ChangeableShape> shapes = new ArrayList<>();

    public DrawablePane(LayoutManager layout) {
        super(layout);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ChangeableShape shape : shapes) {
            ((Graphics2D)g).setStroke(new BasicStroke(2));
            ((Graphics2D)g).draw(shape.shape());
        }
    }

    public void add(ChangeableShape shape) {
        if (shape != null) {
            this.shapes.add(shape);
        }
    }
}
