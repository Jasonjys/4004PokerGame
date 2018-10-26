package com.comp4004;

import java.util.*;

public class Player {
    private List <Card> hand;
    private Boolean won;

    public Player () {
        this.hand = new ArrayList <Card>();
        this.won = false;
    }

    public List<Card> getHand () { return hand; }

    public Boolean getWon() { return won; }

    public void setWon(Boolean won) { this.won = won; }

    public void addCard (Card card) {
        getHand().add(card);
        Collections.sort(getHand(), new CardSorter());
    }

    public int getScore () {
        if (Algorithm.isRoyalFlush(getHand()).isMatched()) {
            return 10;
        } else if (Algorithm.isStraightFlush(getHand()).isMatched()) {
            return 9;
        } else if (Algorithm.isFourOfAKind(getHand()).isMatched()) {
            return 8;
        } else if (Algorithm.isFullHouse(getHand()).isMatched()) {
            return 7;
        } else if (Algorithm.isFlush(getHand()).isMatched()) {
            return 6;
        } else if (Algorithm.isStraight(getHand()).isMatched()) {
            return 5;
        } else if (Algorithm.isThreeOfAKind(getHand()).isMatched()) {
            return 4;
        } else if (Algorithm.isTwoPair(getHand()).isMatched()) {
            return 3;
        } else if (Algorithm.isOnePair(getHand()).isMatched()) {
            return 2;
        } else {
            return 1;
        }
    }

    public List<Card> discardCards () {
        if (Algorithm.isStraightOrBetter(getHand())) {
            if (Algorithm.isRoyalFlush(getHand()).isMatched()) {
                System.out.println("AIP has a Royal Flush!");
            } else if (Algorithm.isStraightFlush(getHand()).isMatched()) {
                System.out.println("AIP has a Straight Flush!");
            } else if (Algorithm.isFourOfAKind(getHand()).isMatched()) {
                System.out.println("AIP has a Four Of A Kind!");
            } else if (Algorithm.isFullHouse(getHand()).isMatched()) {
                System.out.println("AIP has a Full House!");
            } else if (Algorithm.isFlush(getHand()).isMatched()) {
                System.out.println("AIP has a Flush!");
            } else {
                System.out.println("AIP has a Straight!");
            }
            return new ArrayList <Card>();
        }

        Result isOneCardAwayFromRoyalFlush,
                isOneCardAwayFromStraightFlush,
                isOneCardAwayFromFullHouse,
                isOneCardAwayFromFlush,
                isOneCardAwayFromStraight,
                isThreeOfASuit,
                isThreeCardsInSequence,
                isOnePair,
                isHighCard;
        isOneCardAwayFromRoyalFlush = Algorithm.isOneCardAwayFromRoyalFlush(getHand());
        isOneCardAwayFromStraightFlush = Algorithm.isOneCardAwayFromStraightFlush(getHand());
        isOneCardAwayFromFullHouse = Algorithm.isOneCardAwayFromFullHouse(getHand());
        isOneCardAwayFromFlush = Algorithm.isOneCardAwayFromFlush(getHand());
        isOneCardAwayFromStraight = Algorithm.isOneCardAwayFromStraight(getHand());
        isThreeOfASuit = Algorithm.isThreeOfASuit(getHand());
        isThreeCardsInSequence = Algorithm.isThreeCardsInSequence(getHand());
        isOnePair = Algorithm.isOnePair(getHand());
        isHighCard = Algorithm.isHighCard(getHand());

        if (isOneCardAwayFromRoyalFlush.isMatched()) {
            System.out.println("AIP is one card away from a Royal Flush!");
            return isOneCardAwayFromRoyalFlush.getDiscardCards();
        } else if (isOneCardAwayFromStraightFlush.isMatched()) {
            System.out.println("AIP is one card away from a Straight Flush!");
            return isOneCardAwayFromStraightFlush.getDiscardCards();
        } else if (isOneCardAwayFromFullHouse.isMatched()) {
            System.out.println("AIP is one card away from a Full House!");
            return isOneCardAwayFromFullHouse.getDiscardCards();
        } else if (isOneCardAwayFromFlush.isMatched()) {
            System.out.println("AIP is one card away from a Flush!");
            return isOneCardAwayFromFlush.getDiscardCards();
        } else if (isOneCardAwayFromStraight.isMatched()) {
            System.out.println("AIP is one card away from a Straight!");
            return isOneCardAwayFromStraight.getDiscardCards();
        } else if (isThreeOfASuit.isMatched()) {
            System.out.println("AIP has three cards of the same suit!");
            return isThreeOfASuit.getDiscardCards();
        } else if (isThreeCardsInSequence.isMatched()) {
            System.out.println("AIP has three cards in sequence!");
            return isThreeCardsInSequence.getDiscardCards();
        } else if (isOnePair.isMatched()) {
            System.out.println("AIP has a One Pair!");
            return isOnePair.getDiscardCards();
        } else {
            System.out.println("AIP has a High Card :(");
            return isHighCard.getDiscardCards();
        }
    }

    public List<Card> exchangeCards (List<Card> discardCards, Queue<Card> deck) {
        System.out.println("Replacement(s): " + deck.toString());

        if (deck.size() == 0) {
            return discardCards;
        }

        for (Card card : discardCards) {
            getHand().remove(card);
            addCard(deck.remove());
        }
        return discardCards;
    }
}
