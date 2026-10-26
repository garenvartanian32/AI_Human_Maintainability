"""Decode a message enciphered with a repeating sequence of shift keys."""

import sys

ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
ALPHABET_INDEX = {letter: position for position, letter in enumerate(ALPHABET)}
END_OF_INPUT = 0


def decode(ciphertext: str, keys: list[int]) -> str:
    """Undo the encryption by shifting each letter back by its key.

    Lower and upper case form one continuous 52-letter ring, so a shift can
    carry a letter from one case into the other.
    """
    plaintext = []
    for position, letter in enumerate(ciphertext):
        shift = keys[position % len(keys)]
        original = (ALPHABET_INDEX[letter] - shift) % len(ALPHABET)
        plaintext.append(ALPHABET[original])
    return "".join(plaintext)


def main() -> None:
    lines = sys.stdin.read().split("\n")
    cursor = 0
    messages = []

    while cursor < len(lines):
        header = lines[cursor].strip()
        cursor += 1
        if not header:
            continue

        key_count = int(header)
        if key_count == END_OF_INPUT:
            break

        keys = [int(token) for token in lines[cursor].split()]
        cursor += 1
        ciphertext = lines[cursor].strip()
        cursor += 1

        messages.append(decode(ciphertext, keys))

    sys.stdout.write("\n".join(messages) + "\n")


if __name__ == "__main__":
    main()