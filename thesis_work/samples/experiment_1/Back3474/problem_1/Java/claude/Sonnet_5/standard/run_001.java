import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums) {
        Map<Integer,Integer> best = new HashMap<Integer,Integer>();
        int ans = -1;
        for (int v : nums) {
            int md = 0;
            int t = v;
            while (t > 0) {
                md = Math.max(md, t % 10);
                t /= 10;
            }
            if (best.containsKey(md)) {
                ans = Math.max(ans, best.get(md) + v);
                best.put(md, Math.max(best.get(md), v));
            } else {
                best.put(md, v);
            }
        }
        return ans;
    }
}