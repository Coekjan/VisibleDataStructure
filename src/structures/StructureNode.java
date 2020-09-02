package structures;

import visibility.ChangeableShape;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public abstract class StructureNode {
    public static final Dimension SIZE = new Dimension(50, 50);

    protected String data;
    protected Point pos;
    public ChangeableShape connections;

    protected final int id;
    protected final JButton button = new JButton();

    public StructureNode(String data, Point pos) {
        this.data = data;
        this.id = this.initID();
        this.pos = new Point(pos);
        button.setText(Integer.toString(id));
        button.setLocation(pos);
        button.setSize(SIZE);
        button.addActionListener(e -> onClickListener());
    }

    protected abstract int initID();
    protected abstract void onClickListener();
}
