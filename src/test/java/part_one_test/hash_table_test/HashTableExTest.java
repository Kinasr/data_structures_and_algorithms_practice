package part_one_test.hash_table_test;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import part_one.hash_table.HashTableEx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashTableExTest {
    @Nested
    class GetFirstRepeatedCharacter {
        @Test
        void hasOneRepeatedChar() {
            var actual = HashTableEx.getFirstRepeatedCharacter("abcdefc");
            assertEquals('c', actual);
        }

        @Test
        void hasTwoRepeatedChar() {
            var actual = HashTableEx.getFirstRepeatedCharacter("abcdefcd");
            assertEquals('c', actual);
        }

        @Test
        void hasNoRepeatedChars() {
            var actual = HashTableEx.getFirstRepeatedCharacter("abcdefg");
            assertEquals(Character.MIN_VALUE, actual);
        }

        @Test
        void sendingNull() {
            assertThrows(
                    IllegalStateException.class,
                    () -> HashTableEx.getFirstRepeatedCharacter(null)
            );
        }
    }
}
