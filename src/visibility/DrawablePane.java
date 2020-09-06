package visibility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class DrawablePane extends JPanel {
    protected final ArrayList<ButtonPairShapeConstructor> shapes = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        for(ButtonPairShapeConstructor shape : shapes) {
            ((Graphics2D)g).setStroke(new BasicStroke(4));
            ((Graphics2D)g).draw(shape.shape());
        }
    }

    @Override
    public void removeAll() {
        super.removeAll();
        shapes.clear();
    }

    public void add(ButtonPairShapeConstructor buttonPairShape, int x, int y) {
        if(buttonPairShape != null) {
            add(buttonPairShape.button(x, y));
            shapes.add(buttonPairShape);
        }
    }
}