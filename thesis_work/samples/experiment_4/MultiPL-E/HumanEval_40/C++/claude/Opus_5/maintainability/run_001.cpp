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
    // Sorting lets each first element be paired off with a two-pointer sweep,
    // finding a matching pair in linear rather than quadratic time.
    std::sort(l.begin(), l.end());

    for (std::size_t first = 0; first < l.size(); ++first) {
        std::size_t low = first + 1;
        std::size_t high = l.empty() ? 0 : l.size() - 1;

        while (low < high) {
            const long total = l[first] + l[low] + l[high];
            if (total == 0) {
                return true;
            }
            if (total < 0) {
                ++low;
            } else {
                --high;
            }
        }
    }

    return false;
}