package part_one.hash_table;


import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashTable {
    public final Integer TABLE_SIZE = 100;
    private final LinkedList<Entry>[] map;

    public HashTable() {
        this.map = new LinkedList[TABLE_SIZE];
    }

    public boolean put(Integer key, String value) {
        var index = hash(key);

        try{
            remove(key);
        } catch (Throwable ignored) {}

        if (map[index] == null)
            map[index] = new LinkedList<>();
        map[index].push(new Entry(key, value));

        return true;
    }

    public String get(Integer key) {
        var index = hash(key);

        var entries = map[index];

        if (entries != null && !entries.isEmpty()) {
            for (Entry entry : entries) {
                if (Objects.equals(entry.key, key))
                    return entry.value;
            }
        }

        throw new NoSuchElementException();
    }

    public String remove(Integer key) {
        var index = hash(key);

        var entries = map[index];

        if (entries != null && !entries.isEmpty()) {
            for (Entry entry : entries) {
                if (Objects.equals(entry.key, key)){
                    var value = entry.value;
                    entries.remove(entry);
                    return value;
                }
            }
        }

        throw new NoSuchElementException();
    }

    public Integer size() {
        int count = 0;
        for (LinkedList<Entry> entry : map) {
            if (entry != null)
                count += entry.size();
        }
        return count;
    }

    private Integer hash(Integer key) {
        return key % TABLE_SIZE;
    }

    private record Entry(Integer key, String value) {
    }
}
