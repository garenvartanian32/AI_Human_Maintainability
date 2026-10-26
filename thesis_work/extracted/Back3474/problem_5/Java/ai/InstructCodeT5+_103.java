class Solution {    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int[] primes = new int[n];
        int[] scores = new int[n];
        Arrays.fill(primes, -1);
        Arrays.fill(scores, 1);
        
        for (int i = 0; i < n; i++) {
            primes[i] = getPrime(nums.get(i));
        }
        
        int maxScore = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int l = Math.max(i, primes[i] - 1);
                int r = Math.min(j, primes[j] - 1);
                int score = (r - l + 1) * scores[j];
                if (score > maxScore) {
                    maxScore = score;
                }
            }
        }
        
        return (int) (maxScore % (1e9 + 7));
    }
    
    private int getPrime(int num) {
        int res = num;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                res = i;
                break;
            }
        }
        return res;
    }
}