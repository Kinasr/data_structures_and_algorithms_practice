package part_one.stack;

import java.util.Stack;

public class StackEx {

    public static String reverseString(String s) {
        if (s == null)
            throw new IllegalArgumentException();

        var stack = new Stack<Character>();
        for (char ch : s.toCharArray())
            stack.push(ch);

        StringBuilder rString = new StringBuilder();
        while (!stack.empty())
            rString.append(stack.pop());

        return rString.toString();
    }

    public static boolean isStringBalanced(String s) {
        if (s == null)
            throw new IllegalArgumentException();

        var stack = new Stack<Character>();
        for (Character ch : s.toCharArray()) {
            var chS = String.valueOf(ch);
            if (chS.matches("[(<\\[]"))
                stack.push(ch);
            if (chS.matches("[)>\\]]")) {
                if (stack.isEmpty() || !stack.pop().equals(getEquivalentBracket(ch))) return false;
            }
        }
        return stack.empty();
    }

    private static Character getEquivalentBracket(Character bracket) {
        Character eBracket;
        switch (bracket) {
            case '(' -> eBracket = ')';
            case ')' -> eBracket = '(';
            case '[' -> eBracket = ']';
            case ']' -> eBracket = '[';
            case '<' -> eBracket = '>';
            case '>' -> eBracket = '<';
            default -> eBracket = null;
        }
        return eBracket;
    }
}
