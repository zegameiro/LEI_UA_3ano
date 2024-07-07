package pt.deti.ua;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> implements Stack<T> {

    private LinkedList<T> collection;

    public TqsStack() {
        this.collection = new LinkedList<>();
    }

    @Override
    public T pop() {
        if(isEmpty())
            throw new NoSuchElementException();

        return collection.removeLast();
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public T peek() {
        if(isEmpty())
            throw new NoSuchElementException();
            
        return collection.getLast();
    }

    @Override
    public void push(T arg) {
        collection.add(arg);
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }
}
