package utils;

public class NodeList<T> {
    private T data;

    private NodeList<T> next;

    public NodeList<T> getNext() {
        return next;
    }

    public void setNext(NodeList<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeList(T data) {
        this.data = data;
        this.next = null;
    }

}
