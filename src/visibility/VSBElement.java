package visibility;

import struct.DataStructure;

import javax.swing.*;

/**
 * @author Yip Coekjan
 * @Date 8/31/2020
 */
public interface VSBElement {

    JPanel getControllerPanel();

    DataStructure getDataStruct();

    void display(JComponent component);

}
