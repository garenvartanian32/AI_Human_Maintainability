#include <algorithm>
#include <iostream>
#include <utility>
#include <vector>

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    long long shopCount, drinksWanted;
    std::cin >> shopCount >> drinksWanted;

    // Each shop is (price, stock); sorting by price puts the cheapest first.
    std::vector<std::pair<long long, long long>> shops(shopCount);
    for (auto& shop : shops) {
        std::cin >> shop.first >> shop.second;
    }
    std::sort(shops.begin(), shops.end());

    // Drinks are interchangeable, so exhausting the cheapest shop before
    // moving on is optimal.
    long long totalCost = 0;
    long long stillNeeded = drinksWanted;
    for (const auto& shop : shops) {
        if (stillNeeded == 0) {
            break;
        }
        const long long bought = std::min(shop.second, stillNeeded);
        totalCost += bought * shop.first;
        stillNeeded -= bought;
    }

    std::cout << totalCost << '\n';
    return 0;
}