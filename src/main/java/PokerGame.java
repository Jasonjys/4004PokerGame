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
}
