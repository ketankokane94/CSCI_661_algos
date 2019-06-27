
/**
 * @author Ketan Kokane
 * Basic implementation of Binary Search Tree
 * Methods:
 * add
 * Inorder traversal
 * Problems solution:
 * Lowest Common Ancestor
 * Transform A sorted Array into a Complete Binary Tree (Heap)
 * Validate BST
 * construct Tree with minimum height (Cant be done if array is not ordered)
 */



public class BinarySearchTree<E extends Comparable> {
    TreeNode rootNode;

    public void add(E element) {
        rootNode = add(element, rootNode);
    }

    public TreeNode add(E element, TreeNode node) {
        if (node == null) {
            node = new TreeNode(element);
            return node;
        }
        if (element.compareTo(node.value) <= 0) {
            node.left = add(element, node.left);
        } else {
            node.right = add(element, node.right);
        }
        return node;
    }

    public String inOrdertraversal() {
        return inOrderRec(rootNode);
    }

    public String inOrderRec(TreeNode node) {
        if (node == null)
            return "";
        String str = inOrderRec(node.left);
        str += node.value;
        str += inOrderRec(node.right);
        return str;
    }

    public int height()
    {
        return heightrec(rootNode);
    }
    public int heightrec(TreeNode node){
        if (node == null)
            return -1;
        return 1 + Math.max(heightrec(node.left),heightrec(node.right));
    }

    public TreeNode lowestCommonAncestor(E value1, E value2 ){
        E left, right;
        if (value1.compareTo(value2) < 0){
            left = value1;
            right = value2;
        }
        else {
            left = value2;
            right = value1;
        }
        return lcaRec(left, right, rootNode);
    }

    public TreeNode lcaRec(E left, E right, TreeNode node){
        if (node.value.compareTo(left) > 0 && node.value.compareTo(right) < 0){
            return node;
        }
        if (node.value.compareTo(left) > 0 && node.value.compareTo(right) > 0){
            return lcaRec(left,right,node.left);
        }
            return lcaRec(left,right,node.right);
    }

    public TreeNode transformAsHeap(E[] array){
        return heapify(array,0);
    }

    public TreeNode heapify(E[] array,int index){
        if (index >= array.length){
            return null;
        }
        TreeNode node = new TreeNode(array[index]);
        node.left = heapify(array, index * 2 + 1);
        node.right = heapify(array, index * 2 + 2);
        return node;
    }

    public boolean validateBST(TreeNode node){
            // if its a leaf node return true 
            if (node == null || (node.left == null &&  node.right == null)){
                return true;
            }
            // check if the left node has value greater than current node
            if (node.left != null && node.left.value.compareTo(node.value) > 0 ){
                return false;
            }
            // check if the right node has value smaller or ever equal to current node
            if (node.right != null && node.right.value.compareTo(node.value) <= 0 ){
                return false;
            }
            return validateBST(node.left) && validateBST(node.right);
            
    }

    public TreeNode constructMinHeightBT(E [] elements, int left, int right){
        
        if ( right < left)
            return null;    
        int middle = (right+left) >> 1;
        TreeNode root = new TreeNode(elements[middle]);
        root.left = constructMinHeightBT(elements, left, middle - 1);
        root.right = constructMinHeightBT(elements, middle + 1,right);
        return root;

    }


    public static void main(String args[]) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(4);
        bst.add(5);
        bst.add(6);
        bst.add(7);
        bst.add(8);
        System.out.println(bst.inOrdertraversal());
        // created A BST 
        System.out.println("height of BST with elements {1,2,3,4,5,6,7,8} = " + bst.height()); // print height of the BST 
        System.out.println("check if the BST is valid = " + bst.validateBST(bst.rootNode));
        BinarySearchTree bst3 = new BinarySearchTree(); 
        Integer [] arrays = {1,2,3,4,5,6,7,8};
        bst3.rootNode = bst.transformAsHeap(arrays);
        System.out.println("In order traversal after heapifying the tree = "+bst3.inOrdertraversal());
        System.out.println("check if the BST is valid after heapify = " + bst3.validateBST(bst3.rootNode));
        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.rootNode = bst.constructMinHeightBT(arrays, 0, arrays.length - 1);
        System.out.println("height of the BST {1,2,3,4,5,6,7,8} with min height routine " + bst2.height());
        
        

    }
}
