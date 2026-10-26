import java.util.*;

class Solution {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int n = nums.size();
        TreeMultiset:
        TreeMap<Integer,Integer> sl = new TreeMap<Integer,Integer>();
        int ans = Integer.MAX_VALUE;
        for (int i = x; i < n; i++) {
            int add = nums.get(i-x);
            sl.merge(add, 1, Integer::sum);
            int cur = nums.get(i);
            Integer ceil = sl.ceilingKey(cur);
            Integer floor = sl.floorKey(cur);
            if (ceil != null) ans = Math.min(ans, Math.abs(ceil-cur));
            if (floor != null) ans = Math.min(ans, Math.abs(floor-cur));
        }
        return ans;
    }
}