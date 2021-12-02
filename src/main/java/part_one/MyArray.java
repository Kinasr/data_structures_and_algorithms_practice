package part_one;

import java.util.Arrays;

public class MyArray<T> {
    private int length;
    private int count = 0;
    private Object[] myArray;

    public MyArray(int length) {
        this.length = length;
        myArray = new Object[length];
    }

    public void insert(int value) {
        if (count >= length) {
            var newArray = new Object[length + 1];
            System.arraycopy(myArray, 0, newArray, 0, length);
            length++;

            myArray = newArray;
        }
        myArray[count++] = value;
    }

    public void removeAt(int index) {
        if (index >= length || index < 0)
            throw new IllegalArgumentException();

        var newArray = new Object[length - 1];
        for (int i = 0; i < length; i++) {
            if (i < index) newArray[i] = myArray[i];
            else if (i > index) newArray[i - 1] = myArray[i];
        }
        count--;
        length--;
        myArray = newArray;
    }

    public T get(int index) {
        return (T) myArray[index];
    }

    public int indexOf(T value) {
        for (var i = 0; i < length; i++) {
            if (myArray[i] == value) return i;
        }
        return -1;
    }

    public int length() {
        return length;
    }

    @Override
    public String toString() {
        return Arrays.toString(myArray);
    }
}

