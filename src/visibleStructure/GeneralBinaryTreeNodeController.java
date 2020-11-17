package visibleStructure;

import visibility.GUILangSupporter;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @author Yip Coekjan
 * @Date 10/26/2020
 */
public abstract class GeneralBinaryTreeNodeController extends StructureNodeController {

    public enum Position {
        LEFT, RIGHT, NONE
    }

    protected int size = 1;
    protected int layer = 0;
    protected int height = 1;
    protected GeneralBinaryTreeNodeController parent = null;
    protected GeneralBinaryTreeNodeController left = null;
    protected GeneralBinaryTreeNodeController right = null;
    protected final GeneralBinaryTreeNodeController self = this;
    protected final JLabel idText = new JLabel(GUILangSupporter.STRUCT_NODE_ID_TEXT.toString());
    protected final JTextField idField = new JTextField();
    protected final JLabel dataText = new JLabel(GUILangSupporter.STRUCT_NODE_DATA_TEXT.toString());
    protected final JTextField dataField = new JTextField();
    protected final JLabel parentText = new JLabel(GUILangSupporter.LINKED_NODE_PARENT_TEXT.toString());
    protected final JTextField parentField = new JTextField();
    protected final JLabel leftText = new JLabel(GUILangSupporter.LINKED_NODE_LEFT_CHILD_TEXT.toString());
    protected final JTextField leftField = new JTextField();
    protected final JLabel rightText = new JLabel(GUILangSupporter.LINKED_NODE_RIGHT_CHILD_TEXT.toString());
    protected final JTextField rightField = new JTextField();

    public GeneralBinaryTreeNodeController(String data) {
        super(data);
        idField.setText(Integer.toString(id));
        parentField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
        leftField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
        rightField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
        idField.setEditable(false);
        parentField.setEditable(false);
        leftField.setEditable(false);
        rightField.setEditable(false);
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

    public void leftLinkWithUpdate(GeneralBinaryTreeNodeController node) {
        this.left = node;
        if (node == null) {
            leftField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
            updateSize(getRightSize() + 1);
            updateHeight((right != null ? right.height : 0) + 1);
        } else {
            leftField.setText(Integer.toString(node.id));
            node.parentField.setText(Integer.toString(id));
            node.parent = this;
            node.updateLayer(layer + 1);
            updateSize(node.size + getRightSize() + 1);
            updateHeight(Math.max(
                    left.height,
                    right != null ? right.height : 0
            ) + 1);
        }
    }

    public void leftLink(GeneralBinaryTreeNodeController node) {
        this.left = node;
        if (node == null) {
            leftField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
        } else {
            leftField.setText(Integer.toString(node.id));
            node.parentField.setText(Integer.toString(id));
            node.parent = this;
        }
    }

    public void rightLinkWithUpdate(GeneralBinaryTreeNodeController node) {
        this.right = node;
        if (node == null) {
            rightField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
            updateSize(getLeftSize() + 1);
            updateHeight((left != null ? left.height : 0) + 1);
        } else {
            rightField.setText(Integer.toString(node.id));
            node.parentField.setText(Integer.toString(id));
            node.parent = this;
            node.updateLayer(layer + 1);
            updateSize(node.size + getLeftSize() + 1);
            updateHeight(Math.max(
                    left != null ? left.height : 0,
                    right.height
            ) + 1);
        }
    }

    public void rightLink(GeneralBinaryTreeNodeController node) {
        this.right = node;
        if (node == null) {
            rightField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
        } else {
            rightField.setText(Integer.toString(node.id));
            node.parentField.setText(Integer.toString(id));
            node.parent = this;
        }
    }

    public void updateSize(int newSize) {
        size = newSize;
        if (parent != null) {
            if (parent.left == this) parent.updateSize(newSize + parent.getRightSize() + 1);
            else parent.updateSize(newSize + parent.getLeftSize() + 1);
        }
    }

    public void updateLayer(int newLayer) {
        layer = newLayer;
        if (left != null) left.updateLayer(newLayer + 1);
        if (right != null) right.updateLayer(newLayer + 1);
    }

    public void updateHeight(int newHeight) {
        this.height = newHeight;
    }

    protected void resetSize() {
        if (left == null && right == null) {
            size = 1;
        } else if (left == null) {
            right.resetSize();
            size = 2 + right.size;
        } else if (right == null) {
            left.resetSize();
            size = 2 + left.size;
        } else {
            right.resetSize();
            left.resetSize();
            size = 1 + left.size + right.size;
        }
    }

    protected void resetLayer(int newLayer) {
        layer = newLayer;
        if (left != null) left.resetLayer(layer + 1);
        if (right != null) right.resetLayer(layer + 1);
    }

    protected void resetHeight() {
        if (left == null && right == null) {
            height = 1;
        } else if (left == null) {
            right.resetHeight();
            height = 1 + right.height;
        } else if (right == null) {
            left.resetHeight();
            height = 1 + left.height;
        } else {
            right.resetHeight();
            left.resetHeight();
            height = 1 + Math.max(
                    right.height,
                    left.height
            );
        }
    }

    public int getSize() {
        return size;
    }

    public int getLeftSize() {
        if (left == null) return 1;
        return left.size;
    }

    public int getRightSize() {
        if (right == null) return 1;
        return right.size;
    }

    public GeneralBinaryTreeNodeController getChildByPosition(Position position) {
        return position == Position.LEFT ? left : right;
    }

    @Override
    protected void onClickListener(JPanel infoController, JSplitPane controller) {
        dataField.setText(data);
        infoController.removeAll();
        infoController.add(idText);
        infoController.add(idField);
        infoController.add(parentText);
        infoController.add(parentField);
        infoController.add(leftText);
        infoController.add(leftField);
        infoController.add(rightText);
        infoController.add(rightField);
        infoController.add(dataText);
        infoController.add(dataField);
        controller.updateUI();
    }
}
