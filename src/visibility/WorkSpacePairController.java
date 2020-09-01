package visibility;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public abstract class WorkSpacePairController {
    protected DrawablePane workSpace;
    protected JTabbedPane controller;

    public WorkSpacePairController() {
        this.workSpace = new DrawablePane();
        this.controller = new JTabbedPane();

        this.workSpace.setLayout(null);
    }

    protected void setWorkSpace(DrawablePane workSpace) {
        this.workSpace = workSpace;
    }

    public DrawablePane getWorkSpace() {
        return workSpace;
    }

    protected void setController(JTabbedPane controller) {
        this.controller = controller;
    }

    public JTabbedPane getController() {
        return controller;
    }

}
