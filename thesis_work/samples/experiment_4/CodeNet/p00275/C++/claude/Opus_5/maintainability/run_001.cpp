#include <algorithm>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace {

constexpr int kEndOfInput = 0;
constexpr char kMonk = 'S';      // sends the player's hand to the field
constexpr char kPrincess = 'L';  // sweeps the field into the hand

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    std::ostringstream output;
    int playerCount;

    while (std::cin >> playerCount) {
        if (playerCount == kEndOfInput) {
            break;
        }

        std::string cards;
        std::cin >> cards;

        std::vector<int> hands(playerCount, 0);
        int field = 0;

        for (std::size_t turn = 0; turn < cards.size(); ++turn) {
            const int player = static_cast<int>(turn) % playerCount;
            const char card = cards[turn];

            if (card == kMonk) {
                field += hands[player] + 1;
                hands[player] = 0;
            } else if (card == kPrincess) {
                hands[player] += field + 1;
                field = 0;
            } else {
                hands[player]++;
            }
        }

        std::sort(hands.begin(), hands.end());
        for (int hand : hands) {
            output << hand << ' ';
        }
        output << field << '\n';
    }

    std::cout << output.str();
    return 0;
}