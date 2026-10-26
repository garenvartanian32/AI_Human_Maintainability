class Solution {
public:
    const int MOD = 1e9 + 7;

    int maximumScore(std::vector<int>& nums, int k) {
        int n = nums.size();
        std::unordered_map<int, int> primeScore; // Map to store prime scores of elements
        int left = 0; // Left pointer of the sliding window
        long long score = 1; // Initial score
        int maxScore = 1; // Maximum score

        for (int right = 0; right < n; ++right) {
            // Calculate prime score for the new element
            int curScore = calculatePrimeScore(nums[right]);

            // Update prime score map
            primeScore[nums[right]]++;

            // Increment the score by multiplying it with the prime score
            score = (score * curScore) % MOD;

            // Check if the window size exceeds k, then we need to shrink the window
            while (right - left + 1 > k) {
                int leftElem = nums[left];
                primeScore[leftElem]--;

                // If the left element has no more occurrences in the window, remove it from the map
                if (primeScore[leftElem] == 0) {
                    primeScore.erase(leftElem);
                }

                // Move the left pointer to the right
                left++;
            }

            // Update the maximum score
            maxScore = std::max(maxScore, static_cast<int>(score));
        }

        return maxScore;
    }

    // Helper function to calculate the prime score of an integer
    int calculatePrimeScore(int x) {
        int score = 0;
        for (int i = 2; i * i <= x; ++i) {
            while (x % i == 0) {
                x /= i;
                score++;
            }
        }
        if (x > 1) {
            score++;
        }
        return score;
    }
};