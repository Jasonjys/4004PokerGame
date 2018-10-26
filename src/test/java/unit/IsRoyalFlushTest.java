package unit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.comp4004.*;

public class IsRoyalFlushTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"H10 HJ HQ HK HA", "D10 DJ DQ DK DA", "C10 CJ CQ CK CA", "S10 SJ SQ SK SA"})
    void straightFlushShouldPass(String royalFlush) {
        game.setPlayerHand(0, royalFlush);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isRoyalFlush(aip.getHand()).isMatched());
    }

    @Test
    void royalFlushWithRandomOrderShouldPass () {
        game.setPlayerHand(0, "CQ C10 CJ CK CA");
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isRoyalFlush(aip.getHand()).isMatched());
    }

    @Test
    void nonRoyalFlushShouldFail () {
        game.setPlayerHand(0, "C9 S8 H10 DJ SQ");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isRoyalFlush(aip.getHand()).isMatched());
        Assertions.assertTrue(Algorithm.isStraight(aip.getHand()).isMatched());
    }
}
