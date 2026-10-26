public class Solution {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(nums.get(0), 0);
        int n = nums.size();
        int minDiff = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            if (i >= x) {
                map.remove(nums.get(i - x));
            }
            Integer lower = map.floorKey(nums.get(i));
            Integer higher = map.ceilingKey(nums.get(i));
            if (lower != null) {
                minDiff = Math.min(minDiff, Math.abs(nums.get(i) - lower));
            }
            if (higher != null) {
                minDiff = Math.min(minDiff, Math.abs(nums.get(i) - higher));
            }
            map.put(nums.get(i), i);
        }

        return minDiff;
    }
}