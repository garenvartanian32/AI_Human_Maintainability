import java.util.*;

class Solution {
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        int n = items.length;
        long total = 0;
        for (int i = 0; i < k; i++) total += items[i][0];
        Set<Integer> seen = new HashSet<>();
        Deque<Integer> dup = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            int profit = items[i][0], cat = items[i][1];
            if (!seen.add(cat)) {
                dup.push(profit);
            }
        }
        long ans = total + (long) seen.size() * seen.size();
        for (int i = k; i < n; i++) {
            if (dup.isEmpty()) break;
            int profit = items[i][0], cat = items[i][1];
            if (seen.contains(cat)) continue;
            total += profit - dup.pop();
            seen.add(cat);
            ans = Math.max(ans, total + (long) seen.size() * seen.size());
        }
        return ans;
    }
}