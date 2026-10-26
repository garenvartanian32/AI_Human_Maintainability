#include <assert.h>
#include <bits/stdc++.h>

long search(std::vector<long> lst) {
    std::unordered_map<long, long> counts;
    for (long value : lst) ++counts[value];

    long result = -1;
    for (const auto& [value, frequency] : counts) {
        if (frequency >= value) result = std::max(result, value);
    }
    return result;
}