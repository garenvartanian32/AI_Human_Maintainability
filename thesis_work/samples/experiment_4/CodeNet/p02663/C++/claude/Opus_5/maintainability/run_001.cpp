#include <algorithm>
#include <iostream>

namespace {

constexpr int kMinutesPerHour = 60;

int toMinutes(int hour, int minute) {
    return hour * kMinutesPerHour + minute;
}

}  // namespace

int main() {
    int wakeHour, wakeMinute, sleepHour, sleepMinute, studyLength;
    std::cin >> wakeHour >> wakeMinute >> sleepHour >> sleepMinute >> studyLength;

    const int awakeMinutes =
        toMinutes(sleepHour, sleepMinute) - toMinutes(wakeHour, wakeMinute);
    std::cout << std::max(0, awakeMinutes - studyLength) << '\n';
    return 0;
}