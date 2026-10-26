"""A toy memory manager supporting alloc, erase and defragment."""

import sys

FREE = 0
NULL_RESULT = "NULL"
ILLEGAL_ERASE = "ILLEGAL_ERASE_ARGUMENT"


class MemoryManager:
    """Fixed-size memory modelled as a list of owning block identifiers."""

    def __init__(self, size: int) -> None:
        self._cells = [FREE] * size
        self._live_blocks: set[int] = set()
        self._next_identifier = 1

    def allocate(self, cells_needed: int) -> str:
        """Reserve the first run of free cells that is long enough."""
        start = self._find_free_run(cells_needed)
        if start is None:
            return NULL_RESULT

        identifier = self._next_identifier
        self._next_identifier += 1

        for cell in range(start, start + cells_needed):
            self._cells[cell] = identifier
        self._live_blocks.add(identifier)

        return str(identifier)

    def erase(self, identifier: int) -> str | None:
        """Free a live block; returns an error string for unknown blocks."""
        if identifier not in self._live_blocks:
            return ILLEGAL_ERASE

        self._live_blocks.discard(identifier)
        for cell, owner in enumerate(self._cells):
            if owner == identifier:
                self._cells[cell] = FREE
        return None

    def defragment(self) -> None:
        """Slide every allocated block towards the start, preserving order."""
        occupied = [owner for owner in self._cells if owner != FREE]
        self._cells = occupied + [FREE] * (len(self._cells) - len(occupied))

    def _find_free_run(self, cells_needed: int) -> int | None:
        run_length = 0
        for cell, owner in enumerate(self._cells):
            run_length = run_length + 1 if owner == FREE else 0
            if run_length == cells_needed:
                return cell - cells_needed + 1
        return None


def main() -> None:
    lines = sys.stdin.read().split("\n")
    operation_count, memory_size = (int(value) for value in lines[0].split())

    manager = MemoryManager(memory_size)
    output: list[str] = []

    for index in range(1, operation_count + 1):
        parts = lines[index].split()
        if not parts:
            continue

        command = parts[0]
        if command == "alloc":
            output.append(manager.allocate(int(parts[1])))
        elif command == "erase":
            error = manager.erase(int(parts[1]))
            if error is not None:
                output.append(error)
        elif command == "defragment":
            manager.defragment()

    sys.stdout.write("\n".join(output) + ("\n" if output else ""))


if __name__ == "__main__":
    main()