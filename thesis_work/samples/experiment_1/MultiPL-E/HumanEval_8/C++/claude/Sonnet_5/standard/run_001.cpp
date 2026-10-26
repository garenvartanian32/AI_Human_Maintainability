#include<assert.h>
#include<bits/stdc++.h>
// For a given vector of integers, return a tuple consisting of a sum and a product of all the integers in a vector.
// Empty sum should be equal to 0 and empty product should be equal to 1.
// >>> sum_product((std::vector<long>()))
// (std::make_tuple(0, 1))
// >>> sum_product((std::vector<long>({(long)1, (long)2, (long)3, (long)4})))
// (std::make_tuple(10, 24))
std::tuple<long, long> sum_product(std::vector<long> numbers) {

    long sum = 0, prod = 1;
    for (long n : numbers) { sum += n; prod *= n; }
    return std::make_tuple(sum, prod);
}