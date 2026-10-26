#include<assert.h>
#include<bits/stdc++.h>
// Concatenate vector of strings into a single string
// >>> concatenate((std::vector<std::string>()))
// ("")
// >>> concatenate((std::vector<std::string>({(std::string)"a", (std::string)"b", (std::string)"c"})))
// ("abc")
std::string concatenate(std::vector<std::string> strings) {
    std::string result;
    for (auto &s : strings) result += s;
    return result;
}

int main() {
    std::cout << "[" << concatenate({}) << "]" << std::endl;
    std::cout << concatenate({"a","b","c"}) << std::endl;
}