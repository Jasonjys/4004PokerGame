import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OneCardAwayFromRoyalFlushTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"S10 D7 SQ SJ SK", "SJ SA SQ D2 S10", "DJ S10 SJ SK SQ", "HQ H10 HK HA S8"})
    void oneAwayFromRFWithRandomOrderShouldPass(String oneCardAwayFromRF) {
        game.setPlayerHand(0, oneCardAwayFromRF);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isOneCardAwayFromRoyalFlush(aip.getHand()).isMatched());
    }

    @Test
    void nonOneAwayFromRFShouldFail () {
        game.setPlayerHand(0, "S2 C9 H9 D9 SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isOneCardAwayFromRoyalFlush(aip.getHand()).isMatched());
    }
}
