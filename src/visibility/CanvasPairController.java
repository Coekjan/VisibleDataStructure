package visibility;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public abstract class CanvasPairController {
    protected int count = 0;
    protected final DrawablePane canvas = new DrawablePane();
    protected final JSplitPane controller = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    protected final JPanel controlPanel = new JPanel();
    protected final JPanel infoControllerPane = new JPanel();

    public CanvasPairController() {
        this.canvas.setLayout(null);
        this.infoControllerPane.setLayout(new GridLayout(0, 2));
        this.controlPanel.setLayout(new GridLayout(0, 2));
        this.controller.setLeftComponent(controlPanel);
        this.controller.setRightComponent(infoControllerPane);
        this.controller.setEnabled(false);
        this.controller.setDividerSize(2);
        this.controller.updateUI();
    }

    protected String dialogInputData() {
        return JOptionPane.showInputDialog(
                canvas,
                GUILangSupporter.DATA_ADD_MESSAGE.toString(),
                GUILangSupporter.DATA_ADD_DEFAULT.toString() + (count + 1)
        );
    }

    public DrawablePane getCanvas() {
        return canvas;
    }

    public JSplitPane getController() {
        return controller;
    }

    protected abstract void updateComponents();

}
