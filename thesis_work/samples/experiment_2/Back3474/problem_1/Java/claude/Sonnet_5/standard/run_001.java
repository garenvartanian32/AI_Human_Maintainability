import java.util.*;

class Solution {
    public int maxSum(int[] nums) {
        int[] best = new int[10];
        Arrays.fill(best, -1);
        int ans = -1;
        for (int x : nums) {
            int d = 0, t = x;
            while (t > 0) {
                d = Math.max(d, t % 10);
                t /= 10;
            }
            if (best[d] != -1) {
                ans = Math.max(ans, best[d] + x);
            }
            if (x > best[d]) best[d] = x;
        }
        return ans;
    }
}