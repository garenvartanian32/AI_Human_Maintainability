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
    std::sort(l.begin(), l.end());
    int n = l.size();
    for (int i = 0; i < n; i++) {
        int lo = i + 1, hi = n - 1;
        while (lo < hi) {
            long total = l[i] + l[lo] + l[hi];
            if (total == 0) return true;
            else if (total < 0) lo++;
            else hi--;
        }
    }
    return false;
}

int main() {
    std::cout << std::boolalpha;
    std::cout << triples_sum_to_zero({1,3,5,0}) << std::endl;
    std::cout << triples_sum_to_zero({1,3,-2,1}) << std::endl;
    std::cout << triples_sum_to_zero({1,2,3,7}) << std::endl;
    std::cout << triples_sum_to_zero({2,4,-5,3,9,7}) << std::endl;
    std::cout << triples_sum_to_zero({1}) << std::endl;
}