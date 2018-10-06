import java.util.*;

public class Player {
    private List <Card> hand;

    public Player () {
        this.hand = new ArrayList <Card>();
    }

    public List<Card> getHand () {
        return hand;
    }

    public void addCard (Card card) {
        getHand().add(card);
    }

    public void printHand () {
        Collections.sort(getHand(), new CardSorter());

        if (Algorithm.isTenToFourteen(getHand())) {
            getHand().add(getHand().remove(0));
        }

        for (int i = 0; i < hand.size(); i++) {
            System.out.print(hand.get(i).toString() + " ");
        }
        System.out.println("\n-------------------------");
    }
}
