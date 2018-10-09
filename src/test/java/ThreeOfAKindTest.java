import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ThreeOfAKindTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"C9 S9 H9 DJ SQ", "S4 SA DA HA C2", "SK D3 H3 C3 D10", "C8 D7 H8 D6 S8"})
    void threeOfAKindWithRandomOrderShouldPass(String threeOfAKind) {
        game.setPlayerHand(0, threeOfAKind);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isThreeOfAKind(aip.getHand()).isMatched());
    }

    @Test
    void nonThreeOfAKindShouldFail () {
        game.setPlayerHand(0, "S2 C9 H9 DA SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isThreeOfAKind(aip.getHand()).isMatched());
    }
}
