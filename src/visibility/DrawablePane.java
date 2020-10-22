package visibility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class DrawablePane extends JPanel {
    protected final ArrayList<ButtonPairShapeConstructor> buttonPairShapeConstructors = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        for(ButtonPairShapeConstructor buttonPairShapeConstructor : buttonPairShapeConstructors) {
            ((Graphics2D)g).setStroke(new BasicStroke(2));
            ArrayList<Shape> shapes = buttonPairShapeConstructor.shapes();
            for (Shape shape : shapes) {
                ((Graphics2D) g).draw(shape);
            }
        }
    }

    @Override
    public void removeAll() {
        super.removeAll();
        buttonPairShapeConstructors.clear();
    }

    public void add(ButtonPairShapeConstructor buttonPairShape, int x, int y) {
        if(buttonPairShape != null) {
            add(buttonPairShape.button(x, y));
            buttonPairShapeConstructors.add(buttonPairShape);
        }
    }
}