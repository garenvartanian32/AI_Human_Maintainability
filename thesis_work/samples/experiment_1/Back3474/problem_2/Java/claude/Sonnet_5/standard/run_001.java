import java.util.*;

class Solution {
    public String finalString(String s) {
        Deque<Character> dq = new ArrayDeque<Character>();
        boolean head = true; // true means append to front conceptually reversed handling
        for (char c : s.toCharArray()) {
            if (c == 'i') {
                // reverse current deque
                Deque<Character> rev = new ArrayDeque<Character>();
                while (!dq.isEmpty()) rev.addLast(dq.pollLast());
                dq = rev;
            } else {
                dq.addLast(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : dq) sb.append(c);
        return sb.toString();
    }
}