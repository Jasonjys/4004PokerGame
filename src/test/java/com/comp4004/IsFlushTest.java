package com.comp4004;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class IsFlushTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"D2 D4 D7 D8 DA", "S4 SA S5 S2 S9", "D8 D7 D4 DJ DK", "C10 C5 C3 C6 CJ"})
    void flushShouldPass(String flush) {
        game.setPlayerHand(0, flush);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isFlush(aip.getHand()).isMatched());
    }

    @Test
    void nonFlushShouldFail () {
        game.setPlayerHand(0, "S2 C7 H9 DJ SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isFlush(aip.getHand()).isMatched());
    }
}
