"""Capitalise every letter whose alphabet position is at most k."""

import sys

ALPHABET_START = ord("a")


def capitalise_low_ranked(text: str, threshold: int) -> str:
    """Upper-case letters ranked 1..threshold; lower-case everything else.

    The original casing carries no information, so each character is first
    normalised to lower case and then re-cased purely from its rank.
    """
    result = []
    for character in text:
        lowered = character.lower()
        rank = ord(lowered) - ALPHABET_START + 1
        result.append(lowered.upper() if rank <= threshold else lowered)
    return "".join(result)


def main() -> None:
    lines = sys.stdin.read().split("\n")
    text = lines[0].rstrip("\r")
    threshold = int(lines[1].strip())

    print(capitalise_low_ranked(text, threshold))


if __name__ == "__main__":
    main()