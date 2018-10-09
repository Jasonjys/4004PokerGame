import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class IsTwoPairTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"C9 S9 HJ DJ SQ", "S4 SA D2 HA C2", "SK D3 H10 C3 D10", "C8 D7 H6 D6 S8"})
    void twoPairWithRandomOrderShouldPass(String twoPair) {
        game.setPlayerHand(0, twoPair);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isTwoPair(aip.getHand()).isMatched());
    }

    @Test
    void nonTwoPairShouldFail () {
        game.setPlayerHand(0, "S2 C9 H9 D9 SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isTwoPair(aip.getHand()).isMatched());
    }
}
