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
    const bool containsLetter = std::any_of(
        s.begin(), s.end(),
        [](unsigned char character) { return std::isalpha(character) != 0; });

    // With no letters to flip there is nothing for the case rule to do, so the
    // specification falls back to reversing the whole string instead.
    if (!containsLetter) {
        std::reverse(s.begin(), s.end());
        return s;
    }

    // Flip the case of every letter, leaving other characters untouched.
    for (char& character : s) {
        const unsigned char raw = static_cast<unsigned char>(character);
        if (std::isupper(raw)) {
            character = static_cast<char>(std::tolower(raw));
        } else if (std::islower(raw)) {
            character = static_cast<char>(std::toupper(raw));
        }
    }
    return s;
}