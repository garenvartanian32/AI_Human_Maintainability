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

/// True when every closing bracket is matched by an earlier opening one.
static bool isBalanced(const std::string& text) {
    int depth = 0;
    for (char character : text) {
        depth += (character == '(') ? 1 : -1;
        if (depth < 0) {
            return false;
        }
    }
    return depth == 0;
}

std::string match_parens(std::vector<std::string> lst) {
    const std::string& first = lst[0];
    const std::string& second = lst[1];

    // Only two orderings exist, so testing both settles the question.
    const bool anyOrderWorks = isBalanced(first + second) || isBalanced(second + first);
    return anyOrderWorks ? "Yes" : "No";
}