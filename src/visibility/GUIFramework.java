package visibility;

import visibleStructure.StructureNodeController;

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
public class GUIFramework extends JFrame {
    private final GUIFramework self = this;
    private static final Dimension FRAME_DIMENSION;
    private boolean save = true;

    static {
        FRAME_DIMENSION = new Dimension(
                StructureNodeController.SIZE.width * 25,
                StructureNodeController.SIZE.width * 15
        );
    }

    private final Hashtable<LangString, LangString[]> structures;
    private final HashMap<LangString, CanvasPairControllerConstructor> handlers;
    private final JSplitPane workSpace = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    public GUIFramework(Hashtable<LangString, LangString[]> structures,
                        HashMap<LangString, CanvasPairControllerConstructor> handlers) {
        this.structures = new Hashtable<>(structures);
        this.handlers = new HashMap<>(handlers);

        JScrollPane sourceManager = new GUIFrameworkSourceManagerTree();
        JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        this.add(content);
        this.setTitle(GUILangSupporter.TITLE.toString());
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
        content.setEnabled(false);
        content.setDividerLocation(FRAME_DIMENSION.width / 7);
        content.setDividerSize(2);

        workSpace.setDividerLocation(FRAME_DIMENSION.height * 3 / 4);
        this.setResizable(false);
        this.setVisible(true);
    }

    private class GUIFrameworkMenuBar extends JMenuBar {
        public GUIFrameworkMenuBar() {
            JMenu file = new JMenu(GUILangSupporter.MENU_TEXT_FILE.toString());
            JMenuItem fileNewFile = new JMenuItem(GUILangSupporter.MENU_TEXT_FILE_NEW.toString());
            JMenuItem fileOpenFile = new JMenuItem(GUILangSupporter.MENU_TEXT_FILE_OPEN.toString());
            JMenuItem fileSaveFile = new JMenuItem(GUILangSupporter.MENU_TEXT_FILE_SAVE.toString());
            JMenuItem fileLang = new JMenuItem(GUILangSupporter.MENU_TEXT_FILE_LANG.toString());
            JMenuItem fileExit = new JMenuItem(GUILangSupporter.MENU_TEXT_FILE_EXIT.toString());
            file.add(fileNewFile);
            file.add(fileOpenFile);
            file.add(fileSaveFile);
            file.add(fileLang);
            file.addSeparator();
            file.add(fileExit);
            fileNewFile.addActionListener(e -> new GUIFramework(self.structures, self.handlers));
            fileOpenFile.addActionListener(e -> {

            }); // TODO
            fileSaveFile.addActionListener(e -> {

            }); // TODO
            fileLang.addActionListener(e -> {
                String input = (String) JOptionPane.showInputDialog(
                        self,
                        GUILangSupporter.MENU_TEXT_FILE_LANG.toString(),
                        GUILangSupporter.LANG_CHOOSE_TITLE.toString(),
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        LangString.languages,
                        LangString.languages[GUILangSupporter.currentLangIndex]
                );
                if(!input.equals(LangString.languages[GUILangSupporter.currentLangIndex])) {
                    if(save || JOptionPane.showConfirmDialog(
                            self,
                            GUILangSupporter.LANG_CHANGE_BUT_NOT_SAVE.toString(),
                            GUILangSupporter.NOT_SAVE_TITLE.toString(),
                            JOptionPane.YES_NO_OPTION
                    ) == JOptionPane.YES_OPTION) {
                        for(int i = 0; i < LangString.languages.length; i++) {
                            if(LangString.languages[i].equals(input)) {
                                GUILangSupporter.currentLangIndex = i;
                                self.dispose();
                                new GUIFramework(structures, handlers);
                                break;
                            }
                        }
                    }
                }

            });
            fileExit.addActionListener(e -> onExitListener());
            JMenu about = new JMenu(GUILangSupporter.MENU_TEXT_ABOUT.toString());
            JMenuItem aboutSponsor = new JMenuItem(GUILangSupporter.MENU_TEXT_ABOUT_SPONSOR.toString());
            JMenuItem aboutFeedback = new JMenuItem(GUILangSupporter.MENU_TEXT_ABOUT_FEEDBACK.toString());
            JMenuItem aboutAuthor = new JMenuItem(GUILangSupporter.MENU_TEXT_ABOUT_AUTHOR.toString());
            about.add(aboutSponsor);
            about.add(aboutAuthor);
            about.addSeparator();
            about.add(aboutFeedback);
            aboutSponsor.addActionListener(e -> {

            }); // TODO
            aboutFeedback.addActionListener(e -> {

            }); // TODO
            aboutAuthor.addActionListener(e -> JOptionPane.showMessageDialog(
                    self,
                    GUILangSupporter.AUTHOR.toString(),
                    GUILangSupporter.MENU_TEXT_ABOUT_AUTHOR.toString(),
                    JOptionPane.PLAIN_MESSAGE
            ));
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
                            GUILangSupporter.STRUCTURE_SWITCH_WARNING_MESSAGE.toString(),
                            GUILangSupporter.WARNING.toString(),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE) != JOptionPane.NO_OPTION) {
                        nowNode = selectedNode;
                        CanvasPairController canvasPairController = self.handlers.get(selectedNode.getUserObject()).construct();
                        JSplitPane controller = canvasPairController.getController();
                        controller.setDividerLocation(FRAME_DIMENSION.width * 3 / 5);
                        self.workSpace.removeAll();
                        self.workSpace.setTopComponent(canvasPairController.getCanvas());
                        self.workSpace.setBottomComponent(controller);
                        self.workSpace.setDividerSize(2);
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
                GUILangSupporter.EXIT_BUT_NOT_SAVE_MESSAGE.toString(),
                GUILangSupporter.NOT_SAVE_TITLE.toString(),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        ) == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }
}