class Solution {
public:
    long long findMaximumElegance(vector<vector<int>>& items, int k) {
        int n=items.size();
        vector<long long>profit(n);
        vector<long long>category(n);
        for(int i=0;i<n;i++){
            profit[i]=items[i][0];
            category[i]=items[i][1];
        }
        sort(profit.begin(),profit.end());
        sort(category.begin(),category.end());
        long long ans=0;
        long long max_profit=0;
        long long max_category=0;
        for(int i=0;i<n;i++){
            if(i<k){
                max_profit+=profit[i];
                max_category+=category[i];
                ans=max(ans,max_profit+max_category*max_category);
                continue;
            }
            max_profit-=profit[i-k];
            max_profit+=profit[i];
            max_category-=category[i-k];
            max_category+=category[i];
            ans=max(ans,max_profit+max_category*max_category);
        }
        return ans;
    }
};