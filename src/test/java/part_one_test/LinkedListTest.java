package part_one_test;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import part_one.LinkedList;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Nested
    class AddItemTest {
        @Test @Tag("Smoke") @Tag("Regression")
        void addFirstElementInLinkedList() {
            var list = new LinkedList<String>();
            list.addFirst("hi");

            assertEquals(1, list.size());
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void addTwoElementsInFirstAtLinkedList() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);
            list.addFirst(20);

            assertEquals(2, list.size());
            assertEquals(0, list.indexOf(20));
            assertEquals(1, list.indexOf(10));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void addLastElementInLinkedList() {
            var list = new LinkedList<String>();
            list.addLast("hi");

            assertEquals(1, list.size());
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void addTwoElementsInLastAtLinkedList() {
            var list = new LinkedList<Integer>();
            list.addLast(10);
            list.addLast(20);

            assertEquals(2, list.size());
            assertEquals(1, list.indexOf(20));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void addItemAtFirstThenItemAtLast() {
            var list = new LinkedList<String>();
            list.addFirst("f");
            list.addLast("l");

            assertEquals(2, list.size());
            assertEquals(0, list.indexOf("f"));
            assertEquals(1, list.indexOf("l"));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void addItemAtLastThenItemAtFirst() {
            var list = new LinkedList<String>();
            list.addLast("l");
            list.addFirst("f");

            assertEquals(2, list.size());
            assertEquals(0, list.indexOf("f"));
            assertEquals(1, list.indexOf("l"));
        }
    }

    @Nested
    class GetIndexTest {
        @Test @Tag("Regression")
        void getIndexForNull() {
            assertEquals(-1, new LinkedList<String>().indexOf("hi"));
        }

        @Test @Tag("Regression")
        void getIndexForNotExistItem() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);
            list.addFirst(0);
            list.addLast(20);

            assertEquals(-1, list.indexOf(30));
        }
    }

    @Nested
    class DeleteItemTest {
        @Test @Tag("Smoke") @Tag("Regression")
        void deleteFirstItem() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);
            list.addLast(20);
            list.deleteFirst();

            assertEquals(1, list.size());
            assertFalse(list.contains(10));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void deleteFirstItemFromListWithOneItem() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);
            list.deleteFirst();

            assertEquals(0, list.size());
            assertFalse(list.contains(10));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void deleteLastItemFromListWithOneItem() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);
            list.deleteLast();

            assertEquals(0, list.size());
            assertFalse(list.contains(10));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void deleteLastItem() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);
            list.addFirst(20);
            list.addFirst(30);
            list.addFirst(40);
            list.addLast(50);
            list.deleteLast();

            assertEquals(4, list.size());
            assertFalse(list.contains(50));
        }

        @Test @Tag("Regression")
        void deleteNotExistFirstItem() {
            var list = new LinkedList<Integer>();

            assertThrows(NoSuchElementException.class, list::deleteFirst);
        }

        @Test @Tag("Regression")
        void deleteNotExistLastItem() {
            var list = new LinkedList<Integer>();

            assertThrows(NoSuchElementException.class, list::deleteLast);
        }
    }

    @Nested
    class ContainsTest {
        @Test @Tag("Smoke") @Tag("Regression")
        void containsAnExistedItem() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);

            assertTrue(list.contains(10));
        }

        @Test @Tag("Regression")
        void containsNotExistedItem() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);

            assertFalse(list.contains(20));
        }

        @Test @Tag("Regression")
        void containsInEmptyList() {
            var list = new LinkedList<Integer>();

            assertFalse(list.contains(20));
        }
    }

    @Nested
    class ReverseTest {
        @Test @Tag("Smoke") @Tag("Regression")
        void reverseTest() {
            var list = new LinkedList<Integer>();

            list.addLast(10);
            list.addLast(20);
            list.addLast(30);
            list.addLast(40);
            list.reverse();

            assertEquals(list.first(), 40);
            assertEquals(0, list.indexOf(40));
            assertEquals(1, list.indexOf(30));
            assertEquals(2, list.indexOf(20));
            assertEquals(3, list.indexOf(10));
            assertEquals(list.last(), 10);
        }
    }

    @Nested
    class GetTest{
        @Test @Tag("Smoke") @Tag("Regression")
        void getFirst() {
            var list = new LinkedList<String>();
            list.addFirst("f");
            list.addLast("l");

            assertEquals("f", list.first());
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void getLast() {
            var list = new LinkedList<String>();
            list.addFirst("f");
            list.addLast("l");

            assertEquals("l", list.last());
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void getFirstAndLastWithDeclareFirstOnly() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);

            assertEquals(10, list.first());
            assertEquals(10, list.last());
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void getFirstAndLastWithDeclareLastOnly() {
            var list = new LinkedList<Integer>();
            list.addLast(10);

            assertEquals(10, list.first());
            assertEquals(10, list.last());
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void getIndexZero() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);
            list.addFirst(20);
            list.addFirst(30);

            assertEquals(30, list.get(0));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void getLastIndex() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);
            list.addFirst(20);
            list.addFirst(30);

            assertEquals(10, list.get(2));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void getRandomIndex() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);
            list.addFirst(20);
            list.addFirst(30);
            list.addFirst(40);
            list.addFirst(50);
            list.addFirst(60);
            list.addFirst(70);
            list.addFirst(80);
            list.addFirst(90);

            var random = new Random().nextInt(list.size());

            assertEquals(random, list.indexOf(list.get(random)));
        }

        @Test @Tag("Regression")
        void getUsingInvalidIndex() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);
            list.addFirst(20);
            list.addFirst(30);

            assertNull(list.get(-1));
        }

        @Test @Tag("Regression")
        void getUsingOutOfBoundaryIndex() {
            var list = new LinkedList<Integer>();
            list.addFirst(10);
            list.addFirst(20);
            list.addFirst(30);

            assertNull(list.get(10));
        }
    }

    @Nested
    class GetKthFromTheEndTest{
        @Test @Tag("Smoke") @Tag("Regression")
        void getMiddleFromEnd() {
            var list = new LinkedList<Integer>();
            list.addLast(10);
            list.addLast(20);
            list.addLast(30);
            list.addLast(40);
            list.addLast(50);

            assertEquals(20, list.getKthFromTheEnd(4));
            assertEquals(30, list.getKthFromTheEnd(3));
            assertEquals(40, list.getKthFromTheEnd(2));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void getFirstFromEnd() {
            var list = new LinkedList<Integer>();
            list.addLast(10);
            list.addLast(20);
            list.addLast(30);

            assertEquals(10, list.getKthFromTheEnd(3));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void getLastFromEnd() {
            var list = new LinkedList<Integer>();
            list.addLast(10);
            list.addLast(20);
            list.addLast(30);

            assertEquals(30, list.getKthFromTheEnd(1));
        }

        @Test @Tag("Regression")
        void getInvalidFromEnd() {
            var list = new LinkedList<Integer>();
            list.addLast(10);
            list.addLast(20);
            list.addLast(30);

            assertNull(list.getKthFromTheEnd(-1));
        }

        @Test @Tag("Regression")
        void getOutOfBoundaryFromEnd() {
            var list = new LinkedList<Integer>();
            list.addLast(10);
            list.addLast(20);
            list.addLast(30);

            assertNull(list.getKthFromTheEnd(10));
        }

        @Test @Tag("Regression")
        void getFromEndInEmptyList() {
            var list = new LinkedList<Integer>();

            assertNull(list.getKthFromTheEnd(1));
        }
    }
}