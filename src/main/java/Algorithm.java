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

    private static HashMap<String, Integer> buildSuitMap (List<Card> hand) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Card card:hand) {
            if (!map.containsKey(card.getSuit())) {
                map.put(card.getSuit(), 1);
            } else {
                map.put(card.getSuit(), map.get(card.getSuit()) + 1);
            }
        }
        return map;
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

    private static boolean isInSequence (List<Card> hand) {
        for (int i = 0; i < hand.size() - 1; i++) {
            int currentRank = hand.get(i).getRank();
            int nextRank = hand.get(i + 1).getRank();
            if (currentRank + 1 != nextRank) {
                return false;
            }
        }
        return true;
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
        HashMap<String, Integer> map = buildSuitMap(hand);

        if (map.size() == 1) {
            if (isTenToFourteen(hand) || hand.get(0).getRank() == 1) {
                return new Result(true, hand.get(0), null);
            }
            return new Result(true, hand.get(4), null);
        }
        return new Result(false, null, null);
    }

    public static Result isStraight (List<Card> hand) {
        if (isTenToFourteen(hand)) {
            return new Result(true, hand.get(0), null);
        }
        if (isInSequence(hand)) {
            return new Result(true, hand.get(4), null);
        }
        return new Result(false, null, null);
    }

    public static Result isThreeOfAKind (List<Card> hand) { return isXOfAKind(hand, 3); }

    public static Result isTwoPair(List<Card> hand) {
        HashMap<Integer, Integer> map = buildRankMap(hand);
        List<Card> cardsWithSameRank = new ArrayList<Card>();

        if (map.containsValue(2) && map.size() == 3) {
            for (Card card : hand) {
                if (map.get(card.getRank()) == 2) {
                    cardsWithSameRank.add(card);
                }
            }

            Collections.sort(cardsWithSameRank, new CardSorter());
            return new Result(true, cardsWithSameRank.get(cardsWithSameRank.size() - 1), null);
        }
        return new Result(false, null, null);
    }

    public static Result isOnePair (List<Card> hand) {
        return isXOfAKind(hand, 2);
    }

    public static Result isHighCard (List<Card> hand) {
        HashMap<Integer, Integer> map = buildRankMap(hand);
        if (!isFlush(hand).isMatched() && !isStraight(hand).isMatched() && map.size() == 5) {
            List<Card> discardCards = new ArrayList <Card>();
            if (hand.get(0).getRank() == 1) {
                discardCards.add(hand.get(1));
                discardCards.add(hand.get(2));
                discardCards.add(hand.get(3));
                return new Result(true, hand.get(0), discardCards);
            }
            discardCards.add(hand.get(0));
            discardCards.add(hand.get(1));
            discardCards.add(hand.get(2));
            return new Result(true, hand.get(4), discardCards);
        }

        return new Result(false, null, null);
    }

    public static Result isOneCardAwayFromRoyalFlush (List<Card> hand) {
        List<Card> discardCards = new ArrayList <Card>();
        if (isOneCardAwayFromFlush(hand).isMatched() && isTenToFourteen(hand)) {
            discardCards.add(isOneCardAwayFromFlush(hand).getDiscardCards().get(0));
            return new Result(true, null, discardCards);
        } else if (isOneCardAwayFromFlush(hand).isMatched() && isOneCardAwayFromTenToFourTeen(hand).isMatched()) {
            String missingSuit = isOneCardAwayFromFlush(hand).getDiscardCards().get(0).getSuit();
            int missingRank = isOneCardAwayFromTenToFourTeen(hand).getDiscardCards().get(0).getRank();
            return isOneCardAwayFromSForRF(missingSuit, missingRank, hand);
        }
        return new Result(false, null, null);
    }

    public static Result isOneCardAwayFromStraightFlush (List<Card> hand) {
        if (isOneCardAwayFromRoyalFlush(hand).isMatched()) {
            return isOneCardAwayFromRoyalFlush(hand);
        } else if (isOneCardAwayFromFlush(hand).isMatched() && isOneCardAwayFromStraight(hand).isMatched()) {
            String missingSuit = isOneCardAwayFromFlush(hand).getDiscardCards().get(0).getSuit();
            int missingRank = isOneCardAwayFromStraight(hand).getDiscardCards().get(0).getRank();
            return isOneCardAwayFromSForRF (missingSuit, missingRank, hand);
        } else if (isFlush(hand).isMatched() && isOneCardAwayFromStraight(hand).isMatched()) {
            return new Result(true, null, isOneCardAwayFromStraight(hand).getDiscardCards());
        } else if (isOneCardAwayFromFlush(hand).isMatched() && isStraight(hand).isMatched()) {
            return new Result(true, null, isOneCardAwayFromFlush(hand).getDiscardCards());
        } else {
            return new Result(false, null, null);
        }
    }
}
