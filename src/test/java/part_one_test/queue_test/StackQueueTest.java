package part_one_test.queue_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import part_one.queue.StackQueue;

import static org.junit.jupiter.api.Assertions.*;

public class StackQueueTest {
    @Nested
    class EnqueueTest {
        @Test
        void addItemsOnTheQueue() {
            var q = new StackQueue<Integer>(10);

            assertTrue(q.enqueue(10));
            assertTrue(q.enqueue(20));
            assertTrue(q.enqueue(30));
        }

        @Test
        void addOutOfQueueSize() {
            var q = new StackQueue<Integer>(2);

            q.enqueue(10);
            q.enqueue(20);

            assertFalse(q.enqueue(30));
        }
    }

    @Nested
    class DequeueTest {
        StackQueue<Integer> q;

        @BeforeEach
        void setUp() {
            q = new StackQueue<>(2);
        }

        @Test
        void getItemFromNotEmptyQueue() {
            q.enqueue(10);
            q.enqueue(20);

            assertEquals(10, q.dequeue());
        }

        @Test
        void getItemFromEmptyQueue() {
            assertNull(q.dequeue());
        }

        @Test
        void getItemOutOfQueueSize() {
            q.enqueue(10);

            assertEquals(10, q.dequeue());
            assertNull(q.dequeue());
            assertNull(q.dequeue());
        }
    }

    @Nested
    class PeekTest {
        StackQueue<Integer> q;

        @BeforeEach
        void setUp() {
            q = new StackQueue<>(2);
        }

        @Test
        void getItemFromNotEmptyQueue() {
            q.enqueue(10);
            q.enqueue(20);

            assertEquals(10, q.peek());
        }

        @Test
        void getItemFromEmptyQueue() {
            assertNull(q.peek());
        }

        @Test
        void getItemOutOfQueueSize() {
            q.enqueue(10);

            assertEquals(10, q.dequeue());
            assertNull(q.dequeue());
            assertNull(q.peek());
        }
    }

    @Nested
    class IsEmptyTest {
        StackQueue<Integer> q;

        @BeforeEach
        void setUp() {
            q = new StackQueue<>(2);
        }

        @Test
        void checkForEmptyQueue() {
            assertTrue(q.isEmpty());
        }

        @Test
        void checkForNotEmptyQueue() {
            q.enqueue(10);

            assertFalse(q.isEmpty());
        }

        @Test
        void checkForAnQueueBecomeEmpty() {
            q.enqueue(10);
            q.dequeue();

            assertTrue(q.isEmpty());
        }
    }

    @Nested
    class IsFullTest {
        StackQueue<Integer> q;

        @BeforeEach
        void setUp() {
            q = new StackQueue<>(2);
        }

        @Test
        void checkForAFullQueue() {
            q.enqueue(10);
            q.enqueue(20);

            assertTrue(q.isFull());
        }

        @Test
        void checkForEmptyQueue() {
            assertFalse(q.isFull());
        }

        @Test
        void checkForNotFullQueue() {
            assertFalse(q.isFull());
        }

        @Test
        void checkForAnQueueWasFullAndBecomeNotFull() {
            q.enqueue(10);
            q.enqueue(20);
            q.dequeue();

            assertFalse(q.isFull());
        }
    }
}
