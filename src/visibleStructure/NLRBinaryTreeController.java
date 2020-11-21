package visibleStructure;

/**
 * @author Yip Coekjan
 * @Date 11/21/2020
 */
public class NLRBinaryTreeController extends TraversalBinaryTreeController {

    @Override
    protected void refactorThread() {
        threads.clear();
        nlrTraversal(root);
    }

    private GeneralBinaryTreeNodeController nlrTraversal(GeneralBinaryTreeNodeController node) {
        if (node != null) {
            if (node.left != null) {
                threads.put(node, node.left);
                GeneralBinaryTreeNodeController retLeft = nlrTraversal(node.left);
                if (retLeft == null) return null;
                else {
                    if (node.right != null) {
                        threads.put(retLeft, node.right);
                        return nlrTraversal(node.right);
                    } else return retLeft;
                }
            } else if (node.right != null) {
                threads.put(node, node.right);
                return nlrTraversal(node.right);
            } else return node;
        } else return null;
    }
}
