package part_one.queue;

public class PriorityQueues {
    private int[] queue;
    private int count;
    private int front;
    private int rear;

    public PriorityQueues(int size) {
        queue = new int[size];
    }

    public void enqueue(int item) {
        if (isFull())
            throw new IllegalStateException();

        for (int i = count; i >= 0; i--) {
            if (item >= queue[i] || i == 0) {
                queue[i + 1] = item;
                break;
            } else
                queue[i + 1] = queue[i];
            count++;
        }
    }
    // 2, 3, 4
    //    2, 3, 4

    public int dequeue() {
        return -1;
    }

    public int peak() {
        return -1;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count >= queue.length;
    }
}
