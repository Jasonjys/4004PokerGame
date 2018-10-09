import java.util.*;
import java.io.*;

public class PokerGame {
    private List<Player> playerList;
    private Queue<Card> deck;

    public PokerGame (String cards) {
        this.playerList = new ArrayList<Player>();
        this.deck = new LinkedList <Card>();
        this.playerList.add(new Player());
        this.playerList.add(new Player());

        String[] cardArray = cards.split("\\s+");
        for (String card : cardArray) {
            this.deck.add(new Card(card));
        }
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                this.playerList.get(0).addCard(this.deck.remove());
            } else {
                this.playerList.get(1).addCard(this.deck.remove());
            }
        }
    }

    public PokerGame () {
        this.playerList = new ArrayList<Player>();
        this.playerList.add(new Player());
        this.playerList.add(new Player());
        this.deck = new LinkedList <Card>();
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
        String[] cardArray = cards.split("\\s+");
        for (String card :  cardArray) {
            getPlayer(index).addCard(new Card(card));
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

    private void winner () {
        compareScore();
        if (getPlayer(0).getWon()) {
            System.out.println("Opponent won!");
        } else {
            System.out.println("AIP won!");
        }
    }

    public void run (int round) {
        Player opponent = getPlayer(0);
        Player aip = getPlayer(1);
        System.out.println("Game Round " + round);
        System.out.println("=============================");
        System.out.print("opponent: ");
        System.out.println(opponent.getHand().toString());
        System.out.print("AIP:      ");
        System.out.println(aip.getHand().toString());

        System.out.println("Card(s) to discard: " + aip.exchangeCards(aip.discardCards(), getDeck()));
        System.out.println("After AIP exchanged its cards");
        System.out.println("=============================");
        System.out.print("opponent: ");
        System.out.println(opponent.getHand().toString());
        System.out.print("AIP:      ");
        System.out.println(aip.getHand().toString());
        winner();
        System.out.println("###################################");
    }

    public static void main (String args[]) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int round = 0;
        while (scanner.hasNextLine()) {
            PokerGame game = new PokerGame(scanner.nextLine());
            game.run(round);
            round++;
        }
        scanner.close();
    }
}
