#include<assert.h>
#include<bits/stdc++.h>
// triples_sum_to_zero takes a vector of integers as an input.
// it returns true if there are three distinct elements in the vector that
// sum to zero, and false otherwise.
// >>> triples_sum_to_zero((std::vector<long>({(long)1, (long)3, (long)5, (long)0})))
// (false)
// >>> triples_sum_to_zero((std::vector<long>({(long)1, (long)3, (long)-2, (long)1})))
// (true)
// >>> triples_sum_to_zero((std::vector<long>({(long)1, (long)2, (long)3, (long)7})))
// (false)
// >>> triples_sum_to_zero((std::vector<long>({(long)2, (long)4, (long)-5, (long)3, (long)9, (long)7})))
// (true)
// >>> triples_sum_to_zero((std::vector<long>({(long)1})))
// (false)
bool triples_sum_to_zero(std::vector<long> l) {

    using namespace std;
    vector<long> s = l;
    sort(s.begin(), s.end());
    int n = s.size();
    for (int i = 0; i < n - 2; i++) {
        int lo = i+1, hi = n-1;
        while (lo < hi) {
            long sum = s[i] + s[lo] + s[hi];
            if (sum == 0) return true;
            else if (sum < 0) lo++;
            else hi--;
        }
    }
    return false;
}