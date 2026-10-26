#include <algorithm>
#include <climits>
#include <set>
#include <vector>

/**
 * Minimum absolute difference between elements at least x indices apart.
 */
class Solution {
public:
    /**
     * Returns min |nums[i] - nums[j]| over all pairs with |i - j| >= x.
     *
     * A sorted multiset holds every element that is far enough behind the
     * current index. For each position we only need the two entries that
     * bracket the current value.
     */
    int minAbsoluteDifference(std::vector<int>& nums, int x) {
        if (x == 0) {
            return 0;
        }

        std::multiset<int> eligibleValues;
        int smallestDifference = INT_MAX;

        for (std::size_t index = x; index < nums.size(); ++index) {
            eligibleValues.insert(nums[index - x]);
            const int current = nums[index];

            auto justAbove = eligibleValues.lower_bound(current);
            if (justAbove != eligibleValues.end()) {
                smallestDifference = std::min(smallestDifference, *justAbove - current);
            }
            if (justAbove != eligibleValues.begin()) {
                const int justBelow = *std::prev(justAbove);
                smallestDifference = std::min(smallestDifference, current - justBelow);
            }
        }

        return smallestDifference;
    }
};