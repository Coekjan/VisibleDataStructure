package visibility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Yip Coekjan
 * @Date 9/3/2020
 */
public interface ButtonPairShapeConstructor {
    JButton button(int x, int y);
    ArrayList<Shape> shapes();
}
