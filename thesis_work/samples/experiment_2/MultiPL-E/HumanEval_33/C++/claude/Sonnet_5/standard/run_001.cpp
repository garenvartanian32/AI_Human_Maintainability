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
    std::vector<int> indices;
    std::vector<long> values;
    for (size_t i = 0; i < l.size(); i++) {
        if (i % 3 == 0) {
            indices.push_back(i);
            values.push_back(l[i]);
        }
    }
    std::sort(values.begin(), values.end());
    std::vector<long> result = l;
    for (size_t k = 0; k < indices.size(); k++) {
        result[indices[k]] = values[k];
    }
    return result;
}

int main() {
    for (long v : sort_third({1,2,3})) std::cout << v << " ";
    std::cout << std::endl;
    for (long v : sort_third({5,6,3,4,8,9,2})) std::cout << v << " ";
    std::cout << std::endl;
}