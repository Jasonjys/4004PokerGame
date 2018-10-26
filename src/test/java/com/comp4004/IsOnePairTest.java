package com.comp4004;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class IsOnePairTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"C9 S2 HJ DJ SQ", "S4 SA D9 HA C2", "SK D3 H10 C3 D4", "C8 D7 H2 D6 S8"})
    void onePairWithRandomOrderShouldPass(String onePair) {
        game.setPlayerHand(0, onePair);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isOnePair(aip.getHand()).isMatched());
    }

    @Test
    void nonOnePairShouldFail () {
        game.setPlayerHand(0, "S2 C9 H9 D9 SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isOnePair(aip.getHand()).isMatched());
    }
}
