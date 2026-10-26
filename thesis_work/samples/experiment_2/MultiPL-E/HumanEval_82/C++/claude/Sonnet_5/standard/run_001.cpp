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
    int n = string.length();
    if (n < 2) return false;
    for (int i = 2; (long)i * i <= n; i++) {
        if (n % i == 0) return false;
    }
    return true;
}

int main() {
    std::cout << std::boolalpha;
    std::cout << prime_length("Hello") << std::endl;
    std::cout << prime_length("abcdcba") << std::endl;
    std::cout << prime_length("kittens") << std::endl;
    std::cout << prime_length("orange") << std::endl;
}