#include <iostream>
#include <sstream>
#include <string>

namespace {

constexpr char kRaiseSuffix = 'u';
constexpr int kEndOfInput = 0;

// Where the pair of feet currently is.
enum FootPosition { kOnFloor, kRising, kOnStep, kFalling };

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    std::ostringstream answers;
    int movementCount;

    while (std::cin >> movementCount) {
        if (movementCount == kEndOfInput) {
            break;
        }

        FootPosition position = kOnFloor;
        int completed = 0;

        for (int i = 0; i < movementCount; ++i) {
            std::string movement;
            std::cin >> movement;
            const bool isRaise = movement[1] == kRaiseSuffix;

            switch (position) {
                case kOnFloor:
                    position = kRising;
                    break;
                case kRising:
                    if (isRaise) {
                        position = kOnStep;
                        ++completed;
                    } else {
                        position = kOnFloor;
                    }
                    break;
                case kOnStep:
                    position = kFalling;
                    break;
                case kFalling:
                    if (isRaise) {
                        position = kOnStep;
                    } else {
                        position = kOnFloor;
                        ++completed;
                    }
                    break;
            }
        }

        answers << completed << '\n';
    }

    std::cout << answers.str();
    return 0;
}