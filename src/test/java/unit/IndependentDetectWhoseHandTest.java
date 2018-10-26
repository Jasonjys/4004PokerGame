package unit;

import com.comp4004.Player;
import com.comp4004.PokerGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IndependentDetectWhoseHandTest {
    private final PokerGame game = new PokerGame("C5 H7 H9 D6 S8 S4 SA D9 DA C2 DK SK D10");

    @Test
    void IndependentDetectWhoseHand () {
        Player opponent = game.getPlayer(0);
        Player aip = game.getPlayer(1);

        int opponentScore = opponent.getScore();
        int aipScore = aip.getScore();

        Assertions.assertEquals(5, opponentScore);
        Assertions.assertEquals(2, aipScore);
    }
}
