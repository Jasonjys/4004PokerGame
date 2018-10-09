import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class IsThreeOfASuitTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"C9 C10 CQ DJ S3", "S4 SA DJ H7 S2", "SK S2 H5 C8 S10", "D8 D7 DA H6 S8"})
    void threeOfASuitWithRandomOrder(String threeOfASuit) {
        game.setPlayerHand(0, threeOfASuit);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isThreeOfASuit(aip.getHand()).isMatched());
    }

    @Test
    void nonThreeOfASuit () {
        game.setPlayerHand(0, "S2 C9 H9 DA SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isThreeOfASuit(aip.getHand()).isMatched());
    }
}
