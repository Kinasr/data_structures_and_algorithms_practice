package part_two.binary_tree;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class BinaryTree {
    private Node root;
    private final Predicate<Node> isLeaf = node ->
            node.leftChild == null && node.rightChild == null;

    public boolean find(Integer value) {
        var cNode = root;
        while (cNode != null) {
            if (Objects.equals(value, cNode.value))
                return true;
            else if (value > cNode.value)
                cNode = cNode.rightChild;
            else
                cNode = cNode.leftChild;
        }
        return false;
    }

    public void insert(Integer value) {
        var node = new Node(value);

        if (root == null)
            root = node;
        else {
            var cNode = root;
            while (true) {
                if (value > cNode.value) {
                    if (cNode.rightChild == null) {
                        cNode.rightChild = node;
                        break;
                    } else
                        cNode = cNode.rightChild;
                } else if (value < cNode.value) {
                    if (cNode.leftChild == null) {
                        cNode.leftChild = node;
                        break;
                    } else
                        cNode = cNode.leftChild;
                } else
                    break;
            }
        }
    }

    public Integer height() {
        return height(root);
    }

    private Integer height(Node node) {
        if (node == null) return -1;

        if (isLeaf.test(node)) return 0;

        return 1 + max(height(node.leftChild), height(node.rightChild));
    }

    public Integer valueDepth(Integer value) {
        var cNode = root;
        var depth = 0;

        while (cNode != null) {
            if (Objects.equals(value, cNode.value))
                return depth;
            else if (value > cNode.value)
                cNode = cNode.rightChild;
            else
                cNode = cNode.leftChild;

            depth++;
        }
        throw new NoSuchElementException();
    }

    public Integer minValue() {
        if (isEmpty())
            throw new IllegalStateException();

        return minValue(root);
    }

    private Integer minValue(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;

        if (isLeaf.test(node)) return node.value;

        return min(node.value, min(minValue(node.leftChild), minValue(node.rightChild)));
    }

    public Integer maxValue() {
        if (isEmpty())
            throw new IllegalStateException();

        return maxValue(root);
    }

    private Integer maxValue(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;

        if (isLeaf.test(node)) return node.value;

        return max(node.value, max(maxValue(node.leftChild), maxValue(node.rightChild)));
    }

    public Integer size() {
        return size(root);
    }

    private Integer size(Node node) {
        if (node == null)
            return 0;

        return 1 + size(node.leftChild) + size(node.rightChild);
    }

    public Integer countLeaves() {
        return valuesAtKDistance(height()).size();
    }

    public List<Integer> orderTreeBy(TreeOrder order) {
        return order.treeToList.apply(this);
    }

    private List<Integer> orderTreeByLevel(Node node) {
        var q = new ArrayDeque<Node>();
        var treeList = new ArrayList<Integer>();

        if (node != null)
            q.add(node);

        while (!q.isEmpty()) {
            var cNode = q.pop();
            if (cNode.leftChild != null) q.add(cNode.leftChild);
            if (cNode.rightChild != null) q.add(cNode.rightChild);

            treeList.add(cNode.value);
        }
        return treeList;
    }

    private List<Integer> orderTreeByPreOrder(Node node) {
        var treeList = new ArrayList<Integer>();

        if (node != null) {
            treeList.add(node.value);
            treeList.addAll(orderTreeByPreOrder(node.leftChild));
            treeList.addAll(orderTreeByPreOrder(node.rightChild));
        }

        return treeList;
    }

    private List<Integer> orderTreeByInOrder(Node node) {
        var treeList = new ArrayList<Integer>();

        if (node != null) {
            treeList.addAll(orderTreeByInOrder(node.leftChild));
            treeList.add(node.value);
            treeList.addAll(orderTreeByInOrder(node.rightChild));
        }

        return treeList;
    }

    private List<Integer> orderTreeByPostOrder(Node node) {
        var treeList = new ArrayList<Integer>();

        if (node != null) {
            treeList.addAll(orderTreeByPostOrder(node.leftChild));
            treeList.addAll(orderTreeByPostOrder(node.rightChild));
            treeList.add(node.value);
        }

        return treeList;
    }

    public List<Integer> valuesAtKDistance(int distance) {
        return valuesAtKDistance(root, distance);
    }

    private List<Integer> valuesAtKDistance(Node node, int distance) {
        var list = new ArrayList<Integer>();

        if (distance == 0 && node != null) {
            list.add(node.value);
        } else if (distance > 0 && node != null) {
            distance--;
            list.addAll(valuesAtKDistance(node.leftChild, distance));
            list.addAll(valuesAtKDistance(node.rightChild, distance));
        }
        return list;
    }

    public List<Integer> getAncestors(int value) {
        var list = new ArrayList<Integer>();
        var cNode = root;

        while (cNode != null) {
            if (Objects.equals(value, cNode.value))
                return list;
            else if (value > cNode.value){
                list.add(cNode.value);
                cNode = cNode.rightChild;
            }
            else {
                list.add(cNode.value);
                cNode = cNode.leftChild;
            }
        }
        return list;
    }

    public boolean contains(int value) {
        if (isEmpty()) return false;

        return contains(root, value);
    }

    private boolean contains(Node node, int value) {
        if (node == null) return false;

        return node.value == value || contains(node.leftChild, value) || contains(node.rightChild, value);
    }

    public boolean areSibling(int a, int b) {
        if (isEmpty()) return false;

        return areSibling(root, a, b);
    }

    private boolean areSibling(Node node, int a, int b) {
        if (node.leftChild == null || node.rightChild == null) return false;

        return (node.leftChild.value == a && node.rightChild.value == b) ||
                (node.leftChild.value == b && node.rightChild.value == a) ||
                areSibling(node.leftChild, a, b) ||
                areSibling(node.rightChild, a, b);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean isEqual(BinaryTree tree) {
        if(tree == null) return false;

        return isEqual(root, tree.root);
    }

    private boolean isEqual(Node node1, Node node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;

        return Objects.equals(node1.value, node2.value) &&
                isEqual(node1.leftChild, node2.leftChild) &&
                isEqual(node1.rightChild, node2.rightChild);
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean isBinarySearchTree(Node node, int max, int min) {
        if (node == null) return true;

        return node.value <= max && node.value >= min &&
                isBinarySearchTree(node.leftChild, node.value - 1, min) &&
                isBinarySearchTree(node.rightChild, max, node.value + 1);
    }

    private static class Node {
        private final Integer value;
        private Node leftChild;
        private Node rightChild;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public enum TreeOrder {
        BREADTH_FIRST(t -> t.orderTreeByLevel(t.root)),
        PRE_ORDER(t -> t.orderTreeByPreOrder(t.root)),
        IN_ORDER(t -> t.orderTreeByInOrder(t.root)),
        POST_ORDER(t -> t.orderTreeByPostOrder(t.root));

        public final Function<BinaryTree, List<Integer>> treeToList;

        TreeOrder(Function<BinaryTree, List<Integer>> treeToList) {
            this.treeToList = treeToList;
        }
    }
}
