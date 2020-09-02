package visibility;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public abstract class WorkSpaceConfig {
    protected final SpringLayout workSpaceLayout = new SpringLayout();

    protected final DrawablePane canvas = new DrawablePane(null);

    protected final SpringLayout controllerLayout = new SpringLayout();
    protected final JPanel controller = new JPanel(controllerLayout);

    protected final GridLayout canvasHandlerLayout = new GridLayout(0, 0);
    protected final JPanel canvasHandler = new JPanel(canvasHandlerLayout);

    protected final GridLayout infoControllerLayout = new GridLayout(0, 0);
    protected final JPanel infoController = new JPanel(infoControllerLayout);


    public void config(JPanel workSpace) {
        workSpace.setLayout(workSpaceLayout);
        workSpace.add(canvas);
        workSpace.add(controller);

        SpringLayout.Constraints canvasCons = workSpaceLayout.getConstraints(canvas);
        canvasCons.setX(Spring.constant(5));
        canvasCons.setY(Spring.constant(5));
        canvasCons.setConstraint(SpringLayout.SOUTH, Spring.constant(1029)); // 1029 = 1024 + 5
        canvasCons.setConstraint(SpringLayout.EAST, Spring.constant(1029));

        SpringLayout.Constraints controllerCons = workSpaceLayout.getConstraints(controller);
        controllerCons.setX(Spring.constant(5));
        controllerCons.setY(Spring.constant(canvasCons.get));

    }

    protected abstract void updateCanvasComponents();

}
