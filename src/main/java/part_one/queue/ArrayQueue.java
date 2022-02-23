package part_one.queue;

public class ArrayQueue<T> {
    private final Object[] queue;
    private final int size;
    private int front;
    private int rear;

    public ArrayQueue (int size){
        this.size = size;
        queue = new Object[size];
    }

    public boolean enqueue(T item) {
        if (isFull()) return false;
        queue[rear++] = item;
        return true;
    }

    public T dequeue() {
        if (isEmpty()) return null;
        return (T) queue[front++];
    }

    public T peek() {
        if (isEmpty()) return null;
        return (T) queue[front];
    }

    public boolean isEmpty() {
        return rear <= front;
    }

    public boolean isFull() {
        return rear >= size;
    }
}
