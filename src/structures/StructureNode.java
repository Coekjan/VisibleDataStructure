package structures;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public abstract class StructureNode {
    public static final Dimension NODE_SIZE = new Dimension(50, 50);

    protected final int id;
    protected String data;
    protected final JButton button = new JButton();
    protected final Point pos;

    public StructureNode(String data, Point pos) {
        this.data = data;
        this.id = this.initID();
        this.pos = new Point(pos);
        button.setText(Integer.toString(id));
        button.setLocation(pos);
        button.setSize(NODE_SIZE);
        button.addActionListener(e -> onClickListener());
    }

    protected abstract int initID();
    protected abstract void onClickListener();
    public abstract Shape getConnection();
}
