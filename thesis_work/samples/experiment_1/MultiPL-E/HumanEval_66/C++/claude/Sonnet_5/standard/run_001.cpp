#include<assert.h>
#include<bits/stdc++.h>
// Task
// Write a function that takes a string as input and returns the sum of the upper characters only'
// ASCII codes.
// Examples:
// >>> digitSum((""))
// (0)
// >>> digitSum(("abAB"))
// (131)
// >>> digitSum(("abcCd"))
// (67)
// >>> digitSum(("helloE"))
// (69)
// >>> digitSum(("woArBld"))
// (131)
// >>> digitSum(("aAaaaXa"))
// (153)
long digitSum(std::string s) {

    long sum = 0;
    for (char c : s) if (isupper(c)) sum += (long) c;
    return sum;
}