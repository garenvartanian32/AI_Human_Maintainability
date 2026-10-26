import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);
        int[] spf = new int[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= maxVal; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            int s = 0, last = -1;
            while (x > 1) {
                int p = spf[x];
                if (p != last) { s++; last = p; }
                x /= p;
            }
            scores[i] = s;
        }
        int[] left = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && scores[stack.peek()] < scores[i]) stack.pop();
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        int[] right = new int[n];
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && scores[stack.peek()] <= scores[i]) stack.pop();
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> nums.get(b) - nums.get(a));

        long ans = 1;
        long remaining = k;
        for (int idx : order) {
            if (remaining <= 0) break;
            long cnt = (long) (idx - left[idx]) * (right[idx] - idx);
            cnt = Math.min(cnt, remaining);
            ans = ans * power(nums.get(idx), cnt) % MOD;
            remaining -= cnt;
        }
        return (int) ans;
    }

    private long power(long base, long exp) {
        base %= MOD;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }
}