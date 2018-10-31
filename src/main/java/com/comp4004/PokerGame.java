package com.comp4004;

import java.util.*;
import java.io.*;

public class PokerGame {
    private List<Player> playerList;
    private Queue<Card> deck;

    public PokerGame (String cards) {
        this.playerList = new ArrayList<>();
        this.deck = new LinkedList<>();
        this.playerList.add(new Player());
        this.playerList.add(new Player());
        setUpCards(cards);
    }

    public PokerGame () {
        this.playerList = new ArrayList<>();
        this.deck = new LinkedList<>();
        this.playerList.add(new Player());
        this.playerList.add(new Player());
    }

    public void setUpCards (String cards) {
        String[] cardArray = cards.split("\\s+");
        for (String card : cardArray) {
            getDeck().add(new Card(card));
        }
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                getPlayer(0).addCard(getDeck().remove());
            } else {
                getPlayer(1).addCard(getDeck().remove());
            }
        }
    }

    public Queue<Card> getDeck () { return this.deck; }

    public List<Player> getPlayers () {
        return this.playerList;
    }

    public Player getPlayer (int index) {
        return getPlayers().get(index);
    }

    public void setPlayerHand (int index, String cards) {
        if (cards.length() == 0) {
            return;
        }
        getPlayer(index).getHand().clear();
        String[] cardArray = cards.split("\\s+");
        for (String card :  cardArray) {
            getPlayer(index).addCard(new Card(card));
        }
    }

    public void setDeck (String cards) {
        getDeck().clear();
        if (cards.equals("[]")) {
            return;
        }
        String[] cardArray = cards.split("\\s+");
        for (String card :  cardArray) {
            getDeck().add(new Card(card));
        }
    }

    private int compare (Card a, Card b) {
        if (a.getRank() == 1 || b.getRank() == 1) {
            return a.getSuit().compareTo(b.getSuit());
        } else if (a.getRank() == 1 && b.getRank() != 1) {
            return 1;
        } else if (a.getRank() != 1 && b.getRank() == 1) {
            return -1;
        }
        if (a.getRank() == b.getRank()) {
            return a.getSuit().compareTo(b.getSuit());
        }
        return a.getRank() - b.getRank();
    }

    private void compareScore () {
        Player opponent = getPlayer(0);
        Player aip = getPlayer(1);

        int s1 = opponent.getScore();
        int s2 = aip.getScore();

        Card opponentHighCard, aipHighCard;
        if (s1 != s2) {
            if (s1 > s2) opponent.setWon(true);
            else aip.setWon(true);
            return;
        } else {
            if (s1 == 10) {
                opponentHighCard = Algorithm.isRoyalFlush(opponent.getHand()).getHighestCard();
                aipHighCard = Algorithm.isRoyalFlush(aip.getHand()).getHighestCard();
            } else if (s1 == 9) {
                opponentHighCard = Algorithm.isStraightFlush(opponent.getHand()).getHighestCard();
                aipHighCard = Algorithm.isStraightFlush(aip.getHand()).getHighestCard();
            } else if (s1 == 8) {
                opponentHighCard = Algorithm.isFourOfAKind(opponent.getHand()).getHighestCard();
                aipHighCard = Algorithm.isFourOfAKind(aip.getHand()).getHighestCard();
            } else if (s1 == 7) {
                opponentHighCard = Algorithm.isFullHouse(opponent.getHand()).getHighestCard();
                aipHighCard = Algorithm.isFullHouse(aip.getHand()).getHighestCard();
            } else if (s1 == 6) {
                List<Card> opponentHand = opponent.getHand();
                List<Card> aipHand = aip.getHand();

                opponentHighCard = Algorithm.isFlush(opponentHand).getHighestCard();
                aipHighCard = Algorithm.isFlush(aipHand).getHighestCard();

                for (int i = 0; i < 5; i++) {
                    if (opponentHand.get(i).getRank() > aipHand.get(i).getRank()) {
                        opponent.setWon(true);
                    } else if (opponentHand.get(i).getRank() < aipHand.get(i).getRank()) {
                        aip.setWon(true);
                    }
                }
            } else if (s1 == 5) {
                opponentHighCard = Algorithm.isStraight(opponent.getHand()).getHighestCard();
                aipHighCard = Algorithm.isStraight(aip.getHand()).getHighestCard();
            } else if (s1 == 4) {
                opponentHighCard = Algorithm.isThreeOfAKind(opponent.getHand()).getHighestCard();
                aipHighCard = Algorithm.isThreeOfAKind(aip.getHand()).getHighestCard();
            } else if (s1 == 3) {
                opponentHighCard = Algorithm.isTwoPair(opponent.getHand()).getHighestCard();
                aipHighCard = Algorithm.isTwoPair(aip.getHand()).getHighestCard();
            } else if (s1 == 2) {
                opponentHighCard = Algorithm.isOnePair(opponent.getHand()).getHighestCard();
                aipHighCard = Algorithm.isOnePair(aip.getHand()).getHighestCard();
            } else {
                opponentHighCard = Algorithm.isHighCard(opponent.getHand()).getHighestCard();
                aipHighCard = Algorithm.isHighCard(aip.getHand()).getHighestCard();
            }
        }

        if (compare(opponentHighCard, aipHighCard) > 0) opponent.setWon(true);
        else aip.setWon(true);
    }

    private void displayHands () {
        Player opponent = getPlayer(0);
        Player aip = getPlayer(1);
        System.out.println("=============================");
        System.out.println("opponent: " + opponent.getHand().toString());
        System.out.println("AIP:      " + aip.getHand().toString());
    }

    private String Winner () {
        compareScore();
        if (getPlayer(0).getWon()) {
            return "Opponent won!";
        }
        return "AIP won!";
    }

    public List<String> play (int round) {
        Player aip = getPlayer(1);
        List<String> results = new ArrayList <String>();
        System.out.println("Game Round " + round);
        displayHands();
        String discardCards = aip.exchangeCards(aip.discardCards(), getDeck()).toString();
        results.add(discardCards);
        System.out.println("Card(s) to discard: " + discardCards);
        System.out.println("After AIP exchanged its cards");
        displayHands();
        System.out.println(Winner());
        results.add(Winner());
        System.out.println("###################################");

        return results;
    }

    public static void main (String args[]) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int round = 0;
        while (scanner.hasNextLine()) {
            PokerGame game = new PokerGame();
            game.setUpCards(scanner.nextLine());
            game.play(round);
            round++;
        }
        scanner.close();
    }
}
