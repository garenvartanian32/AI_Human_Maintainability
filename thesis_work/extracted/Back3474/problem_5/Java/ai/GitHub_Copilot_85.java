public class Solution {
    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums.get(stack.peek()) >= nums.get(i)) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums.get(stack.peek()) >= nums.get(i)) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (left[i] < k && right[i] > k) {
                ans = Math.max(ans, nums.get(i) * (right[i] - left[i] - 1));
            }
        }
        return ans;
    }
}