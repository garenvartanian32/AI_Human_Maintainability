class Solution {
public:
    int minAbsoluteDifference(std::vector<int>& nums, int x) {
        int n = nums.size();
        std::vector<std::pair<int, int>> indexedNums; // Store the numbers along with their indices
        
        // Create a vector of pairs (number, index)
        for (int i = 0; i < n; ++i) {
            indexedNums.push_back({nums[i], i});
        }
        
        // Sort the vector based on numbers
        std::sort(indexedNums.begin(), indexedNums.end());
        
        int minDiff = INT_MAX; // Initialize the minimum difference to a large value
        
        // Traverse the sorted vector and find the minimum absolute difference
        for (int i = 0; i < n; ++i) {
            // Find the index of the element that is at least x indices apart
            int j = i + x;
            if (j < n) {
                int diff = abs(indexedNums[i].first - indexedNums[j].first);
                minDiff = std::min(minDiff, diff);
            }
        }
        
        return minDiff;
    }
};