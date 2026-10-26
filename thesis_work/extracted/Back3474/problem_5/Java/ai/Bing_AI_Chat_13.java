public class Solution {
    private static final int MOD = 1_000_000_007;
    private List<Integer> nums;
    private int n;
    private List<Integer>[] factors;

    public int maximumScore(List<Integer> nums, int k) {
        this.nums = nums;
        this.n = nums.size();
        this.factors = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            factors[i] = primeFactors(nums.get(i));
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        pq.offer(new int[]{0, n - 1, maxPrimeScore(0, n - 1)});

        long res = 1;
        boolean[] chosen = new boolean[n];
        while (k-- > 0) {
            int[] cur = pq.poll();
            int l = cur[0], r = cur[1], maxScore = cur[2];
            res = (res * maxScore) % MOD;

            int maxIdx = maxPrimeScoreIndex(l, r);
            chosen[maxIdx] = true;

            if (maxIdx - 1 >= l && !chosen[maxIdx - 1]) {
                pq.offer(new int[]{l, maxIdx - 1, maxPrimeScore(l, maxIdx - 1)});
            }
            if (maxIdx + 1 <= r && !chosen[maxIdx + 1]) {
                pq.offer(new int[]{maxIdx + 1, r, maxPrimeScore(maxIdx + 1, r)});
            }
        }

        return (int) res;
    }

    private List<Integer> primeFactors(int num) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                factors.add(i);
                while (num % i == 0) {
                    num /= i;
                }
            }
        }
        if (num > 1) {
            factors.add(num);
        }
        return factors;
    }

    private int maxPrimeScore(int l, int r) {
        int maxScore = 0;
        for (int i = l; i <= r; i++) {
            maxScore = Math.max(maxScore, factors[i].size());
        }
        return maxScore;
    }

    private int maxPrimeScoreIndex(int l, int r) {
        int maxScore = 0, idx = -1;
        for (int i = l; i <= r; i++) {
            if (factors[i].size() >= maxScore) {
                maxScore = factors[i].size();
                idx = i;
            }
        }
        return idx;
    }
}