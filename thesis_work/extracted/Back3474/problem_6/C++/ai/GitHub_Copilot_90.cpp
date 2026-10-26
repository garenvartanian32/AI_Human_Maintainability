class Solution {
public:
    long long findMaximumElegance(vector<vector<int>>& items, int k) {
        int n = items.size();
        vector<pair<int,int>> v;
        for(int i=0;i<n;i++){
            v.push_back({items[i][0],items[i][1]});
        }
        sort(v.begin(),v.end(),greater<pair<int,int>>());
        int i=0;
        long long ans = 0;
        unordered_map<int,int> mp;
        while(i<n && k>0){
            if(mp[v[i].second]==0){
                ans += v[i].first;
                mp[v[i].second]++;
                k--;
            }
            i++;
        }
        return ans + (k*k);
    }
};