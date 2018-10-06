import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.*;

public class TestStraightFlush {
    private final PokerGame game = new PokerGame();

    @ParameterizedTest
    @MethodSource("straightFlushProvider")
    void allStraightFlushesShouldPass(String straightFlush) {
        game.setPlayerHand(0, straightFlush);
        Player aip = game.getPlayer(0);

        Assertions.assertTrue(Algorithm.isStraightFlush(aip.getHand()).isMatched());
    }

    @Test
    void nonStraightFlushShouldFail () {
        game.setPlayerHand(0, "S3 C6 H10 D10 SQ");
        Player aip = game.getPlayer(0);

        Assertions.assertFalse(Algorithm.isStraightFlush(aip.getHand()).isMatched());
    }

    static List<String> straightFlushProvider () {
        List<String> combinations = new ArrayList <String>();
        String[] suits = {"S", "H", "D", "C"};

        for (String suit : suits) {
            for (int i = 1; i <=9; i++) {
                String combination = "";
                for (int j = 0; j < 5; j++) {
                    String rank = Integer.toString(i + j);
                    if (rank.equals("1")) rank = "A";
                    if (rank.equals("11")) rank = "J";
                    if (rank.equals("12")) rank = "Q";
                    if (rank.equals("13")) rank = "K";

                    if (j != 4) {
                        combination += suit + rank + " ";
                    } else {
                        combination += suit + rank;
                    }
                }
                combinations.add(combination);
            }

            combinations.add(suit + "10 " + suit + "J " + suit + "Q " + suit + "K " + suit + "A");
        }
        return combinations;
    }
}
