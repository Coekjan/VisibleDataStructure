package structures;

import visibility.ChangeableButton;
import visibility.ChangeableShape;
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
    public ChangeableButton button;

    protected final int id;

    public StructureNode(String data) {
        this.data = data;
        this.id = initID();
    }

    protected abstract int initID();
    protected abstract void onClickListener();
}