/**
 * @author
 * The code to create a multi dimensional linked list is taken from geeks for geeks 
 * problem solved:
 * flatten a given 2d linked list (recursive solution)
 */
// Java program for flattening a Linked List
public class MultiDimensionalLinkedList {

    Node head; // head of list

    /* Linked list Node*/
    class Node {
        int data;
        Node right, down;

        Node(int data) {
            this.data = data;
            right = null;
            down = null;
        }
    }

    Node push(Node head_ref, int data) {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(data);

        /* 3. Make next of new Node as head */
        new_node.down = head_ref;

        /* 4. Move the head to point to new Node */
        head_ref = new_node;

        /*5. return to link it back */
        return head_ref;
    }

    void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.right;
        }
        System.out.println();
    }

    /* Drier program to test above functions */
    public static void main(String args[]) {
        LL L = new LL();

		/* Let us create the following linked list
			5 -> 10 -> 19 -> 20
			|    |	   |	 |
			V    V	   V	 V
			7   20    22    35
			|		   |	 |
			V		   V	 V
			8		  50     40
			|			     |
			V			     V
			30			     45
		*/

        L.head = L.push(L.head, 30);
        L.head = L.push(L.head, 8);
        L.head = L.push(L.head, 7);
        L.head = L.push(L.head, 5);

        L.head.right = L.push(L.head.right, 20);
        L.head.right = L.push(L.head.right, 10);

        L.head.right.right = L.push(L.head.right.right, 50);
        L.head.right.right = L.push(L.head.right.right, 22);
        L.head.right.right = L.push(L.head.right.right, 19);

        L.head.right.right.right = L.push(L.head.right.right.right, 45);
        L.head.right.right.right = L.push(L.head.right.right.right, 40);
        L.head.right.right.right = L.push(L.head.right.right.right, 35);
        L.head.right.right.right = L.push(L.head.right.right.right, 20);

        // flatten the list
        L.head = L.flatten(L.head);

        L.printList();
    }

    private Node flatten(Node node) {

        if (node.right != null) {
            node.right = flatten(node.right);
        }
        if (node.down != null) {
            node.down = flatten(node.down);
        }

        Node root = new Node(node.data);
        Node cursor = root;
        Node right = node.right;
        Node down = node.down;
        while (right != null && down != null) {
            if (right.data <= down.data) {
                cursor.right = new Node(right.data);
                right = right.right;
            } else {
                cursor.right = new Node(down.data);
                down = down.right;
            }
            cursor = cursor.right;
        }

        if (down != null) {
            cursor.right = down;
        }
        if (right != null) {
            cursor.right = right;
        }
        return root;

    }
} 
