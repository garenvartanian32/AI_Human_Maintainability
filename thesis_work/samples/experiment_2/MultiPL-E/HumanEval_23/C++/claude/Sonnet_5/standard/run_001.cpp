#include<assert.h>
#include<bits/stdc++.h>
// Return length of given string
// >>> string_length((""))
// (0)
// >>> string_length(("abc"))
// (3)
long string_length(std::string string) {
    return string.length();
}

int main() {
    std::cout << string_length("") << std::endl;
    std::cout << string_length("abc") << std::endl;
}