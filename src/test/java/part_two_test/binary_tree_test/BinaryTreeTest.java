package part_two_test.binary_tree_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import part_two.binary_tree.BinaryTree;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static part_two.binary_tree.BinaryTree.TreeOrder.*;

public class BinaryTreeTest {
    private BinaryTree tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree();
    }

    @Nested
    class AddItemTest {
        @Test
        void addOneItem() {
            tree.insert(10);

            assertTrue(tree.find(10));
        }

        @Test
        void insertARightChildItems() {
            tree.insert(10);
            tree.insert(20);

            assertTrue(tree.find(10));
            assertTrue(tree.find(20));
        }

        @Test
        void insertALeftChildItems() {
            tree.insert(10);
            tree.insert(5);

            assertTrue(tree.find(10));
            assertTrue(tree.find(5));
        }

        @Test
        void insertNestedItems() {
            tree.insert(10);
            tree.insert(100);
            tree.insert(1000);
            tree.insert(0);
            tree.insert(1);
            tree.insert(12);
            tree.insert(2);
            tree.insert(8);
            tree.insert(7);
            tree.insert(9);
            tree.insert(902);
            tree.insert(94);

            assertTrue(tree.find(0));
            assertTrue(tree.find(12));
            assertTrue(tree.find(2));
            assertFalse(tree.find(500));
        }
    }

    @Nested
    class FindItemTest {
        @Test
        void findExistedItem() {
            tree.insert(10);

            assertTrue(tree.find(10));
        }

        @Test
        void findNotExistedItem() {
            assertFalse(tree.find(10));
        }
    }

    @Nested
    class OrderTreeTest {
        @BeforeEach
        void supSetup() {
            tree.insert(20);
            tree.insert(10);
            tree.insert(30);
            tree.insert(6);
            tree.insert(14);
            tree.insert(24);
            tree.insert(3);
            tree.insert(8);
            tree.insert(26);
        }

        @Test
        void orderTreeByBreadthFirst() {
            var list = tree.orderTreeBy(BREADTH_FIRST);

            assertEquals(20, list.get(0));
            assertEquals(10, list.get(1));
            assertEquals(30, list.get(2));
            assertEquals(6, list.get(3));
            assertEquals(14, list.get(4));
            assertEquals(24, list.get(5));
            assertEquals(3, list.get(6));
            assertEquals(8, list.get(7));
            assertEquals(26, list.get(8));
        }

        @Test
        void orderTreeByPreOrder() {
            var list = tree.orderTreeBy(PRE_ORDER);

            assertEquals(20, list.get(0));
            assertEquals(10, list.get(1));
            assertEquals(6, list.get(2));
            assertEquals(3, list.get(3));
            assertEquals(8, list.get(4));
            assertEquals(14, list.get(5));
            assertEquals(30, list.get(6));
            assertEquals(24, list.get(7));
            assertEquals(26, list.get(8));
        }

        @Test
        void orderTreeByInOrder() {
            var list = tree.orderTreeBy(IN_ORDER);

            assertEquals(3, list.get(0));
            assertEquals(6, list.get(1));
            assertEquals(8, list.get(2));
            assertEquals(10, list.get(3));
            assertEquals(14, list.get(4));
            assertEquals(20, list.get(5));
            assertEquals(24, list.get(6));
            assertEquals(26, list.get(7));
            assertEquals(30, list.get(8));
        }

        @Test
        void orderTreeByPostOrder() {
            var list = tree.orderTreeBy(POST_ORDER);

            assertEquals(3, list.get(0));
            assertEquals(8, list.get(1));
            assertEquals(6, list.get(2));
            assertEquals(14, list.get(3));
            assertEquals(10, list.get(4));
            assertEquals(26, list.get(5));
            assertEquals(24, list.get(6));
            assertEquals(30, list.get(7));
            assertEquals(20, list.get(8));
        }
    }

    @Nested
    class TreeHeightTest {
        @Test
        void treeHasSameHeightOnBothLeftAndRight() {
            tree.insert(20);
            tree.insert(10);
            tree.insert(30);
            tree.insert(6);
            tree.insert(14);
            tree.insert(24);
            tree.insert(3);
            tree.insert(8);
            tree.insert(26);

            assertEquals(3, tree.height());
        }

        @Test
        void treeHasLeftHigherThanRight() {
            tree.insert(20);
            tree.insert(10);
            tree.insert(30);
            tree.insert(6);
            tree.insert(14);
            tree.insert(24);
            tree.insert(3);
            tree.insert(8);

            assertEquals(3, tree.height());
        }

        @Test
        void treeHasRightHigherThanLeft() {
            tree.insert(10);
            tree.insert(5);
            tree.insert(20);
            tree.insert(9);
            tree.insert(30);

            assertEquals(2, tree.height());
        }

        @Test
        void heightOfEmptyTree() {
            assertEquals(-1, tree.height());
        }
    }

    @Nested
    class ItemDepthTest {
        @Test
        void existedItem() {
            tree.insert(20);
            tree.insert(10);
            tree.insert(30);
            tree.insert(6);
            tree.insert(14);
            tree.insert(24);
            tree.insert(3);
            tree.insert(8);
            tree.insert(26);

            assertEquals(0, tree.valueDepth(20));
            assertEquals(1, tree.valueDepth(10));
            assertEquals(1, tree.valueDepth(30));
            assertEquals(3, tree.valueDepth(3));
            assertEquals(2, tree.valueDepth(24));
        }

        @Test
        void notExistedItem() {
            assertThrows(NoSuchElementException.class, () -> tree.valueDepth(50));
        }
    }

    @Nested
    class MinValueTest {
        @Test
        void notEmptyTree() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);
            tree.insert(3);

            assertEquals(3, tree.minValue());
        }

        @Test
        void emptyTree() {
            assertThrows(IllegalStateException.class,
                    () -> tree.minValue());
        }
    }

    @Nested
    class IsEqualTest {
        @Test
        void equalTrees() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);

            var tree2 = new BinaryTree();
            tree2.insert(10);
            tree2.insert(20);
            tree2.insert(30);

            assertTrue(tree.isEqual(tree2));
        }

        @Test
        void notEqualTrees() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);

            var tree2 = new BinaryTree();
            tree2.insert(10);
            tree2.insert(20);
            tree2.insert(33);

            assertFalse(tree.isEqual(tree2));
        }

        @Test
        void nullTree() {
            assertFalse(tree.isEqual(null));
        }
    }

    @Nested
    class IsBinarySearchTreeTest {
        @Test
        void validBinarySearchTree() {
            tree.insert(20);
            tree.insert(10);
            tree.insert(30);
            tree.insert(6);
            tree.insert(14);
            tree.insert(24);
            tree.insert(3);
            tree.insert(8);
            tree.insert(26);

            assertTrue(tree.isBinarySearchTree());
        }

        @Test
        void emptyTree() {
            assertTrue(tree.isBinarySearchTree());
        }
    }

    @Nested
    class ValuesAtKDistanceTest {
        @Test
        void notEmptyTree() {
            tree.insert(20);
            tree.insert(10);
            tree.insert(30);
            tree.insert(6);
            tree.insert(14);
            tree.insert(24);
            tree.insert(3);
            tree.insert(8);
            tree.insert(26);

            var list = tree.valuesAtKDistance(0);
            assertEquals(20, list.get(0));

            list = tree.valuesAtKDistance(1);
            assertEquals(10, list.get(0));
            assertEquals(30, list.get(1));

            list = tree.valuesAtKDistance(3);
            assertEquals(3, list.get(0));
            assertEquals(8, list.get(1));
            assertEquals(26, list.get(2));

            list = tree.valuesAtKDistance(5);
            assertEquals(0, list.size());
        }

        @Test
        void emptyTree() {
            assertEquals(0, tree.valuesAtKDistance(0).size());
        }
    }

    @Nested
    class MaxValueTest {
        @Test
        void notEmptyTree() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);
            tree.insert(3);

            assertEquals(30, tree.maxValue());
        }

        @Test
        void emptyTree() {
            assertThrows(IllegalStateException.class,
                    () -> tree.minValue());
        }
    }

    @Nested
    class SizeTest {
        @Test
        void notEmptyTree() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);
            tree.insert(3);

            assertEquals(4, tree.size());
        }

        @Test
        void emptyTree() {
            assertEquals(0, tree.size());
        }
    }

    @Nested
    class CountLeavesTest {
        @Test
        void notEmptyTree() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);
            tree.insert(3);

            assertEquals(1, tree.countLeaves());
        }

        @Test
        void emptyTree() {
            assertEquals(0, tree.countLeaves());
        }
    }
    
    @Nested
    class ContainsTest {
        @Test
        void contains() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);
            tree.insert(3);

            assertTrue(tree.contains(10));
        }

        @Test
        void notContains() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);
            tree.insert(3);

            assertFalse(tree.contains(50));
        }

        @Test
        void emptyTree() {
            assertFalse(tree.contains(10));
        }
    }

    @Nested
    class AreSiblingTest {
        @Test
        void sibling() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);
            tree.insert(3);

            assertTrue(tree.areSibling(20, 3));
            assertTrue(tree.areSibling(3, 20));
        }

        @Test
        void notSibling() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);
            tree.insert(3);

            assertFalse(tree.areSibling(20, 30));
        }

        @Test
        void emptyTree() {
            assertFalse(tree.areSibling(1, 2));
        }
    }

    @Nested
    class GetAncestorsTest {
        @Test
        void validTest() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(30);
            tree.insert(3);

            var list = tree.getAncestors(30);

            assertEquals(10, list.get(0));
            assertEquals(20, list.get(1));
        }
    }
}
