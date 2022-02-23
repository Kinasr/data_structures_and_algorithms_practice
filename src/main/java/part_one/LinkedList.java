package part_one;

import java.util.NoSuchElementException;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public class LinkedList<T> {
    private static class Node<T> {
        private final T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public Node<T> setNext(Node<T> next) {
            this.next = next;
            return this;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int length = 0;

    private final BooleanSupplier isEmpty = () -> first == null;
    private final Predicate<Integer> isValidIndex = (index) -> index < length && index >= 0;
    private final Predicate<Integer> isValidKth = (kth) -> kth <= length && kth > 0;

    public void addFirst(T value) {
        if (isEmpty.getAsBoolean()) {
            first = last = new Node<>(value);
        } else {
            first = new Node<>(value)
                    .setNext(first);
        }
        length++;
    }

    public void addLast(T value) {
        if (isEmpty.getAsBoolean()) {
            first = last = new Node<>(value);
        } else {
            var node = new Node<>(value);
            last.setNext(node);
            last = node;
        }
        length++;
    }

    public void deleteFirst() {
        if (isEmpty.getAsBoolean())
            throw new NoSuchElementException();

        if (first == last){
            first = last = null;
            length--;
            return;
        }
        var second = first.next;
        first.next = null;
        first = second;

        length--;
    }

    public void deleteLast() {
        if (isEmpty.getAsBoolean())
            throw new NoSuchElementException();

        if (first == last){
            first = last = null;
            length--;
            return;
        }

        var currentNode = first;
        while (true){
            if (currentNode.next == last) {
                last = currentNode;
                last.setNext(null);
                length--;
                return;
            }
            currentNode = currentNode.next;
        }
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public int indexOf(T value) {
        if (isEmpty.getAsBoolean())
            return -1;

        var count = 0;
        var currentNode = first;
        do {
            if (currentNode.value == value)
                return count;
            currentNode = currentNode.next;
            count++;
        } while (count < length);

        return -1;
    }

    public T get(int index) {
        if (!isValidIndex.test(index) || isEmpty.getAsBoolean()) return null;

        var node = first;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }

        return node.value;
    }

    public T getKthFromTheEnd2(int k) {
        return get(length - k);
    }

    public T getKthFromTheEnd(int k) {
        // dif k - 1
        if (!isValidKth.test(k)) return null;

        var node1 = first;
        Node<T> node2 = null;

        for (int i = 0; i < length; i++) {
            node1 = node1.next;
            if (i >= k - 1) node2 = node2 == null ? first : node2.next;
        }
        return node2 == null ? null : node2.value;
    }

    public T toArray() {
        var array = new Object[length];

        var currentNode = first;
        for (int i = 0; i < length; i++) {
            array[i] = currentNode.value;
            currentNode = currentNode.next;
        }

        return (T) array;
    }

    public void reverse() {
        Node<T> pNode = null;
        var cNode = first;
        var nNode = first.next;

        for (int i = 0; i < length; i++) {
            cNode.next = pNode;
            if (pNode == null) last = cNode;

            pNode = cNode;
            cNode = nNode;

            if (cNode == null) break;
            nNode = cNode.next;

            if (nNode == null) first = cNode;
        }
    }

    public int size() {
        return length;
    }

    public T first() {
        return first.value;
    }

    public T last() {
        return last.value;
    }
}
