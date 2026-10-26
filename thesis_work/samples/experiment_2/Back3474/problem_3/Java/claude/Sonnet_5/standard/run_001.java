import java.util.*;

class Solution {
    public int minAbsoluteDifference(int[] nums, int x) {
        int n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ans = Integer.MAX_VALUE;
        for (int j = x; j < n; j++) {
            int add = nums[j - x];
            map.merge(add, 1, Integer::sum);
            int cur = nums[j];
            Integer floor = map.floorKey(cur);
            Integer ceil = map.ceilingKey(cur);
            if (floor != null) ans = Math.min(ans, cur - floor);
            if (ceil != null) ans = Math.min(ans, ceil - cur);
        }
        return ans;
    }
}