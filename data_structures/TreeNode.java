/**
 * @author Ketan Kokane
 * @param <E>
 * TreeNode structure to be used by Tree Data structures, i.e Binary Tree, Binary Search Tree, N-ary Tree
 * 
 */
public class TreeNode<E extends  Comparable> {
    E value;
    TreeNode left;
    TreeNode right;

    public TreeNode(E value) {
        this.value = value;
    }
}
