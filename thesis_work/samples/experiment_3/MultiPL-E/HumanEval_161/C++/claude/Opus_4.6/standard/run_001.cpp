#include<assert.h>
#include<bits/stdc++.h>
// You are given a string s.
// if s[i] is a letter, reverse its case from lower to upper or vise versa, 
// otherwise keep it as it is.
// If the string contains no letters, reverse the string.
// The function should return the resulted string.
// Examples
// >>> solve(("1234"))
// ("4321")
// >>> solve(("ab"))
// ("AB")
// >>> solve(("#a@C"))
// ("#A@c")
std::string solve(std::string s) {
    bool hasLetter = false;
    for (char c : s) {
        if (isalpha((unsigned char)c)) { hasLetter = true; break; }
    }
    if (hasLetter) {
        std::string res;
        for (char c : s) {
            if (isupper((unsigned char)c)) res += tolower(c);
            else if (islower((unsigned char)c)) res += toupper(c);
            else res += c;
        }
        return res;
    } else {
        std::reverse(s.begin(), s.end());
        return s;
    }
}

int main() {
    std::cout << solve("1234") << std::endl;
    std::cout << solve("ab") << std::endl;
    std::cout << solve("#a@C") << std::endl;
    return 0;
}