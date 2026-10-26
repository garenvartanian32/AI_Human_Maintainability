class Solution {
    int minAbsoluteDifference(List<Integer> nums, int x) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) < min) {
                min = nums.get(i);
            }
            if (nums.get(i) > max) {
                max = nums.get(i);
            }
        }
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            if (Math.abs(nums.get(i) - min) >= x) {
                diff = Math.min(diff, Math.abs(nums.get(i) - min));
            }
            if (Math.abs(nums.get(i) - max) >= x) {
                diff = Math.min(diff, Math.abs(nums.get(i) - max));
            }
        }
        return diff;
    }
}