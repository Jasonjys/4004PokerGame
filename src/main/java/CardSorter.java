import java.util.Comparator;

class CardSorter implements Comparator<Card> {
    // Used for sorting in ascending order of card
    public int compare (Card a, Card b) {
        if (a.getRank() == b.getRank()) {
            return a.getSuit().compareTo(b.getSuit());
        }

        return a.getRank() - b.getRank();
    }
}
