public class programa {
    public static void main(String[] args) {
        BST<Integer> arbre=new BST<>();
        arbre.root=new Node(5);
        Node<Integer> temp=new Node<>(5);
        System.out.println(arbre.compare(arbre.root,temp));
    }
}
