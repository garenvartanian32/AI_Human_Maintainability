#include<assert.h>
#include<bits/stdc++.h>
// brackets is a string of "(" and ")".
// return true if every opening bracket has a corresponding closing bracket.
// >>> correct_bracketing(("("))
// (false)
// >>> correct_bracketing(("()"))
// (true)
// >>> correct_bracketing(("(()())"))
// (true)
// >>> correct_bracketing((")(()"))
// (false)
bool correct_bracketing(std::string brackets) {
    int bal = 0;
    for (char c : brackets) {
        bal += (c == '(') ? 1 : -1;
        if (bal < 0) return false;
    }
    return bal == 0;
}

int main() {
    std::cout << std::boolalpha;
    std::cout << correct_bracketing("(") << std::endl;
    std::cout << correct_bracketing("()") << std::endl;
    std::cout << correct_bracketing("(()())") << std::endl;
    std::cout << correct_bracketing(")(()") << std::endl;
}