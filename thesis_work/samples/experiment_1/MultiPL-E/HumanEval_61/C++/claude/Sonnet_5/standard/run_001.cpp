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

    int depth = 0;
    for (char c : brackets) {
        depth += (c == '(') ? 1 : -1;
        if (depth < 0) return false;
    }
    return depth == 0;
}