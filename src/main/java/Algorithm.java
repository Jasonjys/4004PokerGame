import java.util.*;

public class Algorithm {
    public Algorithm() {}

    public static boolean isTenToFourteen (List<Card> hand) {
        return hand.get(0).getRank() == 1
                && hand.get(1).getRank() == 10
                && hand.get(2).getRank() == 11
                && hand.get(3).getRank() == 12
                && hand.get(4).getRank() == 13;
    }


}
