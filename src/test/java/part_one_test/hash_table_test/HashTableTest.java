package part_one_test.hash_table_test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import part_one.hash_table.HashTable;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {
    HashTable map;

    @BeforeEach
    void setUp() {
        map = new HashTable();
    }

    @Nested
    class Put{
        @Test
        void putOneItemInTheTable() {
            var r = map.put(0, "hi");

            assertTrue(r);
            assertEquals("hi", map.get(0));
            assertEquals(1, map.size());
        }

        @Test
        void putItemsMoreThanTheArraySize() {
            var key = new Random().nextInt(999999999);
            var value = RandomStringUtils.randomAlphabetic(10);
            map.put(key, value);

            for (int i = 0; i < 999; i++) {
                var r = map.put(new Random().nextInt(999999999),
                        RandomStringUtils.randomAlphabetic(10));
                assertTrue(r);
            }

            assertEquals(value, map.get(key));
            assertEquals(1000, map.size());
        }

        @Test
        void putKeyExisted_shouldUpdateValue() {
            var key = 0;
            var newValue = "hi!!";

            map.put(key, "hi");
            map.put(key, newValue);

            assertEquals(newValue, map.get(key));
            assertEquals(1, map.size());
        }
    }

    @Nested
    class Get{}

    @Nested
    class Remove{
        @Test
        void removeOneItem() {
            map.put(0, "A");
            map.put(1, "B");

            assertEquals("B", map.remove(1));
            assertEquals(1, map.size());
            assertThrows(
                    NoSuchElementException.class,
                    () -> map.get(1)
            );
        }
    }
}
