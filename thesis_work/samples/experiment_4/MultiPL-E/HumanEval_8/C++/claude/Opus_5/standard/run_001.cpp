#include <assert.h>
#include <bits/stdc++.h>

std::tuple<long, long> sum_product(std::vector<long> numbers) {
    long sum = 0;
    long product = 1;
    for (long number : numbers) {
        sum += number;
        product *= number;
    }
    return std::make_tuple(sum, product);
}