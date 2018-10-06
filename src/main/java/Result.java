import java.util.*;

public class Result {
    private boolean matched;
    private Card highestCard;
    private List<Card> discardCards;

    public Result (boolean matched, Card highestCard, List <Card> discardCards) {
        this.matched = matched;
        this.highestCard = highestCard;
        this.discardCards = discardCards;
    }

    public boolean isMatched () {
        return matched;
    }

    public Card getHighestCard () {
        return highestCard;
    }

    public List <Card> getDiscardCards() {
        return discardCards;
    }

    @Override
    public String toString () {
        if (isMatched()) {
            if (getDiscardCards() != null) {
                return getDiscardCards().toString();
            } else {
                return getHighestCard().toString();
            }
        }
        return "false";
    }
}
