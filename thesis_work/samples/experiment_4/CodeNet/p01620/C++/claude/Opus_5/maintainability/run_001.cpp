#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace {

const std::string kAlphabet =
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
constexpr int kEndOfInput = 0;

/**
 * Undoes the encryption by shifting each letter back by its key.
 *
 * Lower and upper case form one continuous 52-letter ring, so a shift can
 * carry a letter from one case into the other.
 */
std::string decode(const std::string& ciphertext, const std::vector<int>& keys) {
    const int ringSize = static_cast<int>(kAlphabet.size());

    std::string plaintext;
    plaintext.reserve(ciphertext.size());
    for (std::size_t position = 0; position < ciphertext.size(); ++position) {
        const int shift = keys[position % keys.size()];
        const int enciphered = static_cast<int>(kAlphabet.find(ciphertext[position]));
        const int original = ((enciphered - shift) % ringSize + ringSize) % ringSize;
        plaintext += kAlphabet[original];
    }
    return plaintext;
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    std::ostringstream messages;
    int keyCount;

    while (std::cin >> keyCount) {
        if (keyCount == kEndOfInput) {
            break;
        }

        std::vector<int> keys(keyCount);
        for (int& key : keys) {
            std::cin >> key;
        }

        std::string ciphertext;
        std::cin >> ciphertext;

        messages << decode(ciphertext, keys) << '\n';
    }

    std::cout << messages.str();
    return 0;
}