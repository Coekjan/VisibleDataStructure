package visibleStructure;

import visibility.ButtonPairShapeConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        public ArrayList<Shape> shapes() {
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
            button.addActionListener(e -> onClickListener(linkInfoController(), linkController()));
            return button;
        }

        @Override
        public abstract ArrayList<Shape> shapes();
    }
    protected abstract ArrayList<Shape> getShape();
    protected abstract int defineID();
    protected abstract void onClickListener(JPanel infoController, JSplitPane controller);
    protected abstract JPanel linkInfoController();
    protected abstract JSplitPane linkController();
}