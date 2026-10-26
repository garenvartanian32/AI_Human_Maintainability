#include<assert.h>
#include<bits/stdc++.h>
// You are given a vector of two strings, both strings consist of open
// parentheses '(' or close parentheses ')' only.
// Your job is to check if it is possible to concatenate the two strings in
// some order, that the resulting string will be good.
// A string S is considered to be good if and only if all parentheses in S
// are balanced. For example: the string '(())()' is good, while the string
// '())' is not.
// Return 'Yes' if there's a way to make a good string, and return 'No' otherwise.
// Examples:
// >>> match_parens((std::vector<std::string>({(std::string)"()(", (std::string)")"})))
// ("Yes")
// >>> match_parens((std::vector<std::string>({(std::string)")", (std::string)")"})))
// ("No")
std::string match_parens(std::vector<std::string> lst) {

    auto check = [](const std::string &s) {
        int depth = 0;
        for (char c : s) {
            depth += (c == '(') ? 1 : -1;
            if (depth < 0) return false;
        }
        return depth == 0;
    };
    std::string a = lst[0], b = lst[1];
    if (check(a+b) || check(b+a)) return "Yes";
    return "No";
}