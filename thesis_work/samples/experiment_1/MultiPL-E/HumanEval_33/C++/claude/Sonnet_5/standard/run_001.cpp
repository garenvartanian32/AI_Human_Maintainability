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

    using namespace std;
    vector<long> result = l;
    vector<long> vals;
    for (size_t i = 0; i < l.size(); i += 3) vals.push_back(l[i]);
    sort(vals.begin(), vals.end());
    size_t idx = 0;
    for (size_t i = 0; i < l.size(); i += 3) result[i] = vals[idx++];
    return result;
}