import dataStructure.node.RectNode;
import dataStructure.view.LinearCanvas;

import java.awt.*;


/**
 * @author Yip Coekjan
 * @Date 8/29/2020
 */
public class Main {
    public static void main(String[] args) {
        LinearCanvas<Integer> cav = new LinearCanvas<>(50, 500, 500);
        cav.add(new RectNode<>(100, 30, 30, Color.RED));
        cav.add(new RectNode<>(50, 100, 100, Color.BLUE));
        cav.draw();
    }
}
