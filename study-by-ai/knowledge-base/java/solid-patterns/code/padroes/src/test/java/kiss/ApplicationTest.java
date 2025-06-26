package kiss;


import org.junit.jupiter.api.Test;

import static kiss.Application.reverseOne;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {

    @Test
    void testReverseNormalString() {
        assertEquals("AUGA", reverseOne("AGUA"));
        assertEquals("4321", reverseOne("1234"));
        assertEquals("cba", reverseOne("abc"));
        assertEquals("!dlroW ,olleH", reverseOne("Hello, World!"));
    }

    @Test
    void testReverseEmptyString() {
        assertEquals("", reverseOne(""));
    }

    @Test
    void testReverseSingleCharacter() {
        assertEquals("A", reverseOne("A"));
        assertEquals("1", reverseOne("1"));
    }

    @Test
    void testReversePalindrome() {
        assertEquals("arara", reverseOne("arara"));
    }
}