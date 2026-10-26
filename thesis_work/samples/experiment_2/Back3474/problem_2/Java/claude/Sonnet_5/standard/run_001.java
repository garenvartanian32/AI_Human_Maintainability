import java.util.*;

class Solution {
    public String finalString(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        boolean normal = true;
        for (char c : s.toCharArray()) {
            if (c == 'i') {
                normal = !normal;
            } else {
                if (normal) dq.addLast(c);
                else dq.addFirst(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (normal) {
            for (char c : dq) sb.append(c);
        } else {
            Iterator<Character> it = dq.descendingIterator();
            while (it.hasNext()) sb.append(it.next());
        }
        return sb.toString();
    }
}