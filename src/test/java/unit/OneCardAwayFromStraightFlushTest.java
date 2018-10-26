package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.comp4004.*;

public class OneCardAwayFromStraightFlushTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"H7 S7 H5 H8 H6", "D9 D5 D6 D8 CQ", "DA D4 D3 D5 SQ", "CQ C10 CJ C9 S8"})
    void oneAwayFromSFWithRandomOrderShouldPass(String oneCardAwayFromSF) {
        game.setPlayerHand(0, oneCardAwayFromSF);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isOneCardAwayFromStraightFlush(aip.getHand()).isMatched());
    }

    @Test
    void nonOneAwayFromSFShouldFail () {
        game.setPlayerHand(0, "S2 C9 H6 D9 SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isOneCardAwayFromStraightFlush(aip.getHand()).isMatched());
    }
}
