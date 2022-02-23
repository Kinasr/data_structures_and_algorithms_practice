package part_one.queue;

import java.util.Stack;

public class StackQueue<T> {
    private Stack<T> queue;
    private int size;

    public StackQueue(int size) {
        this.size = size;
        queue = new Stack<>();
    }

    public StackQueue() {
        queue = new Stack<>();
    }

    public boolean enqueue(T item) {
        if (isFull()) return false;

        queue.push(item);
        return true;
    }

    public T dequeue() {
        if (isEmpty()) return null;

        return queue.remove(0);
    }

    public T peek() {
        if (isEmpty()) return null;

        return queue.get(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return size != 0 && queue.size() >= size;
    }
}
