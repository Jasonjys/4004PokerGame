import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.*;

public class TestFullHouse {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @MethodSource("fullHouseProvider")
    void allFullHouseShouldPass(String straightFlush) {
        game.setPlayerHand(0, straightFlush);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isFullHouse(aip.getHand()).isMatched());
    }

    @Test
    void nonFullHouseShouldFail () {
        game.setPlayerHand(0, "S3 C8 H10 D10 SQ");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isFullHouse(aip.getHand()).isMatched());
    }

    static List<String> fullHouseProvider () {

    }
}
