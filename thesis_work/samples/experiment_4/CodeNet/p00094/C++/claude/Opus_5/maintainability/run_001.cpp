#include <cstdio>
#include <iostream>

namespace {

constexpr double kSquareMetresPerTsubo = 3.305785;

}  // namespace

int main() {
    double width, depth;
    std::cin >> width >> depth;

    const double areaInTsubo = (width * depth) / kSquareMetresPerTsubo;
    std::printf("%.6f\n", areaInTsubo);
    return 0;
}