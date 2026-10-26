#include <algorithm>
#include <iostream>
#include <utility>
#include <vector>

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    long long monsterCount, blastRadius, blastDamage;
    std::cin >> monsterCount >> blastRadius >> blastDamage;

    std::vector<std::pair<long long, long long>> monsters(monsterCount);
    for (auto& monster : monsters) {
        std::cin >> monster.first >> monster.second;
    }
    std::sort(monsters.begin(), monsters.end());

    std::vector<long long> positions(monsterCount);
    for (long long i = 0; i < monsterCount; ++i) {
        positions[i] = monsters[i].first;
    }

    // Sweeping left to right, every monster must die at its own turn, so bombs
    // are always centred as far right as still covers it. `expiring` records
    // where each blast stops contributing, letting us carry a running total.
    std::vector<long long> expiring(monsterCount + 1, 0);
    long long damageInEffect = 0;
    long long bombsUsed = 0;

    for (long long index = 0; index < monsterCount; ++index) {
        damageInEffect -= expiring[index];

        const long long remainingHealth = monsters[index].second - damageInEffect;
        if (remainingHealth <= 0) {
            continue;
        }

        const long long bombs = (remainingHealth + blastDamage - 1) / blastDamage;
        bombsUsed += bombs;
        damageInEffect += bombs * blastDamage;

        const long long lastCovered =
            std::upper_bound(positions.begin(), positions.end(),
                             monsters[index].first + 2 * blastRadius)
            - positions.begin();
        if (lastCovered <= monsterCount) {
            expiring[lastCovered] += bombs * blastDamage;
        }
    }

    std::cout << bombsUsed << '\n';
    return 0;
}