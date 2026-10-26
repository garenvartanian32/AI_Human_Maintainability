#include <assert.h>
#include <bits/stdc++.h>

long search(std::vector<long> lst) {
    std::unordered_map<long, long> frequencies;

    for (long value : lst) {
        ++frequencies[value];
    }

    long greatest_valid_value = -1;

    for (const auto& entry : frequencies) {
        const long value = entry.first;
        const long frequency = entry.second;

        if (frequency >= value && value > greatest_valid_value) {
            greatest_valid_value = value;
        }
    }

    return greatest_valid_value;
}