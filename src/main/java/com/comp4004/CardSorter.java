package com.comp4004;

import java.util.Comparator;

public class CardSorter implements Comparator<Card> {
    // Used for sorting in ascending order of card
    public int compare (Card a, Card b) {
        if (a.getRank() == b.getRank()) {
            return a.getSuit().compareTo(b.getSuit());
        }

        return a.getRank() - b.getRank();
    }
}
