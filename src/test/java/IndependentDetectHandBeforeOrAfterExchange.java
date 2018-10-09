import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IndependentDetectHandBeforeOrAfterExchange {
    private final PokerGame game = new PokerGame("C5 H7 H9 D6 S8 S4 SA D9 DA C2 DK SK D10");

    @Test
    void IndependentDetectHandBeforeOrAfterExchange () {
        Player aip = game.getPlayer(1);

        int scoreBeforeExchange = aip.getScore();
        aip.exchangeCards(aip.discardCards(), game.getDeck());
        int scoreAfterExchange = aip.getScore();

        Assertions.assertEquals(2, scoreBeforeExchange);
        Assertions.assertEquals(3, scoreAfterExchange);
    }
}
