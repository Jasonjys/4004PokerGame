import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class IsFullHouseTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"D8 D4 C8 S8 S4", "S4 SA D4 DA C4", "S7 D7 HK C7 DK", "CA D5 H5 DA SA"})
    void fullHouseWithRandomOrderShouldPass(String fullHouse) {
        game.setPlayerHand(0, fullHouse);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isFullHouse(aip.getHand()).isMatched());
    }

    @Test
    void nonFlushShouldFail () {
        game.setPlayerHand(0, "S2 C9 H9 DJ SA");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isFullHouse(aip.getHand()).isMatched());
    }
}
