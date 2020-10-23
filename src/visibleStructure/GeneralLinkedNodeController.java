package visibleStructure;

import visibility.GUILangSupporter;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @author Yip Coekjan
 * @Date 9/22/2020
 */
public abstract class GeneralLinkedNodeController extends StructureNodeController {

    protected GeneralLinkedNodeController next = null;
    protected GeneralLinkedNodeController prev = null;
    protected final GeneralLinkedNodeController self = this;
    protected final JLabel idText = new JLabel(GUILangSupporter.STRUCT_NODE_ID_TEXT.toString());
    protected final JTextField idField = new JTextField();
    protected final JLabel dataText = new JLabel(GUILangSupporter.STRUCT_NODE_DATA_TEXT.toString());
    protected final JTextField dataField = new JTextField();
    protected final JLabel nextText = new JLabel(GUILangSupporter.LINKED_NODE_NEXT_TEXT.toString());
    protected final JTextField nextField = new JTextField();

    public GeneralLinkedNodeController(String data) {
        super(data);
        idField.setText(Integer.toString(id));
        nextField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
        idField.setEditable(false);
        nextField.setEditable(false);
        dataField.setEditable(true);
        dataField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                dataField.selectAll();
            }
            @Override
            public void focusLost(FocusEvent e) {
                self.data = dataField.getText();
            }
        });
    }

    public abstract void link(GeneralLinkedNodeController node);

    @Override
    protected void onClickListener(JPanel infoController, JSplitPane controller) {
        dataField.setText(data);
        infoController.removeAll();
        infoController.add(idText);
        infoController.add(idField);
        infoController.add(nextText);
        infoController.add(nextField);
        infoController.add(dataText);
        infoController.add(dataField);
        controller.updateUI();
    }
}
