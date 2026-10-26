#include<assert.h>
#include<bits/stdc++.h>
// Concatenate vector of strings into a single string
// >>> concatenate((std::vector<std::string>()))
// ("")
// >>> concatenate((std::vector<std::string>({(std::string)"a", (std::string)"b", (std::string)"c"})))
// ("abc")
std::string concatenate(std::vector<std::string> strings) {
    // Reserving up front avoids repeated reallocation while appending, and an
    // empty vector naturally yields an empty string.
    std::size_t totalLength = 0;
    for (const std::string& piece : strings) {
        totalLength += piece.size();
    }

    std::string joined;
    joined.reserve(totalLength);
    for (const std::string& piece : strings) {
        joined += piece;
    }
    return joined;
}