#include <iostream>
#include <sstream>
#include <string>
int main() {
    std::ostringstream ss;
    ss << std::cin.rdbuf();
    std::string data = ss.str();
    return 0;
}