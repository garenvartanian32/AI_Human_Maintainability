"""Simulate typing on a keyboard where 'i' reverses the text written so far."""

from collections import deque


REVERSE_KEY = "i"


class Solution:
    def finalString(self, s: str) -> str:
        """Return the text shown on screen after typing every character of ``s``.

        Rather than reversing the buffer on each ``REVERSE_KEY`` (which is
        quadratic), the buffer is kept in a deque and a flag tracks whether it
        is currently stored forwards or backwards. New characters are appended
        to whichever end represents "the back" at that moment.
        """
        characters = deque()
        stored_forwards = True

        for character in s:
            if character == REVERSE_KEY:
                stored_forwards = not stored_forwards
            elif stored_forwards:
                characters.append(character)
            else:
                characters.appendleft(character)

        if not stored_forwards:
            characters.reverse()

        return "".join(characters)