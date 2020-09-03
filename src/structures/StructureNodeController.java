package structures;

import visibility.ButtonPairShapeConstructor;
import visibility.GUIFramework;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public abstract class StructureNodeController {
    public static final Dimension SIZE = new Dimension(50, 50);

    protected String data;
    protected Point pos = new Point();
    protected final StructureNodeController self = this;
    public final StructureNodeButtonPairShape buttonPairShape = new StructureNodeButtonPairShape() {
        @Override
        public Shape shape() {
            return getShape();
        }
    };

    protected final int id;

    public StructureNodeController(String data) {
        this.data = data;
        this.id = defineID();
    }

    protected abstract class StructureNodeButtonPairShape implements ButtonPairShapeConstructor {
        @Override
        public JButton button(int x, int y) {
            JButton button = new JButton();
            self.pos.x = x;
            self.pos.y = y;
            button.setText(Integer.toString(self.id));
            button.setLocation(self.pos.x, self.pos.y);
            button.setSize(SIZE);
            button.addActionListener(e -> onClickListener());
            return button;
        }

        @Override
        public abstract Shape shape();
    }
    protected abstract Shape getShape();
    protected abstract int defineID();
    protected abstract void onClickListener();
}