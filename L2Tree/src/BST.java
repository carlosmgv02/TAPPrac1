import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class BST<T >implements Comparator<Node<T>> {
    //private List<Node<T>> l = new LinkedList<>();
    public Node<T> root;

    public void insert(T n){
        boolean done=false;
        if(root==null){
            root=new Node<T>(n);
        }
        else{
            while(!done){
                Node<T>temp=root;

            }
        }


    }
    public boolean contains(Node n){
        return true;
    }


    @Override
    public int compare(Node<T> n1, Node<T> n2) {
        if(n1.compareTo(n2)==0){
            return 0;
        }
        else if(n1.compareTo(n2)==1){
            return 1;
        }
        else{
            return -1;
        }
    }


}
