package com.comp4004;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OneCardAwayFromFullHouseTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"S10 D10 SQ S10 SK", "CJ SA SJ DA S10", "H5 S10 S5 D5 SQ", "HQ H10 D10 HA DA"})
    void oneAwayFromFHWithRandomOrder(String oneCardAwayFromFH) {
        game.setPlayerHand(0, oneCardAwayFromFH);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isOneCardAwayFromFullHouse(aip.getHand()).isMatched());
    }

    @Test
    void nonOneAwayFromFHShouldFail () {
        game.setPlayerHand(0, "S2 C9 H9 D10 SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isOneCardAwayFromFullHouse(aip.getHand()).isMatched());
    }
}
