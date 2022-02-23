package part_one_test.stack_test;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import part_one.stack.StackEx;

import static org.junit.jupiter.api.Assertions.*;

public class StackExTest {

    @Nested
    class ReverseStringTest {
        @Test
        void reverseValidString() {
            var rString = StackEx.reverseString("abcderg");

            assertEquals("gredcba", rString);
        }

        @Test
        void reverseStringContainsNumbersAndSpecialCharacters() {
            var rString = StackEx.reverseString("abc123!@#");

            assertEquals("#@!321cba", rString);
        }

        @Test
        void reverseEmptyString() {
            var rString = StackEx.reverseString("");

            assertEquals("", rString);
        }

        @Test
        void reverseNullString() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> StackEx.reverseString(null)
            );
        }
    }

    @Nested
    class StringBalancedTest {
        @ParameterizedTest
        @ValueSource(strings = {"(hi)", "()hi", "hi()", "(h)i"})
        void balancedStringWithSingleBrackets(String text) {
            assertTrue(StackEx.isStringBalanced(text));
        }

        @ParameterizedTest
        @ValueSource(strings = {"<hi>", "<>hi", "hi<>", "<h>i"})
        void balancedStringWithSingleAngleBrackets(String text) {
            assertTrue(StackEx.isStringBalanced(text));
        }

        @ParameterizedTest
        @ValueSource(strings = {"[hi]", "[]hi", "hi[]", "[h]i"})
        void balancedStringWithSingleSquareBrackets(String text) {
            assertTrue(StackEx.isStringBalanced(text));
        }

        @ParameterizedTest
        @ValueSource(strings = {"[(sdfs)]<[sd]>", "[(<no>)]", "[]<>()", "(jkjlj(kajdkf<klfsj>kj[flk]fd)fsd)"})
        void balancedStringWithMultiBrackets(String text) {
            assertTrue(StackEx.isStringBalanced(text));
        }

        @ParameterizedTest
        @ValueSource(strings = {"(", ")", "<", ">", "[", "]", "(jjdkk>", "[fdfsd(dfs])", "[>", "<)>", "(()", ")("})
        void unbalancesString(String text) {
            assertFalse(StackEx.isStringBalanced(text));
        }
    }
}
