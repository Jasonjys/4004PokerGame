import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ThreeCardsInSequenceTest {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @ValueSource(strings = {"H7 S8 S9 S2 SQ", "DA D2 DK D8 C3", "H10 H5 SJ HQ S2", "S3 SK DQ S4 SA"})
    void threeCardsInSequenceShouldPass(String threeCardsInSequence) {
        game.setPlayerHand(0, threeCardsInSequence);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isThreeCardsInSequence(aip.getHand()).isMatched());
    }

    @Test
    void nonThreeCardsInSequenceShouldFail () {
        game.setPlayerHand(0, "S2 C3 H10 D9 S5");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isThreeCardsInSequence(aip.getHand()).isMatched());
    }
}
