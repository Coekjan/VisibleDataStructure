package visibleStructure;

/**
 * @author Yip Coekjan
 * @Date 11/21/2020
 */
public class LRNBinaryTreeController extends TraversalBinaryTreeController {
    @Override
    protected void refactorThread() {
        threads.clear();
        lrnTraversal(root);
    }

    private GeneralBinaryTreeNodeController lrnTraversal(GeneralBinaryTreeNodeController node) {
        if (node != null) {
            if (node.left != null) {
                GeneralBinaryTreeNodeController retLeft = lrnTraversal(node.left);
                if (node.right != null) {
                    GeneralBinaryTreeNodeController pointer = node.right;
                    while (pointer.left != null) pointer = pointer.left;
                    threads.put(retLeft, pointer);
                    GeneralBinaryTreeNodeController retRight = lrnTraversal(node.right);
                    threads.put(retRight, node);
                } else {
                    threads.put(retLeft, node);
                }
            } else if (node.right != null) {
                GeneralBinaryTreeNodeController retRight = lrnTraversal(node.right);
                threads.put(retRight, node);
            }
            return node;
        } else return null;
    }
}
