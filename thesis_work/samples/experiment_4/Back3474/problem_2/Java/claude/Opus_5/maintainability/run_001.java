import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Simulates typing on a keyboard where the letter 'i' reverses the text so far.
 */
class Solution {

    private static final char REVERSE_KEY = 'i';

    /**
     * Returns the text shown on screen after typing every character of {@code s}.
     *
     * <p>Rather than reversing the buffer on each {@code REVERSE_KEY} (which is
     * quadratic), the buffer is kept in a deque and a flag tracks whether it is
     * currently stored forwards or backwards.
     */
    public String finalString(String s) {
        Deque<Character> characters = new ArrayDeque<>();
        boolean storedForwards = true;

        for (char character : s.toCharArray()) {
            if (character == REVERSE_KEY) {
                storedForwards = !storedForwards;
            } else if (storedForwards) {
                characters.addLast(character);
            } else {
                characters.addFirst(character);
            }
        }

        return joinInOrder(characters, storedForwards);
    }

    /** Concatenates the deque, walking backwards when the buffer is reversed. */
    private String joinInOrder(Deque<Character> characters, boolean storedForwards) {
        StringBuilder builder = new StringBuilder(characters.size());
        Iterator<Character> walker =
                storedForwards ? characters.iterator() : characters.descendingIterator();
        while (walker.hasNext()) {
            builder.append(walker.next());
        }
        return builder.toString();
    }
}