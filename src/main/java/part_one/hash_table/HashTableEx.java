package part_one.hash_table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class HashTableEx {

    public static char getFirstUnrepeatableChar(String text) {
        var uniqueChars = new HashMap<Character, Integer>();
        var ignoreOnes = new HashMap<Character, Integer>();
        AtomicReference<Character> unique = null;

        var chars = text.toCharArray();
        for (var i = 0; i < chars.length; i++) {
            if (!uniqueChars.containsKey(chars[i]) && !ignoreOnes.containsKey(chars[i]))
                uniqueChars.put(chars[i], i);
            else if (uniqueChars.containsKey(chars[i])) {
                var chIndex = uniqueChars.get(chars[i]);
                uniqueChars.remove(chars[i]);
                ignoreOnes.put(chars[i], chIndex);
            }
        }

//        AtomicInteger firstIndex = null;
//        uniqueChars.forEach(
//                (ch, index) -> {
//                    if (firstIndex == null || index < firstIndex) {
//                        firstIndex = index;
//                    }
//
//                }
//        );
        return unique.get();
    }

    public static char getFirstRepeatedCharacter(String text) {
        if (text == null) throw new IllegalStateException();

        var charsSet = new HashSet<Character>();

        for (var ch : text.toCharArray()) {
            if (!charsSet.add(ch))
                return ch;
        }
        return Character.MIN_VALUE;
    }
}
