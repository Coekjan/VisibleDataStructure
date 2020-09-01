package visibility;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class GlobalUserInterfaceFramework extends JFrame {
    private GlobalUserInterfaceFramework self = this;
    private static final Dimension FRAME_DIMENSION = new Dimension(1500, 1000);
    private boolean save = true;

    private final Hashtable<LangString, LangString[]> structures;
    private final HashMap<LangString, WorkSpacePairController> handlers;
    private final JSplitPane workSpace = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    public GlobalUserInterfaceFramework(Hashtable<LangString, LangString[]> structures,
                                        HashMap<LangString, WorkSpacePairController> handlers) {
        this.structures = new Hashtable<>(structures);
        this.handlers = new HashMap<>(handlers);

        JScrollPane sourceManager = new GUIFrameworkSourceManagerTree();
        JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        this.add(content);
        this.setTitle(GlobalUserInterfaceLangController.TITLE.toString());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(FRAME_DIMENSION);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {
                onExitListener();
            }
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
        this.setJMenuBar(new GUIFrameworkMenuBar());

        content.setLeftComponent(sourceManager);
        content.setRightComponent(workSpace);
        content.setDividerLocation(FRAME_DIMENSION.width >> 3);
        content.setDividerSize(2);

        workSpace.setDividerLocation((FRAME_DIMENSION.height >> 2) * 3);

        this.setVisible(true);
    }

    private class GUIFrameworkMenuBar extends JMenuBar {
        public GUIFrameworkMenuBar() {
            JMenu file = new JMenu(GlobalUserInterfaceLangController.MENU_TEXT_FILE.toString());
                JMenuItem fileNewFile = new JMenuItem(GlobalUserInterfaceLangController.MENU_TEXT_FILE_NEW.toString());
                JMenuItem fileOpenFile = new JMenuItem(GlobalUserInterfaceLangController.MENU_TEXT_FILE_OPEN.toString());
                JMenuItem fileSaveFile = new JMenuItem(GlobalUserInterfaceLangController.MENU_TEXT_FILE_SAVE.toString());
                JMenuItem fileLang = new JMenuItem(GlobalUserInterfaceLangController.MENU_TEXT_FILE_LANG.toString());
                JMenuItem fileExit = new JMenuItem(GlobalUserInterfaceLangController.MENU_TEXT_FILE_EXIT.toString());
                file.add(fileNewFile);
                file.add(fileOpenFile);
                file.add(fileSaveFile);
                file.add(fileLang);
                file.addSeparator();
                file.add(fileExit);
                fileNewFile.addActionListener(e -> new GlobalUserInterfaceFramework(self.structures, self.handlers));
                fileOpenFile.addActionListener(e -> {

                }); // TODO
                fileSaveFile.addActionListener(e -> {

                }); // TODO
                fileLang.addActionListener(e -> {
                    String input = (String) JOptionPane.showInputDialog(
                            self,
                            GlobalUserInterfaceLangController.MENU_TEXT_FILE_LANG.toString(),
                            GlobalUserInterfaceLangController.LANG_CHOOSE_TITLE.toString(),
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            LangString.languages,
                            LangString.languages[GlobalUserInterfaceLangController.currentLangIndex]
                    );
                    for(int i = 0; i < LangString.languages.length; i++) {
                        if(LangString.languages[i].equals(input)) {
                            GlobalUserInterfaceLangController.currentLangIndex = i;
                            self.dispose();
                            new GlobalUserInterfaceFramework(structures, handlers);
                            break;
                        }
                    }
                });
                fileExit.addActionListener(e -> onExitListener());
            JMenu about = new JMenu(GlobalUserInterfaceLangController.MENU_TEXT_ABOUT.toString());
                JMenuItem aboutSponsor = new JMenuItem(GlobalUserInterfaceLangController.MENU_TEXT_ABOUT_SPONSOR.toString());
                JMenuItem aboutFeedback = new JMenuItem(GlobalUserInterfaceLangController.MENU_TEXT_ABOUT_FEEDBACK.toString());
                JMenuItem aboutAuthor = new JMenuItem(GlobalUserInterfaceLangController.MENU_TEXT_ABOUT_AUTHOR.toString());
                about.add(aboutSponsor);
                about.add(aboutAuthor);
                about.addSeparator();
                about.add(aboutFeedback);
                aboutSponsor.addActionListener(e -> {

                }); // TODO
                aboutFeedback.addActionListener(e -> {

                }); // TODO
                aboutAuthor.addActionListener(e -> {
                    JOptionPane.showMessageDialog(
                            self,
                            GlobalUserInterfaceLangController.AUTHOR.toString(),
                            GlobalUserInterfaceLangController.MENU_TEXT_ABOUT_AUTHOR.toString(),
                            JOptionPane.PLAIN_MESSAGE
                    );
                });
            this.add(file);
            this.add(about);
        }
    }

    private class GUIFrameworkSourceManagerTree extends JScrollPane {
        private DefaultMutableTreeNode nowNode = null;
        private final JTree tree = new JTree(structures);
        public GUIFrameworkSourceManagerTree() {
            this.setViewportView(tree);
            tree.setRootVisible(false);
            tree.setEditable(false);
            tree.setShowsRootHandles(true);
            tree.addTreeSelectionListener(e -> {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if(selectedNode != null && selectedNode != nowNode
                        && selectedNode != tree.getModel().getRoot() && selectedNode.isLeaf()) {
                    if(save || JOptionPane.showConfirmDialog(
                            self,
                            GlobalUserInterfaceLangController.STRUCTURE_SWITCH_WARNING_MESSAGE.toString(),
                            GlobalUserInterfaceLangController.STRUCTURE_SWITCH_WARNING_TITLE.toString(),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE) != JOptionPane.NO_OPTION) {
                        nowNode = selectedNode;
                        self.workSpace.removeAll();
                        self.workSpace.setTopComponent(self.handlers.get(selectedNode.getUserObject()).getWorkSpace());
                        self.workSpace.setBottomComponent(self.handlers.get(selectedNode.getUserObject()).getController());
                        self.workSpace.updateUI();
                        self.save = false;
                    } else {
                        tree.setSelectionPath(new TreePath(nowNode.getPath()));
                    }
                }
            });
        }
    }

    private void onExitListener() {
        if(save || JOptionPane.showConfirmDialog(
                        this,
                        GlobalUserInterfaceLangController.EXIT_BUT_NOT_SAVE_MESSAGE.toString(),
                        GlobalUserInterfaceLangController.EXIT_BUT_NOT_SAVE_TITLE.toString(),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
        ) == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }
}
