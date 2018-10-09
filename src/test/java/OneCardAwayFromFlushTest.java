import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OneCardAwayFromFlushTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"H7 S7 S5 S8 S6", "D9 D5 DK D8 CQ", "HA S4 H7 H5 HQ", "S10 SJ D6 S4 SA"})
    void oneAwayFromFlushWithRandomOrderShouldPass(String oneCardAwayFromFlush) {
        game.setPlayerHand(0, oneCardAwayFromFlush);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isOneCardAwayFromFlush(aip.getHand()).isMatched());
    }

    @Test
    void nonOneAwayFromFlushShouldFail () {
        game.setPlayerHand(0, "S2 C9 H6 D9 SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isOneCardAwayFromFlush(aip.getHand()).isMatched());
    }
}
