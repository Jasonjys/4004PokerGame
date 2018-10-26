package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.comp4004.*;

public class OneCardAwayFromStraightTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"H7 S7 D5 H8 H6", "D9 D5 H6 D8 CQ", "DA D4 S3 D5 SQ", "HQ C10 DJ C9 S4"})
    void oneAwayFromStraightWithRandomOrderShouldPass(String oneCardAwayFromStraight) {
        game.setPlayerHand(0, oneCardAwayFromStraight);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isOneCardAwayFromStraight(aip.getHand()).isMatched());
    }

    @Test
    void nonOneAwayFromStraightShouldFail () {
        game.setPlayerHand(0, "S2 C9 H6 D9 SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isOneCardAwayFromStraight(aip.getHand()).isMatched());
    }
}
