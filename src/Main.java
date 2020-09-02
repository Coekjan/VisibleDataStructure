import structures.SinglyLinkedList;
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
                GlobalUserInterfaceLangController.LINKED_LINEAR_LIST_SINGLY_NOT_LOOP
        };
        structures.put(GlobalUserInterfaceLangController.LINKED_LINEAR_LIST, linkedList);

        HashMap<LangString, WorkSpacePairControllerConstructor> handlers = new HashMap<>();

        handlers.put(linkedList[0], new WorkSpacePairControllerConstructor() {
            @Override
            public WorkSpaceConfig construct() {
                return new SinglyLinkedList();
            }
        });

        new GlobalUserInterfaceFramework(structures, handlers);
    }
}
