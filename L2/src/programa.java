public class programa {
    public static void main(String[] args) {
        BST arbre=new BST();
        arbre.root=new Node(5);
        System.out.println(arbre.compare(arbre.root,new Node(5)));
    }
}
