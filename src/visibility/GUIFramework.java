package visibility;

import visibleStructure.StructureNodeController;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class GUIFramework extends JFrame {
    private final GUIFramework self = this;
    private static final Dimension FRAME_DIMENSION;

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
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(FRAME_DIMENSION);
        this.setLocationRelativeTo(null);
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
            JMenuItem fileLang = new JMenuItem(GUILangSupporter.MENU_TEXT_FILE_LANG.toString());
            JMenuItem fileExit = new JMenuItem(GUILangSupporter.MENU_TEXT_FILE_EXIT.toString());
            file.add(fileNewFile);
            file.add(fileLang);
            file.addSeparator();
            file.add(fileExit);
            fileNewFile.addActionListener(e -> new GUIFramework(self.structures, self.handlers));
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
                    for(int i = 0; i < LangString.languages.length; i++) {
                        if(LangString.languages[i].equals(input)) {
                            GUILangSupporter.currentLangIndex = i;
                            self.dispose();
                            new GUIFramework(structures, handlers);
                            break;
                        }
                    }
                }

            });
            JMenu about = new JMenu(GUILangSupporter.MENU_TEXT_ABOUT.toString());
            JMenuItem aboutSponsor = new JMenuItem(GUILangSupporter.MENU_TEXT_ABOUT_SPONSOR.toString());
            JMenuItem aboutFeedback = new JMenuItem(GUILangSupporter.MENU_TEXT_ABOUT_FEEDBACK.toString());
            JMenuItem aboutAuthor = new JMenuItem(GUILangSupporter.MENU_TEXT_ABOUT_AUTHOR.toString());
            about.add(aboutSponsor);
            about.add(aboutAuthor);
            about.addSeparator();
            about.add(aboutFeedback);
            aboutSponsor.addActionListener(e -> {

            });
            aboutFeedback.addActionListener(e -> {

            });
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
        private final HashMap<DefaultMutableTreeNode, Integer> selectedNodes = new HashMap<>();
        private final ArrayList<DrawablePane> drawablePanes = new ArrayList<>();
        private final ArrayList<JSplitPane> controllers = new ArrayList<>();
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
                    DrawablePane drawablePane;
                    JSplitPane controller;
                    if (selectedNodes.containsKey(selectedNode)) {
                        int index = selectedNodes.get(selectedNode);
                        drawablePane = drawablePanes.get(index);
                        controller = controllers.get(index);
                    } else {
                        int index = drawablePanes.size();
                        CanvasPairController canvasPairController = self.handlers.get(selectedNode.getUserObject()).construct();
                        controller = canvasPairController.getController();
                        drawablePane = canvasPairController.getCanvas();
                        controllers.add(controller);
                        drawablePanes.add(drawablePane);
                        selectedNodes.put(selectedNode, index);
                    }
                    controller.setDividerLocation(FRAME_DIMENSION.width * 3 / 5);
                    nowNode = selectedNode;
                    self.workSpace.removeAll();
                    self.workSpace.setTopComponent(drawablePane);
                    self.workSpace.setBottomComponent(controller);
                    self.workSpace.setDividerSize(2);
                    self.workSpace.updateUI();
                }
            });
        }
    }
}