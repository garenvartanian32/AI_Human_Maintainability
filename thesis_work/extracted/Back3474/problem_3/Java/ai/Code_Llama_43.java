class Solution {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int min = Integer.MAX_VALUE;
        int n = nums.size();
        for(int i = 0; i < n; i++){
            for(int j = i + x; j < n; j++){
                min = Math.min(min, Math.abs(nums.get(i) - nums.get(j)));
            }
        }
        return min;
    }
}