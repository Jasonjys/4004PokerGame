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
            case "4 of a kind":
                return "C3 D3 H3 S3 C2";
            case "full house":
                return "CA D5 H5 DA HA";
            case "flush":
                return "D8 D7 D4 D7 DK";
            case "straight":
                return "CK DJ HQ S9 D10";
            case "3 of a kind":
                return "C8 D2 H8 D9 S8";
            case "2 pairs":
                return "H4 CJ H6 D6 C4";
            default:
                return "S2 HJ C9 S4 CQ";
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

    @Then("^AIP does not exchange any of its cards$")
    public void aipDoesNotExchangeAnyOfItsCards() throws Throwable {
        String discardCards = game.getPlayer(1).discardCards().toString();
        Assertions.assertEquals(discardCards, "[]");
    }

    @Then("^AIP exchanges \"([^\"]*)\"$")
    public void aipExchangesCards(String card) throws Throwable {
        String discardCards = game.getPlayer(1).discardCards().toString();
        Assertions.assertEquals(discardCards, card);
    }

    @Then("^AIP wins")
    public void aipWins() throws Throwable {
        game.play(0);
        Assertions.assertTrue(game.getPlayer(1).getWon());
    }

    @Then("^AIP loses$")
    public void aipLoses() throws Throwable {
        game.play(0);
        Assertions.assertTrue(game.getPlayer(0).getWon());
    }

    @Then("^HTB wins")
    public void htbWins() throws Throwable {
        game.play(0);
        Assertions.assertTrue(game.getPlayer(0).getWon());
    }

    @Then("^AIP gets \"([^\"]*)\" after exchanging$")
    public void aipGetsFromTheDeck(String cards) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        game.setDeck(cards);
    }

    @When("^\"([^\"]*)\" has \"([^\"]*)\"$")
    public void has(String player, String cards) throws Throwable {
        if (player.equals("AIP")) {
            game.setPlayerHand(1, generateCards(cards));
        } else {
            game.setPlayerHand(0, generateCards(cards));
        }
    }

    @Then("^winner is \"([^\"]*)\"$")
    public void winnerIs(String player) throws Throwable {
        game.play(0);
        if (player.equals("AIP")) {
            Assertions.assertTrue(game.getPlayer(1).getWon());
        } else {
            Assertions.assertTrue(game.getPlayer(0).getWon());
        }
    }
}
