#include <iostream>
#include <unordered_set>

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int buttonCount, bulbCount;
    std::cin >> buttonCount >> bulbCount;

    // Pressing every button is always allowed, so a bulb can be switched off
    // exactly when at least one button controls it.
    std::unordered_set<int> controlledBulbs;

    for (int button = 0; button < buttonCount; ++button) {
        int bulbsOnThisButton;
        std::cin >> bulbsOnThisButton;
        for (int i = 0; i < bulbsOnThisButton; ++i) {
            int bulb;
            std::cin >> bulb;
            controlledBulbs.insert(bulb);
        }
    }

    const bool everyBulbIsCovered =
        static_cast<int>(controlledBulbs.size()) == bulbCount;
    std::cout << (everyBulbIsCovered ? "YES" : "NO") << '\n';
    return 0;
}