import java.util.*;

public class Algorithm {
    public Algorithm() {}

    private static HashSet<String> buildSuitSet (List<Card> hand) {
        HashSet<String> set = new HashSet<String>();
        for(Card card:hand) {
            set.add(card.getSuit());
        }
        return set;
    }

    public static boolean isTenToFourteen (List<Card> hand) {
        return hand.get(0).getRank() == 1
                && hand.get(1).getRank() == 10
                && hand.get(2).getRank() == 11
                && hand.get(3).getRank() == 12
                && hand.get(4).getRank() == 13;
    }

    public static Result isRoyalFlush (List<Card> hand) {
        if (isFlush(hand).isMatched() && isTenToFourteen(hand)) {
            return new Result(true, hand.get(0), null);
        }
        return new Result(false, null, null);
    }

    public static Result isFlush (List<Card> hand) {
        HashSet<String> set = buildSuitSet(hand);
        Collections.sort(hand, new CardSorter());

        if (set.size() == 1) {
            if (isTenToFourteen(hand) || hand.get(0).getRank() == 1) {
                return new Result(true, hand.get(0), null);
            }
            return new Result(true, hand.get(4), null);
        }
        return new Result(false, null, null);
    }
}
