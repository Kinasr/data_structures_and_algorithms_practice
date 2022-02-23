package part_one_test.stack_test;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import part_one.stack.MyStack;

import java.security.InvalidParameterException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {

    @Nested
    class PushTest {
        @Test
        void pushNumberToTheStack() {
            var stack = new MyStack<Integer>();
            stack.push(10);

            assertFalse(stack.isEmpty());
            assertEquals(1, stack.size());
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 5, 99, 100, 101, 199, 200, 201, 1000})
        void pushNumbersToStackUsingDefaultArraySize(int numOfItems) {
            var stack = new MyStack<Integer>();
            var rand = new Random();

            for (int i = 0; i < numOfItems; i++) {
                stack.push(rand.nextInt(1000));
            }

            assertFalse(stack.isEmpty());
            assertEquals(numOfItems, stack.size());
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 4, 5, 6, 9, 10, 11, 50, 99, 100, 101})
        void pushNumbersToStackUsingCustomArraySize(int numOfItems) {
            var stack = new MyStack<Integer>(5);
            var rand = new Random();

            for (int i = 0; i < numOfItems; i++) {
                stack.push(rand.nextInt(1000));
            }

            assertFalse(stack.isEmpty());
            assertEquals(numOfItems, stack.size());
        }

        @Test
        void pushNull() {
            var stack = new MyStack<Integer>();

            assertThrowsExactly(
                    InvalidParameterException.class,
                    () -> stack.push(null)
            );

        }
    }

    @Nested
    class PopTest {
        @Test
        void popFromOneItemStack() {
            var stack = new MyStack<Integer>();
            stack.push(10);

            assertEquals(10, stack.pop());
            assertNull(stack.pop());
        }

        @Test
        void popFromMultiItemStack() {
            var stack = new MyStack<Integer>();
            stack.push(10);
            stack.push(20);
            stack.push(30);

            assertEquals(30, stack.pop());
            assertEquals(20, stack.pop());
            assertEquals(10, stack.pop());
            assertNull(stack.pop());
        }

        @Test
        void popEmptyStack() {
            var stack = new MyStack<Integer>();

            assertNull(stack.pop());
        }
    }

    @Nested
    class PeakTest {
        @Test
        void peakFromOneItemStack() {
            var stack = new MyStack<Integer>();
            stack.push(10);

            assertEquals(10, stack.peak());
        }

        @Test
        void peakFromMultiItemStack() {
            var stack = new MyStack<Integer>();
            stack.push(10);
            stack.push(20);
            stack.push(30);

            assertEquals(30, stack.peak());
            assertEquals(30, stack.peak());
        }

        @Test
        void peakEmptyStack() {
            var stack = new MyStack<Integer>();

            assertNull(stack.peak());
        }
    }

    @Nested
    class IsEmptyTest {
        @Test
        void isEmptyOnEmptyStack() {
            var stack = new MyStack<Integer>();

            assertTrue(stack.isEmpty());
        }

        @Test
        void isEmptyOnNonEmptyStack() {
            var stack = new MyStack<Integer>();
            stack.push(10);

            assertFalse(stack.isEmpty());
        }

        @Test
        void isEmptyAfterPop() {
            var stack = new MyStack<Integer>();
            stack.push(10);
            stack.pop();

            assertTrue(stack.isEmpty());
        }
    }
}
