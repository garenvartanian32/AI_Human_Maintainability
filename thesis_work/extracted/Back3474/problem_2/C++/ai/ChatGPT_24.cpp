class Solution {
public:
    std::string finalString(std::string s) {
        std::string result = ""; // Initialize the result string
        
        for (char c : s) {
            if (c == 'i') {
                // If the character is 'i', reverse the result string
                std::reverse(result.begin(), result.end());
            } else {
                // Otherwise, append the character to the result string
                result += c;
            }
        }
        
        return result;
    }
};