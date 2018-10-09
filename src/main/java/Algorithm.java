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

    private static HashMap<Integer, Integer> buildRankMap (List<Card> hand) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (Card card:hand) {
            if (!map.containsKey(card.getRank())) {
                map.put(card.getRank(), 1);
            } else {
                map.put(card.getRank(), map.get(card.getRank()) + 1);
            }
        }
        return map;
    }

    private static Result isXOfAKind (List<Card> hand, int x) {
        HashMap<Integer, Integer> map = buildRankMap(hand);
        List<Card> cardsWithSameRank = new ArrayList <Card>();
        List<Card> discardCards = new ArrayList <Card>();

        if (map.containsValue(x) && map.size() == (6 - x)) {
            for (Card card : hand) {
                if (map.get(card.getRank()) == x) {
                    cardsWithSameRank.add(card);
                } else {
                    discardCards.add(card);
                }
            }
            if (x == 2) {
                return new Result(true, cardsWithSameRank.get(cardsWithSameRank.size() - 1), discardCards);
            }
            return new Result(true, cardsWithSameRank.get(cardsWithSameRank.size() - 1), null);
        }
        return new Result(false, null, null);
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

    public static Result isStraightFlush (List<Card> hand) {
        if (isStraight(hand).isMatched() && isFlush(hand).isMatched()) {
            if (isTenToFourteen(hand)) {
                return new Result(true, hand.get(0), null);
            }
            return new Result(true, hand.get(4), null);
        }

        return new Result(false, null, null);
    }

    public static Result isFourOfAKind (List<Card> hand) {
        return isXOfAKind(hand, 4);
    }

    public static Result isFullHouse (List<Card> hand) {
        HashMap<Integer, Integer> map = buildRankMap(hand);
        List<Card> cardsWithSameRank = new ArrayList<Card>();

        if (map.containsValue(3) && map.size() == 2) {
            for (Card card : hand) {
                if (map.get(card.getRank()) == 3) {
                    cardsWithSameRank.add(card);
                }
            }
            Collections.sort(cardsWithSameRank, new CardSorter());
            return new Result(true, cardsWithSameRank.get(cardsWithSameRank.size() - 1), null);
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
