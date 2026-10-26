"""Minutes left for study between waking and bedtime."""

import sys

MINUTES_PER_HOUR = 60


def to_minutes(hour: int, minute: int) -> int:
    return hour * MINUTES_PER_HOUR + minute


def main() -> None:
    wake_hour, wake_minute, sleep_hour, sleep_minute, study_length = map(
        int, sys.stdin.read().split()
    )

    awake_minutes = to_minutes(sleep_hour, sleep_minute) - to_minutes(wake_hour, wake_minute)
    print(max(0, awake_minutes - study_length))


if __name__ == "__main__":
    main()