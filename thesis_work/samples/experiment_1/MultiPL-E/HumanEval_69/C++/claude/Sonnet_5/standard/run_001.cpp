#include<assert.h>
#include<bits/stdc++.h>
// You are given a non-empty vector of positive integers. Return the greatest integer that is greater than 
// zero, and has a frequency greater than or equal to the value of the integer itself. 
// The frequency of an integer is the number of times it appears in the vector.
// If no such a value exist, return -1.
// Examples:
// >>> search((std::vector<long>({(long)4, (long)1, (long)2, (long)2, (long)3, (long)1})))
// (2)
// >>> search((std::vector<long>({(long)1, (long)2, (long)2, (long)3, (long)3, (long)3, (long)4, (long)4, (long)4})))
// (3)
// >>> search((std::vector<long>({(long)5, (long)5, (long)4, (long)4, (long)4})))
// (-1)
long search(std::vector<long> lst) {

    using namespace std;
    map<long,long> freq;
    for (long x : lst) freq[x]++;
    long best = -1;
    for (auto &p : freq) {
        if (p.second >= p.first && p.first > best) best = p.first;
    }
    return best;
}