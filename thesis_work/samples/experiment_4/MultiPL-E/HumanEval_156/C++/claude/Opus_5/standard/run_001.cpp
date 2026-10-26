#include<assert.h>
#include<bits/stdc++.h>
// Given a positive integer, obtain its roman numeral equivalent as a string,
// and return it in lowercase.
// Restrictions: 1 <= num <= 1000
// Examples:
// >>> int_to_mini_roman((19))
// ("xix")
// >>> int_to_mini_roman((152))
// ("clii")
// >>> int_to_mini_roman((426))
// ("cdxxvi")
std::string int_to_mini_roman(long number) {
    std::vector<long> vals = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    std::vector<std::string> syms = {"m","cm","d","cd","c","xc","l","xl","x","ix","v","iv","i"};
    std::string res;
    long n = number;
    for (size_t i = 0; i < vals.size(); i++) {
        while (n >= vals[i]) {
            res += syms[i];
            n -= vals[i];
        }
    }
    return res;
}

int main() {
    std::cout << int_to_mini_roman(19) << std::endl;
    std::cout << int_to_mini_roman(152) << std::endl;
    std::cout << int_to_mini_roman(426) << std::endl;
    return 0;
}