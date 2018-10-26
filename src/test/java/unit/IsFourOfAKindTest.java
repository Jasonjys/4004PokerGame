package unit;

import com.comp4004.Algorithm;
import com.comp4004.Player;
import com.comp4004.PokerGame;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class IsFourOfAKindTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"H10 D10 S10 C10 HA", "DA SA D5 DA HA", "C10 HJ DJ SJ CJ", "CK HK DK S8 SK"})
    void fourOfAKindWithRandomOrderShouldPass(String fourOfAKind) {
        game.setPlayerHand(0, fourOfAKind);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isFourOfAKind(aip.getHand()).isMatched());
    }

    @Test
    void nonFourOfAKindShouldFail () {
        game.setPlayerHand(0, "S3 C6 H10 D10 S10");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isFourOfAKind(aip.getHand()).isMatched());
    }
}
