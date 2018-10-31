package stepDefinitions;

import com.comp4004.PokerGame;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;

import org.junit.jupiter.api.*;

public class StepDefinitions {
    PokerGame game;

    private String generateCards (String combination) {
        switch(combination) {
            case "royal flush":
                return "S10 SK SA SQ SJ";
            case "straight flush":
                return "H9 H10 HJ HQ HK";
            case "4 of a kind":
                return "C3 D3 H3 S3 C2";
            case "full house":
                return "CA D5 H5 DA HA";
            case "flush":
                return "D8 D7 D4 DQ DK";
            case "straight":
                return "CK DJ CQ S9 D10";
            case "3 of a kind":
                return "C8 D2 H8 D9 S8";
            case "2 pairs":
                return "H4 CJ H6 D6 C4";
            case "1 pair":
                return "H2 S4 S5 C5 C6";
            case "high card":
                return "S2 S6 C9 S4 S7";
            default:
                return "S2 S6 C9 S4 DQ";
        }
    }

    @Given("^Create a Poker Game Play$")
    public void createAPokerGamePlay() throws Throwable {
        game = new PokerGame();
    }

    @When("^AIP has \"([^\"]*)\"$")
    public void AIPHas(String cards) throws Throwable {
        game.setPlayerHand(1, cards);
    }

    @When("^HTB has \"([^\"]*)\"$")
    public void HTBHas(String cards) throws Throwable {
        game.setPlayerHand(0, cards);
    }

    @Then("^AIP wins")
    public void aipWins() throws Throwable {
        game.play(0);
        Assertions.assertTrue(game.getPlayer(1).getWon());
    }

    @When("^\"([^\"]*)\" has \"([^\"]*)\"$")
    public void has(String player, String cards) throws Throwable {
        if (player.equals("AIP")) {
            game.setPlayerHand(1, generateCards(cards));
        } else {
            game.setPlayerHand(0, generateCards(cards));
        }
    }

    @Then("^The winner is \"([^\"]*)\"$")
    public void winnerIs(String player) throws Throwable {
        game.play(0);
        if (player.equals("AIP")) {
            Assertions.assertTrue(game.getPlayer(1).getWon());
        } else {
            Assertions.assertTrue(game.getPlayer(0).getWon());
        }
    }

    @Then("^\"([^\"]*)\" holds$")
    public void holds(String player) throws Throwable {
        if (player.equals("AIP")) {
            String discardCards = game.getPlayer(1).discardCards().toString();
            Assertions.assertEquals(discardCards, "[]");
        }
    }

    @Then("^\"([^\"]*)\" discards \"([^\"]*)\"$")
    public void discards(String player, String cards) throws Throwable {
        if (player.equals("AIP")) {
            String discardCards = game.getPlayer(1).discardCards().toString();
            Assertions.assertEquals(discardCards, cards);
        }
    }

    @When("^\"([^\"]*)\" has \"([^\"]*)\" \"([^\"]*)\"$")
    public void has(String player, String hand, String cards) throws Throwable {
        if (player.equals("AIP")) {
            game.setPlayerHand(1, cards);
        } else {
            game.setPlayerHand(0, cards);
        }
    }

    @When("^Replacement is \"([^\"]*)\"$")
    public void replacement(String replacement) throws Throwable {
        game.setDeck(replacement);
    }

    @Given("^No replacements$")
    public void noReplacements() throws Throwable {
        game.setDeck("[]");
    }
}
