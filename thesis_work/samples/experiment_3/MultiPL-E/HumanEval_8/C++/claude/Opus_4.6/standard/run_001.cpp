#include<assert.h>
#include<bits/stdc++.h>
// For a given vector of integers, return a tuple consisting of a sum and a product of all the integers in a vector.
// Empty sum should be equal to 0 and empty product should be equal to 1.
// >>> sum_product((std::vector<long>()))
// (std::make_tuple(0, 1))
// >>> sum_product((std::vector<long>({(long)1, (long)2, (long)3, (long)4})))
// (std::make_tuple(10, 24))
std::tuple<long, long> sum_product(std::vector<long> numbers) {
    long sum = 0, product = 1;
    for (long x : numbers) {
        sum += x;
        product *= x;
    }
    return std::make_tuple(sum, product);
}

int main() {
    auto r1 = sum_product({});
    std::cout << std::get<0>(r1) << " " << std::get<1>(r1) << std::endl;
    auto r2 = sum_product({1,2,3,4});
    std::cout << std::get<0>(r2) << " " << std::get<1>(r2) << std::endl;
}