"""Reconstruct a train's carriage order from a conductor's walk record."""

import sys

BACKWARD_MARKER = "->"


def rebuild_train(record: str) -> str:
    """Return the carriages from the front, given one walk record.

    The record is a chain of moves; each move links the current carriage to the
    next one either behind it (``->``) or in front of it. Recording both links
    builds a doubly linked list, and the front carriage is the only one with
    nothing ahead of it.
    """
    carriage_behind: dict[str, str] = {}
    carriage_ahead: dict[str, str] = {}
    seen: list[str] = []

    current = record[0]
    seen.append(current)

    position = 1
    while position < len(record):
        moved_backward = record[position : position + len(BACKWARD_MARKER)] == BACKWARD_MARKER
        if moved_backward:
            position += len(BACKWARD_MARKER)

        next_carriage = record[position]
        position += 1
        if next_carriage not in seen:
            seen.append(next_carriage)

        if moved_backward:
            carriage_behind[current] = next_carriage
            carriage_ahead[next_carriage] = current
        else:
            carriage_ahead[current] = next_carriage
            carriage_behind[next_carriage] = current

        current = next_carriage

    front = next(carriage for carriage in seen if carriage not in carriage_ahead)

    order = []
    carriage = front
    while carriage is not None:
        order.append(carriage)
        carriage = carriage_behind.get(carriage)
    return "".join(order)


def main() -> None:
    lines = sys.stdin.read().split("\n")
    record_count = int(lines[0].strip())

    trains = [rebuild_train(lines[index].strip()) for index in range(1, record_count + 1)]
    sys.stdout.write("\n".join(trains) + "\n")


if __name__ == "__main__":
    main()