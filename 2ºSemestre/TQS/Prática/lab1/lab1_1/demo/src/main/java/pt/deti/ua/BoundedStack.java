package pt.deti.ua;

public class BoundedStack<T> extends TqsStack<T> {
    
    private int maxSize;

    public BoundedStack(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    @Override
    public void push(T elem) {
        if(size() == this.maxSize)
            throw new IllegalStateException();
            
        super.push(elem);
    }

}
