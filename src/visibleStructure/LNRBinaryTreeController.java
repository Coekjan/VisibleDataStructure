package visibleStructure;

/**
 * @author Yip Coekjan
 * @Date 11/21/2020
 */
public class LNRBinaryTreeController extends TraversalBinaryTreeController {

    @Override
    protected void refactorThread() {
        threads.clear();
        lnrTraversal(root);
    }

    private GeneralBinaryTreeNodeController lnrTraversal(GeneralBinaryTreeNodeController node) {
        if (node != null) {
            if (node.left != null) {
                GeneralBinaryTreeNodeController retLeft = lnrTraversal(node.left);
                threads.put(retLeft, node);
                if (node.right != null) {
                    GeneralBinaryTreeNodeController pointer = node.right;
                    while (pointer.left != null) pointer = pointer.left;
                    threads.put(node, pointer);
                    return lnrTraversal(node.right);
                } else return node;
            } else if (node.right != null) {
                GeneralBinaryTreeNodeController pointer = node.right;
                while (pointer.left != null) pointer = pointer.left;
                threads.put(node, pointer);
                return lnrTraversal(node.right);
            } else return node;
        } else return null;
    }
}
