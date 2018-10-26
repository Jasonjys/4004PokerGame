package com.comp4004;

public class Card {
    private String suit;
    private int rank;

    public Card (String card) {
        String[] letters = card.split("(?!^)");
        String suit = letters[0];
        String rankString;

        if (letters.length == 2) {
            rankString = letters[1];
        } else {
            rankString = letters[1] + letters[2];
        }

        int rank;
        if (rankString.equals("A")) {
            rank = 1;
        } else if (rankString.equals("J")) {
            rank = 11;
        } else if (rankString.equals("Q")) {
            rank = 12;
        } else if (rankString.equals("K")) {
            rank = 13;
        } else {
            rank = Integer.parseInt(rankString);
        }
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit () {
        return suit;
    }

    public int getRank () {
        return rank;
    }

    @Override
    public String toString() {
        switch (getRank()) {
            case 1: return getSuit().concat("A");
            case 11: return getSuit().concat("J");
            case 12: return getSuit().concat("Q");
            case 13: return getSuit().concat("K");
            default: return getSuit().concat(Integer.toString(getRank()));
        }
    }
}
