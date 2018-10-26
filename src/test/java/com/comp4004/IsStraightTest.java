package com.comp4004;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class IsStraightTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"S10 CJ SQ SK SA", "C9 S8 H10 DJ SQ", "S4 SA D3 H5 C2", "SK DJ HQ CA D10", "C5 D7 H9 D6 S8"})
    void straightWithRandomOrderShouldPass(String straight) {
        game.setPlayerHand(0, straight);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isStraight(aip.getHand()).isMatched());
    }

    @Test
    void nonStraightShouldFail () {
        game.setPlayerHand(0, "S2 C9 H9 DA SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isStraight(aip.getHand()).isMatched());
    }
}
