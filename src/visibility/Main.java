package visibility;

import struct.DataStructure;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

/**
 * @author Yip Coekjan
 * @Date 8/31/2020
 */
public class Main {

    private static final String FRAME_TITLE = "数据结构可视化";
    private static final String EXIT_BUT_NOT_SAVE = "提示您文件未保存, 现在退出将使文件销毁!";
    private static final int FRAME_WIDTH = 1500;
    private static final int FRAME_HEIGHT = 1000;
    private static final JFrame frame = new JFrame(FRAME_TITLE);
    private static final HashMap<
            DefaultMutableTreeNode,
            HashMap<
                    DefaultMutableTreeNode,
                    VSBElement
                    >> structTypes = new HashMap<>();

    private static DataStructure structure = null;
    private static boolean save = true;

    public static void main(String[] args) {
        initStructTypes();
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setJMenuBar(getMenuBar());
        frame.add(getContent());

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(save) {
                    frame.dispose();
                } else if (
                        JOptionPane.showConfirmDialog(
                                frame,
                                EXIT_BUT_NOT_SAVE,
                                "确认退出吗?",
                                JOptionPane.YES_NO_OPTION
                        ) == JOptionPane.YES_OPTION
                ) {
                    frame.dispose();
                }
            }
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        frame.setVisible(true);
    }

    private static JMenuBar getMenuBar() {
        final String AUTHOR = "@Coekjan 叶焯仁";

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("文件");
        JMenu aboutMenu = new JMenu("关于");

        JMenuItem newFileMenuItem = new JMenuItem("新建");
        JMenuItem openFileMenuItem = new JMenuItem("打开");
        JMenuItem exitFileMenuItem = new JMenuItem("退出");

        JMenuItem fundAboutMenuItem = new JMenuItem("资助");
        JMenuItem feedBackAboutMenuItem = new JMenuItem("反馈");
        JMenuItem authorAboutMenuItem = new JMenuItem("开发人员");

        menuBar.add(fileMenu);
            fileMenu.add(newFileMenuItem);
            fileMenu.add(openFileMenuItem);
            fileMenu.addSeparator();
            fileMenu.add(exitFileMenuItem);
        menuBar.add(aboutMenu);
            aboutMenu.add(fundAboutMenuItem);
            aboutMenu.add(feedBackAboutMenuItem);
            aboutMenu.addSeparator();
            aboutMenu.add(authorAboutMenuItem);

        newFileMenuItem.addActionListener(e -> {

        });
        openFileMenuItem.addActionListener(e -> {

        });
        exitFileMenuItem.addActionListener(e -> {
            if(save) {
                frame.dispose();
            } else if (
                    JOptionPane.showConfirmDialog(
                        frame,
                        EXIT_BUT_NOT_SAVE,
                        "确认退出吗?",
                        JOptionPane.YES_NO_OPTION
                    ) == JOptionPane.YES_OPTION
            ) {
                    frame.dispose();
            }
        });

        fundAboutMenuItem.addActionListener(e -> {

        });
        feedBackAboutMenuItem.addActionListener(e -> {

        });
        authorAboutMenuItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    frame,
                    AUTHOR,
                    "关于开发人员",
                    JOptionPane.PLAIN_MESSAGE
            );
        });

        return menuBar;
    }

    private static JSplitPane getContent() {
        JSplitPane workAndControl = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane sourceAndWC = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JScrollPane work = new JScrollPane();
        JScrollPane control = new JScrollPane();
        JPanel source = new JPanel();

        workAndControl.setTopComponent(work);
        workAndControl.setBottomComponent(control);
        workAndControl.setDividerSize(3);
        workAndControl.setDividerLocation(3 * frame.getHeight() >> 2);
        sourceAndWC.setLeftComponent(source);
        sourceAndWC.setRightComponent(workAndControl);
        sourceAndWC.setDividerSize(3);
        sourceAndWC.setDividerLocation(frame.getWidth() >> 3);

        source.add(getStructureTree(work, control));

        return sourceAndWC;
    }

    private static JScrollPane getStructureTree(JScrollPane work, JScrollPane control) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("数据结构");
        JTree dTree = new JTree(rootNode);
        JScrollPane scrollPane = new JScrollPane(dTree);
        for(DefaultMutableTreeNode node : structTypes.keySet()) {
            HashMap<DefaultMutableTreeNode, VSBElement> hashMap = structTypes.get(node);
            rootNode.add(node);
            for(DefaultMutableTreeNode subNode : hashMap.keySet()) {
                node.add(subNode);
            }
        }
        dTree.setEditable(false);
        dTree.setShowsRootHandles(true);
        dTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)dTree.getLastSelectedPathComponent();
            if(node == null || !node.isLeaf()) return;
            else {
                VSBElement ele = structTypes.get((DefaultMutableTreeNode) node.getParent()).get(node);
                ele.display(work);
                control.removeAll();
                control.add(ele.getControllerPanel());
                structure = ele.getDataStruct();
                save = false;
            }
        });
        return scrollPane;
    }

    private static void initStructTypes() {
        
    }

}
