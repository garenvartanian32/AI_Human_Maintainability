class Solution {
public:
    long long findMaximumElegance(vector<vector<int>>& items, int k) {
        int n = items.size();
        vector<int> profit(n);
        vector<int> category(n);
        
        for(int i = 0; i < n; i++){
            profit[i] = items[i][0];
            category[i] = items[i][1];
        }
        
        sort(profit.begin(), profit.end());
        sort(category.begin(), category.end());
        
        int max_elegance = 0;
        
        for(int i = 0; i < n; i++){
            int j = i + k - 1;
            if(j >= n) break;
            
            int total_profit = profit[j] - profit[i];
            int distinct_categories = upper_bound(category.begin(), category.end(), category[j]) - upper_bound(category.begin(), category.end(), category[i]);
            
            max_elegance = max(max_elegance, total_profit + distinct_categories * distinct_categories);
        }
        
        return max_elegance;
    }
};