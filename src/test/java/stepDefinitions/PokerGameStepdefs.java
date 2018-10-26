package stepDefinitions;

import com.comp4004.PokerGame;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.jupiter.api.*;

public class PokerGameStepdefs {
    PokerGame game;

    @Given("^Create a Poker Game Play$")
    public void createAPokerGamePlay() throws Throwable {
        game = new PokerGame();
    }

    @When("^AIP has \"([^\"]*)\"$")
    public void AIPHas(String cards) throws Throwable {
        game.setPlayerHand(1, cards);

    }

    @When("^Opponent has \"([^\"]*)\"$")
    public void opponentHas(String cards) throws Throwable {
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
}
