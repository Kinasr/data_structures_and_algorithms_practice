package part_one_test.queue_test;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import part_one.queue.QueueEx;

import java.security.InvalidParameterException;
import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueueExTest {
    @Nested
    class ReverseQueueTest {
        @Test
        void reverseOddQueue() {
            Queue<Integer> q = new ArrayDeque<>();
            q.add(10);
            q.add(20);
            q.add(30);

            var rQ = QueueEx.reverseQueue(q);

            assertEquals(30,rQ.remove());
            assertEquals(20,rQ.remove());
            assertEquals(10,rQ.remove());
        }

        @Test
        void reverseEvenQueue() {
            Queue<Integer> q = new ArrayDeque<>();
            q.add(10);
            q.add(20);
            q.add(30);
            q.add(40);

            var rQ = QueueEx.reverseQueue(q);

            assertEquals(40,rQ.remove());
            assertEquals(30,rQ.remove());
            assertEquals(20,rQ.remove());
            assertEquals(10,rQ.remove());
        }

        @Test
        void reverseEmptyQueue() {
            Queue<Integer> q = new ArrayDeque<>();

            assertThrows(
                    InvalidParameterException.class,
                    () -> QueueEx.reverseQueue(q)
            );
        }

        @Test
        void reverseNullQueue() {
            assertThrows(
                    InvalidParameterException.class,
                    () -> QueueEx.reverseQueue(null)
            );
        }
    }
}
