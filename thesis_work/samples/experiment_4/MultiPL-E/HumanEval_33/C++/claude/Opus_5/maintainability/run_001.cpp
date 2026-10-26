#include<assert.h>
#include<bits/stdc++.h>
// This function takes a vector l and returns a vector l' such that
// l' is identical to l in the indicies that are not divisible by three, while its values at the indicies that are divisible by three are equal
// to the values of the corresponding indicies of l, but sorted.
// >>> sort_third((std::vector<long>({(long)1, (long)2, (long)3})))
// (std::vector<long>({(long)1, (long)2, (long)3}))
// >>> sort_third((std::vector<long>({(long)5, (long)6, (long)3, (long)4, (long)8, (long)9, (long)2})))
// (std::vector<long>({(long)2, (long)6, (long)3, (long)4, (long)8, (long)9, (long)5}))
std::vector<long> sort_third(std::vector<long> l) {
    constexpr std::size_t kStride = 3;

    // Only the every-third positions are reordered; they keep their slots but
    // receive the same values back in ascending order.
    std::vector<long> sortedValues;
    for (std::size_t slot = 0; slot < l.size(); slot += kStride) {
        sortedValues.push_back(l[slot]);
    }
    std::sort(sortedValues.begin(), sortedValues.end());

    std::vector<long> result = l;
    std::size_t nextValue = 0;
    for (std::size_t slot = 0; slot < result.size(); slot += kStride) {
        result[slot] = sortedValues[nextValue++];
    }
    return result;
}