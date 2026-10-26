class Solution {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int n = nums.size();
        int[] arr  = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = nums.get(i);
        }
        Arrays.sort(arr);
        int res = Integer.MAX_VALUE;
        for(int i =  0; i < n; i++) {
            int left = i + 1;
            int right = n - 1;
            while(left <= right) {
                int mid = left + (right - left) / 2;
                if(arr[mid] - arr[  i] >= x) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if(left < n) {
                res = Math.min(res, arr[left] - arr[i]);
            }
            left  = 0;
            right = i - 1;
            while(left <= right) {
                int mid = left + (right - left) / 2;
                if(arr[i] - arr[mid] >= x) {
                    left = mid + 1;
                 } else {
                    right = mid - 1;
                }
            }
            if(right >= 0) {
                res = Math.min(res, arr[i] - arr[right]);
            }
        }
        return res;
    }
}