import java.util.*;

class Solution {
    public long maximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a,b) -> b[0]-a[0]);
        long total = 0;
        Set<Integer> seen = new HashSet<Integer>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < k; i++) {
            int p = items[i][0], c = items[i][1];
            total += p;
            if (seen.contains(c)) stack.push(p);
            else seen.add(c);
        }
        long best = total + (long) seen.size() * seen.size();
        for (int i = k; i < items.length; i++) {
            int p = items[i][0], c = items[i][1];
            if (!seen.contains(c) && !stack.isEmpty()) {
                total += p - stack.pop();
                seen.add(c);
                best = Math.max(best, total + (long) seen.size() * seen.size());
            }
        }
        return best;
    }
}