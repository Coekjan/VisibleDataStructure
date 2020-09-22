package visibleStructure;

import visibility.GUILangSupporter;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yip Coekjan
 * @Date 9/22/2020
 */
public class DoublyLinkedListController extends GeneralLinkedListController {

    public DoublyLinkedListController() {
        super();
    }

    @Override
    public void append(String data) {

    }

    @Override
    public void ahead(String data) {

    }

    @Override
    public void insert(GeneralLinkedNodeController node, String data) {

    }

    @Override
    public void deleteHead() {

    }

    @Override
    public void deleteTail() {

    }

    @Override
    public void deleteNode(GeneralLinkedNodeController node) {

    }

    private class DoublyLinkedNodeController extends GeneralLinkedNodeController {

        public DoublyLinkedNodeController(String data) {
            super(data);
        }

        @Override
        public void link(GeneralLinkedNodeController node) {
            this.next = node;
            if(node == null) nextField.setText(GUILangSupporter.STRUCT_NODE_POINT_NULL.toString());
            else {
                nextField.setText(Integer.toString(next.id));
                node.prev = this;
            }
        }

        @Override
        protected Shape getShape() {
            return null;
        }

        @Override
        protected int defineID() {
            return ++count;
        }

        @Override
        protected JPanel linkInfoController() {
            return infoControllerPane;
        }

        @Override
        protected JSplitPane linkController() {
            return controller;
        }

    }

    @Override
    protected void updateComponents() {

    }
}
