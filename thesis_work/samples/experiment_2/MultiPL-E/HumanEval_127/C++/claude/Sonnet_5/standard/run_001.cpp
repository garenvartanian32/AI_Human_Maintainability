#include<assert.h>
#include<bits/stdc++.h>
// You are given two intervals,
// where each interval is a pair of integers. For example, interval = (start, end) = (1, 2).
// The given intervals are closed which means that the interval (start, end)
// includes both start and end.
// For each given interval, it is assumed that its start is less or equal its end.
// Your task is to determine whether the length of intersection of these two 
// intervals is a prime number.
// Example, the intersection of the intervals (1, 3), (2, 4) is (2, 3)
// which its length is 1, which not a prime number.
// If the length of the intersection is a prime number, return "YES",
// otherwise, return "NO".
// If the two intervals don't intersect, return "NO".
// [input/output] samples:
// >>> intersection((std::make_tuple(1, 2)), (std::make_tuple(2, 3)))
// ("NO")
// >>> intersection((std::make_tuple(-1, 1)), (std::make_tuple(0, 4)))
// ("NO")
// >>> intersection((std::make_tuple(-3, -1)), (std::make_tuple(-5, 5)))
// ("YES")
std::string intersection(std::tuple<long, long> interval1, std::tuple<long, long> interval2) {
    long lo = std::max(std::get<0>(interval1), std::get<0>(interval2));
    long hi = std::min(std::get<1>(interval1), std::get<1>(interval2));
    long length = hi - lo;
    if (length < 2) return "NO";
    for (long i = 2; i * i <= length; i++) {
        if (length % i == 0) return "NO";
    }
    return "YES";
}

int main() {
    std::cout << intersection(std::make_tuple(1,2), std::make_tuple(2,3)) << std::endl;
    std::cout << intersection(std::make_tuple(-1,1), std::make_tuple(0,4)) << std::endl;
    std::cout << intersection(std::make_tuple(-3,-1), std::make_tuple(-5,5)) << std::endl;
    return 0;
}