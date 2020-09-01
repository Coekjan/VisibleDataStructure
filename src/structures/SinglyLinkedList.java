package structures;

import visibility.DrawablePane;
import visibility.WorkSpacePairController;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class SinglyLinkedList extends WorkSpacePairController {

    private static int count = 0;
    private SinglyLinkedNode head = null;
    private SinglyLinkedNode tail = null;

    public SinglyLinkedList() {

        Box addBox = new Box(BoxLayout.X_AXIS);
        JButton button = new JButton("从链表最后增加");
        addBox.add(button);

        button.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(
                    null,
                    "元素数据",
                    ""
            );
            if(tail == null) {
                append(new SinglyLinkedNode(input, new Point(
                        StructureNode.NODE_SIZE.width, StructureNode.NODE_SIZE.height)));
            } else append(new SinglyLinkedNode(input, new Point(
                    tail.pos.x + (StructureNode.NODE_SIZE.width << 1), tail.pos.y)));
            workSpace.repaint();
//            workSpace.updateUI();
        });

        controller.addTab("增加", addBox);
    }

    private static class SinglyLinkedNode extends StructureNode {

        private SinglyLinkedNode pre = null;
        private SinglyLinkedNode next = null;

        public SinglyLinkedNode(String data, Point pos) {
            super(data, pos);
        }

        @Override
        protected int initID() {
            return ++count;
        }

        @Override
        protected void onClickListener() {
            JOptionPane.showMessageDialog(
                    null,
                    "id = " + id + "\ndata = " + data,
                    "Node",
                    JOptionPane.PLAIN_MESSAGE
            );

        }

        @Override
        public Shape getConnection() {
            if(pre != null) return new QuadCurve2D.Double(
                    pre.pos.x + NODE_SIZE.width,
                    pre.pos.y,
                    (pre.pos.x + pos.x) / 2.0 + (NODE_SIZE.width >> 1) * 3,
                    Math.min(pre.pos.y, pos.y) - Math.abs(pre.pos.y - pos.y) * 2 - NODE_SIZE.height / 2,
                    pos.x + (NODE_SIZE.width >> 1),
                    pos.y
            );
            else return null;
        }
    }

    private void addNode(SinglyLinkedNode node) {
        this.workSpace.add(node.getConnection());
        this.workSpace.add(node.button);
    }

    public void append(SinglyLinkedNode node) {
        if(head == null) head = tail = node;
        else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
        addNode(node);
    }

}
