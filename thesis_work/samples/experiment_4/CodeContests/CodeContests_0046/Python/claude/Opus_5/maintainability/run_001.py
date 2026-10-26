"""Assign each student to the nearest checkpoint by Manhattan distance."""

import sys


def manhattan_distance(first: tuple[int, int], second: tuple[int, int]) -> int:
    return abs(first[0] - second[0]) + abs(first[1] - second[1])


def nearest_checkpoint_number(
    student: tuple[int, int], checkpoints: list[tuple[int, int]]
) -> int:
    """Return the 1-based index of the closest checkpoint.

    Ties are resolved in favour of the smallest index, which falls out of
    scanning left to right and only replacing on a strict improvement.
    """
    best_number = 1
    best_distance = manhattan_distance(student, checkpoints[0])

    for number, checkpoint in enumerate(checkpoints[1:], start=2):
        distance = manhattan_distance(student, checkpoint)
        if distance < best_distance:
            best_distance = distance
            best_number = number

    return best_number


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    student_count = int(tokens[0])
    checkpoint_count = int(tokens[1])

    cursor = 2
    students = []
    for _ in range(student_count):
        students.append((int(tokens[cursor]), int(tokens[cursor + 1])))
        cursor += 2

    checkpoints = []
    for _ in range(checkpoint_count):
        checkpoints.append((int(tokens[cursor]), int(tokens[cursor + 1])))
        cursor += 2

    answers = [nearest_checkpoint_number(student, checkpoints) for student in students]
    sys.stdout.write("\n".join(map(str, answers)) + "\n")


if __name__ == "__main__":
    main()