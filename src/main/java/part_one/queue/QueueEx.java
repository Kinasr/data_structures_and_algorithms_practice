package part_one.queue;

import java.security.InvalidParameterException;
import java.util.Queue;
import java.util.Stack;

public class QueueEx {
    public static <E> Queue<E> reverseQueue(Queue<E> queue) {
        if (queue == null || queue.isEmpty())
            throw new InvalidParameterException();

        var stack = new Stack<E>();
        while (!queue.isEmpty())
            stack.push(queue.remove());

        while (!stack.empty())
            queue.add(stack.pop());

        return queue;
    }
}
