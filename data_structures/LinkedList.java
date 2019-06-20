/**
 * @author Ketan Kokane
 * @param <E>
 * A generic LinkedList implementation
 * With solution to general questions on linked List
 * 1. Insertion in LinkedList, at end and at node after given index
 * 2. Deletion in LinkedList, by value, and only when the reference to the node to be deleted 
 *    is known (without knowing head)
 * 3. sum two linkedlist (numbers are already reversed)
 * 4. Partition a linked List based on a given value 
 */

public class LinkedList <E extends Comparable>{
    public static void main(String[] args) {
        LinkedList<Integer> testLL = new LinkedList<>() ;
        testLL.insert(3);
        testLL.insert(5);
        testLL.insert(8);
        testLL.insert(5);
        testLL.insert(10);
        testLL.insert(2);
        testLL.insert(1);
        System.out.println(testLL);
       // System.out.println(testLL.addLinkedList(testLL.gethead(),testLL.gethead()));
        System.out.println(testLL.partition(testLL,5));
    }

    private Node head;

    public Node gethead(){
        return head;
    }

    public void insert(E value){

        if(head == null){
            head = new Node(value);
            return;
        }

        Node cursor = head;
        while (cursor.next!=null){
            cursor = cursor.next;
        }
        cursor.next = new Node(value);
        return;
    }

    public void insertAt(E value, int index){
        Node cursor = head;
        int n =0;
        while (cursor!= null){
            n+=1;
            cursor = cursor.next;
        }

        if(index < 0 || index > n)
        return;
        cursor = head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        Node newNode = new Node(value);
        newNode.next = cursor.next;
        cursor.next = newNode;
    }

    public void delete(E value){
        if (head != null && head.value.compareTo(value)==0){
            head = head.next;
            return;
        }
        Node cursor = head.next;
        Node runner = head;
        while (cursor!=null ){
            if (cursor.value.compareTo(value) == 0 ){
                runner.next = cursor.next;
                return;
            }
            cursor = cursor.next;
            runner = runner.next;
        }
    }

    @Override
    public String toString() {
        Node cursor = head;
        StringBuilder sb = new StringBuilder();
        while (cursor!=null){
            sb.append(cursor.value + " ");
            cursor = cursor.next;
        }
        return sb.toString();
    }

    public LinkedList addLinkedList(Node first, Node second){
        LinkedList result = new LinkedList();
        int sum =0, carry=0;

        while (first != null && second != null){
            sum = (Integer)first.value + (Integer)second.value + carry;
            result.insert(sum % 10);
            carry = sum / 10;
            first = first.next;
            second = second.next;
        }
        while (first!=null){
            sum = (Integer)first.value + carry;
            result.insert(sum % 10);
            carry = sum / 10;
            first = first.next;
        }
        while (second!=null){
            sum =  (Integer)second.value + carry;
            result.insert(sum % 10);
            carry = sum / 10;
            second = second.next;
        }
        if (carry != 0){
            result.insert(carry);
        }
        return result;

    }

    public LinkedList partition(LinkedList input, int partition) {
        Node left = input.head;
        Node right = left.next;

        while (left.next != null && right != null) {

            if (left.value.compareTo(partition) >= 0) {
                while (right != null) {
                    if (right.value.compareTo(partition) < 0) {
                        // swap
                        E value = (E) left.value;
                        left.value = right.value;
                        right.value = value;
                        break;
                    }
                    right = right.next;
                }
            }
            left = left.next;
            right = right.next;
        }
        return input;
    }
}


class Node<E extends Comparable>{
        E value;
        Node next;

    public Node(E value) {
        this.value = value;
    }
}
