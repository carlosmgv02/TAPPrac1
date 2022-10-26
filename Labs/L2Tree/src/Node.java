public class Node<T>implements Comparable<Node<T>> {
    private T data;
    private Node<T>esquerra;
    private Node<T>dreta;



    //generate constructor
    public Node(T data) {
        this.data = data;
        this.esquerra = null;
        this.dreta = null;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getEsquerra() {
        return esquerra;
    }

    public void setEsquerra(Node<T> esquerra) {
        this.esquerra = esquerra;
    }

    public Node<T> getDreta() {
        return dreta;
    }

    public void setDreta(Node<T> dreta) {
        this.dreta = dreta;
    }


    @Override
    public int compareTo(Node<T> o) {
        return 0;
    }

}
