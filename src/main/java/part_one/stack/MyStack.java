package part_one.stack;

import java.security.InvalidParameterException;
import java.util.function.Consumer;

public class MyStack<T> {
    private Object[] items;
    private int size = 0;
    private int stackSize = 100;
    private Consumer<T> throwExceptionIfNull = t -> {
        if (t == null) throw new InvalidParameterException();
    };

    public MyStack() {
        items = new Object[stackSize];
    }

    public MyStack(int stackSize) {
        this.stackSize = stackSize;
        items = new Object[this.stackSize];
    }


    public void push(T item) {
        throwExceptionIfNull.accept(item);

        if (size >= stackSize) {
            stackSize *= 2;
            var newItems = new Object[stackSize];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
        items[size++] = item;
    }

    public T pop() {
        if (isEmpty()) return null;

        var item = items[size - 1];
        items[--size] = null;

        return (T) item;
    }

    public T peak() {
        if (isEmpty()) return null;

        return (T) items[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
