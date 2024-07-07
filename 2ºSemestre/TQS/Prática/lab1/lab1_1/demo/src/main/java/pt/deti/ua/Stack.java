package pt.deti.ua;

public interface Stack<T> {

    T pop();

    int size();

    T peek();

    void push(T elem);

    boolean isEmpty();

}
