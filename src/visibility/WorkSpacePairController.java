package visibility;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public abstract class WorkSpacePairController {
    protected final DrawablePane workSpace = new DrawablePane();
    protected final JSplitPane controller = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    protected final JTabbedPane tabbedController = new JTabbedPane();
    protected final JPanel infoControllerPane = new JPanel();
    protected GridLayout infoController = new GridLayout(0, 2);

    public WorkSpacePairController() {
        this.workSpace.setLayout(null);
        this.controller.setLeftComponent(tabbedController);
        this.controller.setRightComponent(infoControllerPane);
        this.infoControllerPane.setLayout(infoController);
        this.controller.setEnabled(false);
        this.controller.setDividerSize(2);
        this.controller.updateUI();
    }

    public DrawablePane getCanvas() {
        return workSpace;
    }

    public JSplitPane getController() {
        return controller;
    }

    protected abstract void updateComponents();

}
