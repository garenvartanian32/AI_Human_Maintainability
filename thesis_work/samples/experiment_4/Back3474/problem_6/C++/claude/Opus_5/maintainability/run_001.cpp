#include <algorithm>
#include <unordered_set>
#include <vector>

/**
 * Maximum elegance of a k-item selection (total profit + distinct categories squared).
 */
class Solution {
public:
    /**
     * Returns the best elegance over all subsets of exactly k items.
     *
     * Start with the k most profitable items, then repeatedly trade the least
     * profitable duplicate-category item for the most profitable item of a
     * brand-new category. Profit can only fall, but the distinct-category term
     * grows quadratically, so the trade is sometimes worth it.
     */
    long long findMaximumElegance(std::vector<std::vector<int>>& items, int k) {
        std::sort(items.begin(), items.end(),
                  [](const std::vector<int>& left, const std::vector<int>& right) {
                      return left[kProfit] > right[kProfit];
                  });

        long long totalProfit = 0;
        std::unordered_set<int> seenCategories;
        // Profits of chosen items whose category was already represented;
        // used as a stack so back() is the cheapest one to give up.
        std::vector<int> droppableProfits;

        for (int i = 0; i < k; ++i) {
            totalProfit += items[i][kProfit];
            const bool categoryIsNew = seenCategories.insert(items[i][kCategory]).second;
            if (!categoryIsNew) {
                droppableProfits.push_back(items[i][kProfit]);
            }
        }

        long long bestElegance = totalProfit + square(seenCategories.size());

        for (std::size_t i = k; i < items.size(); ++i) {
            if (droppableProfits.empty()) {
                break;
            }
            if (seenCategories.count(items[i][kCategory]) > 0) {
                continue;
            }

            totalProfit += items[i][kProfit] - droppableProfits.back();
            droppableProfits.pop_back();
            seenCategories.insert(items[i][kCategory]);
            bestElegance = std::max(bestElegance, totalProfit + square(seenCategories.size()));
        }

        return bestElegance;
    }

private:
    static constexpr int kProfit = 0;
    static constexpr int kCategory = 1;

    static long long square(std::size_t value) {
        return static_cast<long long>(value) * static_cast<long long>(value);
    }
};