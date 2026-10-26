#include<assert.h>
#include<bits/stdc++.h>
// Checks if given string is a palindrome
// >>> is_palindrome((""))
// (true)
// >>> is_palindrome(("aba"))
// (true)
// >>> is_palindrome(("aaaaa"))
// (true)
// >>> is_palindrome(("zbcd"))
// (false)
bool is_palindrome(std::string text) {

    std::string rev(text.rbegin(), text.rend());
    return text == rev;
}