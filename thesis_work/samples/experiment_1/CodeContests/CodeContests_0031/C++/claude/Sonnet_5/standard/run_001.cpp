#include <bits/stdc++.h>
using namespace std;
int main(){
    int m, n;
    cin >> m >> n;
    cin.ignore();
    vector<pair<int,int>> free_;
    free_.push_back({1, m});
    map<int, pair<int,int>> blocks;
    int nextId = 1;
    string out;
    for (int q = 0; q < n; q++) {
        string line;
        getline(cin, line);
        istringstream iss(line);
        string cmd;
        iss >> cmd;
        if (cmd == "alloc") {
            int need;
            iss >> need;
            bool placed = false;
            for (size_t i = 0; i < free_.size(); i++) {
                if (free_[i].second >= need) {
                    blocks[nextId] = {free_[i].first, need};
                    if (free_[i].second == need) free_.erase(free_.begin()+i);
                    else free_[i] = {free_[i].first+need, free_[i].second-need};
                    out += to_string(nextId) + "\n";
                    nextId++;
                    placed = true;
                    break;
                }
            }
            if (!placed) { out += "NULL\n"; nextId++; }
        } else if (cmd == "erase") {
            int x;
            iss >> x;
            if (blocks.count(x)) {
                free_.push_back(blocks[x]);
                blocks.erase(x);
                sort(free_.begin(), free_.end());
                vector<pair<int,int>> merged;
                for (auto &seg : free_) {
                    if (!merged.empty() && merged.back().first+merged.back().second == seg.first) {
                        merged.back().second += seg.second;
                    } else merged.push_back(seg);
                }
                free_ = merged;
            } else {
                out += "ILLEGAL_ERASE_ARGUMENT\n";
            }
        } else {
            vector<pair<int,pair<int,int>>> occ;
            for (auto &p : blocks) occ.push_back({p.second.first, {p.first, p.second.second}});
            sort(occ.begin(), occ.end());
            int pos = 1;
            map<int,pair<int,int>> newBlocks;
            for (auto &o : occ) {
                int bid = o.second.first, l = o.second.second;
                newBlocks[bid] = {pos, l};
                pos += l;
            }
            blocks = newBlocks;
            free_.clear();
            if (pos <= m) free_.push_back({pos, m-pos+1});
        }
    }
    cout << out;
    return 0;
}