package structures;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public abstract class StructureNode {
    public static final Dimension SIZE = new Dimension(50, 50);

    protected String data;
    protected Point pos = new Point();
    public ChangeableShape connections;

    protected final int id;
    protected ChangeableButton button;

    public StructureNode(String data) {
        this.data = data;
        this.id = this.initID();
    }

    protected abstract int initID();
    protected abstract void onClickListener();
}
