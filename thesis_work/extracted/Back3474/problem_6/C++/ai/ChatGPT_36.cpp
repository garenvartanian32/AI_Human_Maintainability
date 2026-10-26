class Solution {
public:
    long long findMaximumElegance(std::vector<std::vector<int>>& items, int k) {
        std::unordered_map<int, std::vector<int>> categoryToProfits;
        std::unordered_map<int, long long> categoryToTotalProfit;

        for (auto& item : items) {
            int profit = item[0];
            int category = item[1];
            categoryToProfits[category].push_back(profit);
            categoryToTotalProfit[category] += profit;
        }

        std::vector<long long> maxProfits;
        long long totalElegance = 0;

        for (auto& pair : categoryToProfits) {
            int category = pair.first;
            std::vector<int>& profits = pair.second;

            std::sort(profits.rbegin(), profits.rend());

            long long categoryTotalProfit = categoryToTotalProfit[category];
            int categoryDistinctCount = profits.size();

            for (int i = 0; i < k && i < categoryDistinctCount; ++i) {
                totalElegance += profits[i] + static_cast<long long>(categoryDistinctCount) * categoryDistinctCount;
                maxProfits.push_back(profits[i]);
            }
        }

        std::sort(maxProfits.rbegin(), maxProfits.rend());

        for (int i = k; i < maxProfits.size(); ++i) {
            totalElegance -= maxProfits[i];
        }

        return totalElegance;
    }
};