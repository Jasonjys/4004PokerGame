package com.comp4004;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class IsHighCardTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"C9 S2 H3 DJ SQ", "S4 SJ D9 HA C2", "SK D3 H10 C7 D4", "CA D7 H2 D6 S8"})
    void highCardWithRandomOrderShouldPass(String highCard) {
        game.setPlayerHand(0, highCard);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isHighCard(aip.getHand()).isMatched());
    }

    @Test
    void nonHighCardShouldFail () {
        game.setPlayerHand(0, "S2 C5 H9 D9 SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isHighCard(aip.getHand()).isMatched());
    }
}
