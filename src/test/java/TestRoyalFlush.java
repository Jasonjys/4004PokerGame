import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestRoyalFlush {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"H10 HJ HQ HK HA", "S10 SJ SQ SK SA", "D10 DJ DQ DK DA", "D10 DJ DQ DK DA"})
    void allStraightFlushesShouldPass(String royalFlush) {
        game.setPlayerHand(0, royalFlush);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isRoyalFlush(aip.getHand()).isMatched());
    }

    @Test
    void nonRoyalFlushShouldFail () {
        game.setPlayerHand(0, "S3 C6 H10 DJ SQ");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isRoyalFlush(aip.getHand()).isMatched());
    }
}
