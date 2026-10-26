#include<assert.h>
#include<bits/stdc++.h>
// Write a function that takes a string and returns true if the string
// length is a prime number or false otherwise
// Examples
// >>> prime_length(("Hello"))
// (true)
// >>> prime_length(("abcdcba"))
// (true)
// >>> prime_length(("kittens"))
// (true)
// >>> prime_length(("orange"))
// (false)
bool prime_length(std::string string) {

    size_t n = string.size();
    if (n < 2) return false;
    for (size_t i = 2; i*i <= n; i++) if (n % i == 0) return false;
    return true;
}