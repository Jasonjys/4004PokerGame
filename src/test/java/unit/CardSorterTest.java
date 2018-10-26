package unit;

import com.comp4004.Card;
import com.comp4004.CardSorter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardSorterTest {
    private final CardSorter sorter = new CardSorter();

    @Test
    void D6IsLessThanD10() {
        Card D6 = new Card("D6");
        Card D10 = new Card("D10");

        int result = sorter.compare(D6, D10);
        Assertions.assertTrue(result < 0);
    }

    @Test
    void D9IsGreaterThanD3() {
        Card D9 = new Card("D9");
        Card D3 = new Card("D3");

        int result = sorter.compare(D9, D3);
        Assertions.assertTrue(result > 0);
    }

    @Test
    void S6IsGreaterThanH6() {
        Card S6 = new Card("S6");
        Card H6 = new Card("D6");

        int result = sorter.compare(S6, H6);
        Assertions.assertTrue(result > 0);
    }

    @Test
    void H6IsGreaterThanD6() {
        Card H6 = new Card("H6");
        Card D6 = new Card("D6");

        int result = sorter.compare(H6, D6);
        Assertions.assertTrue(result > 0);
    }

    @Test
    void D6IsGreaterThanC6() {
        Card D6 = new Card("D6");
        Card C6 = new Card("C6");

        int result = sorter.compare(D6, C6);
        Assertions.assertTrue(result > 0);
    }
}