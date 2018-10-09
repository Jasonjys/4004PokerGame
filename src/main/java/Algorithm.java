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

    private static HashSet possibleSequenceSet (int startingNum) {
        HashSet<Integer> possibleSequence = new HashSet<Integer>();

        for (int i = startingNum; i < startingNum + 5; i++) {
            possibleSequence.add(i);
        }

        return possibleSequence;
    }

    private static HashSet tenToFourteenSet () {
        HashSet<Integer> possibleSequence = new HashSet<Integer>();

        for (int i = 10; i < 15; i++) {
            if (i == 14) {
                possibleSequence.add(1);
            } else {
                possibleSequence.add(i);
            }
        }
        return possibleSequence;
    }

    private static Result isOneCardAwayFromTenToFourTeen (List<Card> hand) {
        HashSet<Integer> sequence = tenToFourteenSet();
        List<Card> discardCards = new ArrayList <Card>();

        for (Card card : hand) {
            if (sequence.contains(card.getRank())) {
                sequence.remove(card.getRank());
            } else {
                discardCards.add(card);
            }
        }

        return sequence.size() == 1
                ? new Result(true, null, discardCards)
                : new Result(false, null, null);
    }

    private static Result isOneCardAwayFromSForRF (String missingSuit, int missingRank, List<Card> hand) {
        List<Card> discardCards = new ArrayList <Card>();

        for (Card card : hand) {
            if (card.getSuit().equals(missingSuit) && card.getRank() == missingRank) {
                discardCards.add(card);
                return new Result(true, null, discardCards);
            }
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

    public static Boolean isStraightOrBetter (List<Card> hand) {
        return Algorithm.isRoyalFlush(hand).isMatched()
                || Algorithm.isStraightFlush(hand).isMatched()
                || Algorithm.isFourOfAKind(hand).isMatched()
                || Algorithm.isFullHouse(hand).isMatched()
                || Algorithm.isFlush(hand).isMatched()
                || Algorithm.isStraight(hand).isMatched();
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

    public static Result isOneCardAwayFromFullHouse (List<Card> hand) {
        if (isTwoPair(hand).isMatched() || isThreeOfAKind(hand).isMatched()) {
            HashMap<Integer, Integer> map = buildRankMap(hand);
            List<Card> discardCards = new ArrayList <Card>();
            for(Integer key : map.keySet()){
                if(map.get(key).equals(1)) {
                    for (Card card : hand) {
                        if (card.getRank() == key) {
                            discardCards.add(card);
                            return new Result(true, null, discardCards);
                        }
                    }
                }
            }
        }
        return new Result(false, null, null);
    }

    public static Result isOneCardAwayFromFlush (List<Card> hand) {
        HashMap<String, Integer> map = buildSuitMap(hand);
        List<Card> discardCards = new ArrayList <Card>();
        if (map.size() == 2) {
            for (String key : map.keySet() ) {
                if (map.get(key) == 1) {
                    for (Card card : hand) {
                        if (card.getSuit().equals(key)) {
                            discardCards.add(card);
                            return new Result(true, null, discardCards);
                        }
                    }
                }
            }
        }
        return new Result(false, null, null);
    }

    public static Result isOneCardAwayFromStraight (List<Card> hand) {
        if (!isStraight(hand).isMatched()) {
            if (isOneCardAwayFromTenToFourTeen(hand).isMatched()) {
                return isOneCardAwayFromTenToFourTeen(hand);
            }

            HashSet<Integer> possibleSequence1 = possibleSequenceSet(hand.get(0).getRank());
            HashSet<Integer> possibleSequence2 = possibleSequenceSet(hand.get(1).getRank());

            List<Card> discardCards1 = new ArrayList<Card>();
            List<Card> discardCards2 = new ArrayList<Card>();

            for (int i = 0; i < 5; i++) {
                Card card = hand.get(hand.size() - 1 - i);
                int currentCardRank = card.getRank();
                if (possibleSequence1.contains(currentCardRank)) {
                    possibleSequence1.remove(currentCardRank);
                } else {
                    discardCards1.add(card);
                }
                if (possibleSequence2.contains(currentCardRank)) {
                    possibleSequence2.remove(currentCardRank);
                } else {
                    discardCards2.add(card);
                }
            }

            if (possibleSequence1.size() == 1 && possibleSequence2.size() == 1) {
                return new Result(true, null, discardCards2);
            } else if (possibleSequence1.size() == 1) {
                return new Result(true, null, discardCards1);
            } else if (possibleSequence2.size() == 1) {
                return new Result(true, null, discardCards2);
            }
        }
        return new Result(false, null, null);
    }

    public static Result isThreeOfASuit (List<Card> hand) {
        HashMap<String, Integer> map = buildSuitMap(hand);
        List<Card> discardCards = new ArrayList <Card>();

        for (String key : map.keySet()) {
            if (map.get(key) == 3) {
                for (Card card : hand) {
                    if (!card.getSuit().equals(key)) {
                        discardCards.add(card);
                    }
                }
                return new Result(true, null, discardCards);
            }
        }

        return new Result(false, null, null);
    }
}
