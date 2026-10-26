#include <deque>
#include <string>

/**
 * Simulates typing on a keyboard where the letter 'i' reverses the text so far.
 */
class Solution {
public:
    /**
     * Returns the text shown on screen after typing every character of `s`.
     *
     * Rather than reversing the buffer on each `kReverseKey` (which is
     * quadratic), the buffer is kept in a deque and a flag tracks whether it is
     * currently stored forwards or backwards.
     */
    std::string finalString(std::string s) {
        std::deque<char> characters;
        bool storedForwards = true;

        for (char character : s) {
            if (character == kReverseKey) {
                storedForwards = !storedForwards;
            } else if (storedForwards) {
                characters.push_back(character);
            } else {
                characters.push_front(character);
            }
        }

        return joinInOrder(characters, storedForwards);
    }

private:
    static constexpr char kReverseKey = 'i';

    /// Concatenates the deque, walking backwards when the buffer is reversed.
    static std::string joinInOrder(const std::deque<char>& characters, bool storedForwards) {
        std::string result;
        result.reserve(characters.size());
        if (storedForwards) {
            result.assign(characters.begin(), characters.end());
        } else {
            result.assign(characters.rbegin(), characters.rend());
        }
        return result;
    }
};