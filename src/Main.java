import visibleStructure.CommonBinaryTreeController;
import visibleStructure.DoublyLinkedListController;
import visibleStructure.SinglyLinkedListController;
import visibility.*;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author Yip Coekjan
 * @Date 9/1/2020
 */
public class Main {
    public static void main(String[] args) {
        Hashtable<LangString, LangString[]> structures = new Hashtable<>();
        LangString[] linkedList = {
                GUILangSupporter.LINKED_LINEAR_LIST_SINGLY_NOT_LOOP,
                GUILangSupporter.LINKED_LINEAR_LIST_DOUBLE_NOT_LOOP,
        };
        LangString[] binaryTree = {
                GUILangSupporter.COMMON_BINARY_TREE
        };
        structures.put(GUILangSupporter.LINKED_LINEAR_LIST, linkedList);
        structures.put(GUILangSupporter.BINARY_TREE, binaryTree);

        HashMap<LangString, CanvasPairControllerConstructor> handlers = new HashMap<>();

        handlers.put(linkedList[0], SinglyLinkedListController::new);
        handlers.put(linkedList[1], DoublyLinkedListController::new);
        handlers.put(binaryTree[0], CommonBinaryTreeController::new);

        new GUIFramework(structures, handlers);
    }
}
