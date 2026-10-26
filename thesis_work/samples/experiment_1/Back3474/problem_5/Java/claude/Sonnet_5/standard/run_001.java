import java.util.*;

class Solution {
    public int maximumScore(List<Integer> numsList, int k) {
        final long MOD = 1000000007;
        int n = numsList.size();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = numsList.get(i);
        int[] ps = new int[n];
        for (int i = 0; i < n; i++) ps[i] = primeScore(nums[i]);
        int[] left = new int[n], right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && ps[stack.peek()] < ps[i]) right[stack.pop()] = i;
            stack.push(i);
        }
        stack.clear();
        for (int i = n-1; i >= 0; i--) {
            while (!stack.isEmpty() && ps[stack.peek()] <= ps[i]) left[stack.pop()] = i;
            stack.push(i);
        }
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a,b) -> nums[b]-nums[a]);
        long ans = 1;
        long remaining = k;
        for (int i : order) {
            if (remaining <= 0) break;
            long cnt = (long)(i-left[i]) * (right[i]-i);
            long use = Math.min(cnt, remaining);
            ans = ans * modpow(nums[i], use, MOD) % MOD;
            remaining -= use;
        }
        return (int) ans;
    }
    static int primeScore(int x) {
        int cnt = 0;
        for (int d = 2; (long)d*d <= x; d++) {
            if (x % d == 0) {
                cnt++;
                while (x % d == 0) x /= d;
            }
        }
        if (x > 1) cnt++;
        return cnt;
    }
    static long modpow(long b, long e, long m) {
        long r = 1; b %= m;
        while (e > 0) { if ((e&1)==1) r=r*b%m; b=b*b%m; e>>=1; }
        return r;
    }
}