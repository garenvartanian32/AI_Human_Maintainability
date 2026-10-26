class Solution {
public:    
    int getPrimeScore(int num) {
        int count = 0;
        for (int i = 2; i * i <= num; i++) {
            while (num % i == 0) {
                num /= i;
                count++;
            }
        }
        return count;
    }

    int getMaxPrimeScore(vector<int>& nums, int l, int r) {
        int maxPrimeScore = 0;
        for (int i = l; i <= r; i++) {
            maxPrimeScore = max(maxPrimeScore, getPrimeScore(nums[i]));
        }
        return maxPrimeScore;
    }

    int getMaxScore(vector<int>& nums, int l, int r) {
        int maxScore = 0;
        for (int i = l; i <= r; i++) {
            maxScore = max(maxScore, nums[i]);
        }
        return maxScore;
    }

    int maximumScore(vector<int>& nums, int k) {
        int score = 1;
        int maxScore = 0;

        int l = 0;
        int r = nums.size() - 1;
        
        for (int i = 0; i < k; i++) {
            int temp = getMaxScore(nums, l, r);
            maxScore = max(maxScore, temp);
            int tempPrimeScore = getMaxPrimeScore(nums, l, r);
            int tempScore = temp * tempPrimeScore;
            l = l + 1;
            r = r - 1;
        }
        
        return maxScore;
    }
};