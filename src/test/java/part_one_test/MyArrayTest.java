package part_one_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import part_one.MyArray;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayTest {
    private MyArray<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new MyArray<>(3);
        numbers.insert(1);
        numbers.insert(2);
        numbers.insert(3);
    }

    @Nested
    class InsertTest {
        @Test @Tag("Smoke") @Tag("Regression")
        void insertNumberWithinTheFixedLength() {
            assertEquals(3, numbers.length());
            assertEquals(2, numbers.get(1));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void insertNumberAboveTheFixedLength() {
            numbers.insert(4);

            assertEquals(4, numbers.length());
            assertEquals(4, numbers.get(3));
        }

        @Test @Tag("Smoke") @Tag("Regression")
        void insertNumberAfterRemoveOne() {
            numbers.removeAt(0);
            numbers.insert(4);

            assertAll(
                    () -> assertEquals(3, numbers.length()),
                    () -> assertEquals(4, numbers.get(2))
            );
        }
    }

    @Nested
    class RemoveAtTest {
        @Test @Tag("Smoke") @Tag("Regression")
        void removeNumber() {
            numbers.removeAt(0);

            assertEquals(2, numbers.length());
            assertEquals(2, numbers.get(0));
        }

        @Test @Tag("Regression")
        void removeNumberOutOfArrayLength() {
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> numbers.removeAt(10)),
                    () -> assertEquals(3, numbers.length())
            );
        }

        @Test @Tag("Regression")
        void removeNumberWithWrongIndex() {
            assertThrows(IllegalArgumentException.class,
                    () -> numbers.removeAt(-1));
        }
    }

    @Nested
    class GetTest {

    }

    @Nested
    class GetIndexTest {
        @Test @Tag("Smoke") @Tag("Regression")
        void getIndexForExistNumber() {
            assertEquals(0, numbers.indexOf(1));
        }

        @Test @Tag("Regression")
        void getIndexForNotExistedNumber() {
            assertEquals(-1, numbers.indexOf(10));
        }
    }
}
