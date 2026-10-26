#include <assert.h>
#include <bits/stdc++.h>

std::tuple<long, long> sum_product(std::vector<long> numbers) {
    long total_sum = 0;
    long total_product = 1;

    for (long number : numbers) {
        total_sum += number;
        total_product *= number;
    }

    return std::make_tuple(total_sum, total_product);
}