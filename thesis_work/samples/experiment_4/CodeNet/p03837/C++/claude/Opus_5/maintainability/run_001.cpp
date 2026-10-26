#include <climits>
#include <iostream>
#include <vector>

namespace {

constexpr long long kUnreachable = LLONG_MAX / 4;

void runFloydWarshall(std::vector<std::vector<long long>>& shortest, int townCount) {
    for (int waypoint = 1; waypoint <= townCount; ++waypoint) {
        for (int origin = 1; origin <= townCount; ++origin) {
            const long long toWaypoint = shortest[origin][waypoint];
            if (toWaypoint >= kUnreachable) {
                continue;
            }
            for (int destination = 1; destination <= townCount; ++destination) {
                const long long detour = toWaypoint + shortest[waypoint][destination];
                if (detour < shortest[origin][destination]) {
                    shortest[origin][destination] = detour;
                }
            }
        }
    }
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int townCount, roadCount;
    std::cin >> townCount >> roadCount;

    std::vector<std::vector<long long>> shortest(
        townCount + 1, std::vector<long long>(townCount + 1, kUnreachable));
    for (int town = 1; town <= townCount; ++town) {
        shortest[town][town] = 0;
    }

    std::vector<int> firstTown(roadCount), secondTown(roadCount);
    std::vector<long long> roadLength(roadCount);

    for (int road = 0; road < roadCount; ++road) {
        std::cin >> firstTown[road] >> secondTown[road] >> roadLength[road];

        // Parallel roads may exist; keep only the shortest as the direct link.
        if (roadLength[road] < shortest[firstTown[road]][secondTown[road]]) {
            shortest[firstTown[road]][secondTown[road]] = roadLength[road];
            shortest[secondTown[road]][firstTown[road]] = roadLength[road];
        }
    }

    runFloydWarshall(shortest, townCount);

    // A road is redundant exactly when some detour beats travelling along it.
    int redundant = 0;
    for (int road = 0; road < roadCount; ++road) {
        if (shortest[firstTown[road]][secondTown[road]] < roadLength[road]) {
            ++redundant;
        }
    }

    std::cout << redundant << '\n';
    return 0;
}