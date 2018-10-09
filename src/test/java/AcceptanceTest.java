import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;

public class AcceptanceTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    private List<ArrayList<String>> gameResults = new ArrayList <ArrayList<String>>();

    @Before
    public void setUp () {
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(
                    "/Users/Jason/projects/simplePokerGame/input.txt")));
            int round = 0;
            while (scanner.hasNextLine()) {
                PokerGame game = new PokerGame(scanner.nextLine());
                game.run(round);
                round++;
            }
            String[] lines = systemOutRule.getLog().split("\\r?\\n");
            int numOfLinesPerGame = lines.length/round;
            for (int i = 0; i < round; i++) {
                ArrayList<String> gameResult = new ArrayList <String>();
                String aipHand = lines[i*numOfLinesPerGame + 4];
                String discardCards = lines[i*numOfLinesPerGame + 6];
                String winner = lines[i*numOfLinesPerGame + 11];
                gameResult.add(aipHand);
                gameResult.add(discardCards);
                gameResult.add(winner);
                gameResults.add(gameResult);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void detectRoyalFlushAndHold () {
        ArrayList<String> gameResult = gameResults.get(0);
        Assertions.assertEquals("AIP has a Royal Flush!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: []", gameResult.get(1));
    }

    @Test
    public void detectStraightFlushAndHold () {
        ArrayList<String> gameResult = gameResults.get(1);
        Assertions.assertEquals("AIP has a Straight Flush!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: []", gameResult.get(1));
    }

    @Test
    public void detectFourOfAKindAndHold () {
        ArrayList<String> gameResult = gameResults.get(2);
        Assertions.assertEquals("AIP has a Four Of A Kind!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: []", gameResult.get(1));
    }

    @Test
    public void detectFullHouseAndHold () {
        ArrayList<String> gameResult = gameResults.get(3);
        Assertions.assertEquals("AIP has a Full House!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: []", gameResult.get(1));
    }

    @Test
    public void detectFlushAndHold () {
        ArrayList<String> gameResult = gameResults.get(4);
        Assertions.assertEquals("AIP has a Flush!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: []", gameResult.get(1));
    }

    @Test
    public void detectStraightAndHold () {
        ArrayList<String> gameResult = gameResults.get(5);
        Assertions.assertEquals("AIP has a Straight!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: []", gameResult.get(1));
    }

    @Test
    public void detectOneCardAwayFromRFAndExchange () {
        ArrayList<String> gameResult = gameResults.get(6);
        Assertions.assertEquals("AIP is one card away from a Royal Flush!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: [C7]", gameResult.get(1));
    }

    @Test
    public void detectOneCardAwayFromSFAndExchange () {
        ArrayList<String> gameResult = gameResults.get(7);
        Assertions.assertEquals("AIP is one card away from a Straight Flush!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: [CK]", gameResult.get(1));
    }

    @Test
    public void detectOneCardAwayFromFlushAndExchange () {
        ArrayList<String> gameResult = gameResults.get(8);
        Assertions.assertEquals("AIP is one card away from a Flush!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: [HA]", gameResult.get(1));
    }

    @Test
    public void detectOneCardAwayFromStraightAndExchange () {
        ArrayList<String> gameResult = gameResults.get(9);
        Assertions.assertEquals("AIP is one card away from a Straight!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: [CQ]", gameResult.get(1));
    }

    @Test
    public void detectOneCardAwayFromFullHouseAndExchange () {
        ArrayList<String> gameResult = gameResults.get(10);
        Assertions.assertEquals("AIP is one card away from a Full House!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: [SQ]", gameResult.get(1));
    }

    @Test
    public void detectThreeOfASuitAndExchange () {
        ArrayList<String> gameResult = gameResults.get(11);
        Assertions.assertEquals("AIP has three cards of the same suit!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: [C4, C6]", gameResult.get(1));
    }

    @Test
    public void detectThreeCardsInSequenceAndExchange () {
        ArrayList<String> gameResult = gameResults.get(12);
        Assertions.assertEquals("AIP has three cards in sequence!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: [C8, SK]", gameResult.get(1));
    }

    @Test
    public void detectOnePairAndExchange () {
        ArrayList<String> gameResult = gameResults.get(13);
        Assertions.assertEquals("AIP has a One Pair!", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: [S4, S7, CQ]", gameResult.get(1));
    }

    @Test
    public void detectHighCardAndExchange () {
        ArrayList<String> gameResult = gameResults.get(14);
        Assertions.assertEquals("AIP has a High Card :(", gameResult.get(0));
        Assertions.assertEquals("Card(s) to discard: [S2, H3, C9]", gameResult.get(1));
    }


    @Test
    public void royalFlushBeatsAll () {
        ArrayList<String> RFBeatsSF = gameResults.get(15);
        Assertions.assertEquals("Opponent won!", RFBeatsSF.get(2));

        ArrayList<String> RFBeatsFOAK = gameResults.get(16);
        Assertions.assertEquals("Opponent won!", RFBeatsFOAK.get(2));

        ArrayList<String> RFBeatsFH = gameResults.get(17);
        Assertions.assertEquals("Opponent won!", RFBeatsFH.get(2));

        ArrayList<String> RFBeatsF = gameResults.get(18);
        Assertions.assertEquals("Opponent won!", RFBeatsF.get(2));

        ArrayList<String> RFBeatsS = gameResults.get(19);
        Assertions.assertEquals("Opponent won!", RFBeatsS.get(2));

        ArrayList<String> RFBeatsTOAK = gameResults.get(20);
        Assertions.assertEquals("Opponent won!", RFBeatsTOAK.get(2));

        ArrayList<String> RFBeatsTP = gameResults.get(21);
        Assertions.assertEquals("Opponent won!", RFBeatsTP.get(2));

        ArrayList<String> RFBeatsOP = gameResults.get(22);
        Assertions.assertEquals("Opponent won!", RFBeatsOP.get(2));

        ArrayList<String> RFBeatsHC = gameResults.get(23);
        Assertions.assertEquals("Opponent won!", RFBeatsHC.get(2));
    }

    @Test
    public void SFBeatsAllButRF () {
        ArrayList<String> SFLoseToFOAK = gameResults.get(24);
        Assertions.assertEquals("AIP won!", SFLoseToFOAK.get(2));

        ArrayList<String> SFBeatsFOAK = gameResults.get(25);
        Assertions.assertEquals("Opponent won!", SFBeatsFOAK.get(2));

        ArrayList<String> SFBeatsFH = gameResults.get(26);
        Assertions.assertEquals("Opponent won!", SFBeatsFH.get(2));

        ArrayList<String> SFBeatsF = gameResults.get(27);
        Assertions.assertEquals("Opponent won!", SFBeatsF.get(2));

        ArrayList<String> SFBeatsS = gameResults.get(28);
        Assertions.assertEquals("Opponent won!", SFBeatsS.get(2));

        ArrayList<String> SFBeatsTOAK = gameResults.get(29);
        Assertions.assertEquals("Opponent won!", SFBeatsTOAK.get(2));

        ArrayList<String> SFBeatsTP = gameResults.get(30);
        Assertions.assertEquals("Opponent won!", SFBeatsTP.get(2));

        ArrayList<String> SFBeatsOP = gameResults.get(31);
        Assertions.assertEquals("Opponent won!", SFBeatsOP.get(2));

        ArrayList<String> SFBeatsHC = gameResults.get(32);
        Assertions.assertEquals("Opponent won!", SFBeatsHC.get(2));
    }

    @Test
    public void FOAKBeatsAllButRFSF () {
        ArrayList<String> FOAKLoseToRF = gameResults.get(33);
        Assertions.assertEquals("AIP won!", FOAKLoseToRF.get(2));

        ArrayList<String> FOAKLoseToSF = gameResults.get(34);
        Assertions.assertEquals("AIP won!", FOAKLoseToSF.get(2));

        ArrayList<String> FOAKBeatsFH = gameResults.get(35);
        Assertions.assertEquals("Opponent won!", FOAKBeatsFH.get(2));

        ArrayList<String> FOAKBeatsF = gameResults.get(36);
        Assertions.assertEquals("Opponent won!", FOAKBeatsF.get(2));

        ArrayList<String> FOAKBeatsS = gameResults.get(37);
        Assertions.assertEquals("Opponent won!", FOAKBeatsS.get(2));

        ArrayList<String> FOAKBeatsTOAK = gameResults.get(38);
        Assertions.assertEquals("Opponent won!", FOAKBeatsTOAK.get(2));

        ArrayList<String> FOAKBeatsTP = gameResults.get(39);
        Assertions.assertEquals("Opponent won!", FOAKBeatsTP.get(2));

        ArrayList<String> FOAKBeatsOP = gameResults.get(40);
        Assertions.assertEquals("Opponent won!", FOAKBeatsOP.get(2));

        ArrayList<String> FOAKBeatsHC = gameResults.get(41);
        Assertions.assertEquals("Opponent won!", FOAKBeatsHC.get(2));
    }

    @Test
    public void FHBeatsAllButRFSFFK () {
        ArrayList<String> FHLoseToRF = gameResults.get(42);
        Assertions.assertEquals("AIP won!", FHLoseToRF.get(2));

        ArrayList<String> FHLoseToSF = gameResults.get(43);
        Assertions.assertEquals("AIP won!", FHLoseToSF.get(2));

        ArrayList<String> FHLoseToFH = gameResults.get(44);
        Assertions.assertEquals("AIP won!", FHLoseToFH.get(2));

        ArrayList<String> FHBeatsF = gameResults.get(45);
        Assertions.assertEquals("Opponent won!", FHBeatsF.get(2));

        ArrayList<String> FHBeatsS = gameResults.get(46);
        Assertions.assertEquals("Opponent won!", FHBeatsS.get(2));

        ArrayList<String> FHBeatsTOAK = gameResults.get(47);

        for(String string : FHBeatsTOAK) {
            System.out.println(string);
        }
        Assertions.assertEquals("Opponent won!", FHBeatsTOAK.get(2));

        ArrayList<String> FHBeatsTP = gameResults.get(48);
        Assertions.assertEquals("Opponent won!", FHBeatsTP.get(2));

        ArrayList<String> FHBeatsOP = gameResults.get(49);
        Assertions.assertEquals("Opponent won!", FHBeatsOP.get(2));

        ArrayList<String> FHBeatsHC = gameResults.get(50);
        Assertions.assertEquals("Opponent won!", FHBeatsHC.get(2));
    }

    @Test
    public void FlushBeatsAllButRFSFFKFH () {
        ArrayList<String> FlushLoseToRF = gameResults.get(51);
        Assertions.assertEquals("AIP won!", FlushLoseToRF.get(2));

        ArrayList<String> FlushLoseToSF = gameResults.get(52);
        Assertions.assertEquals("AIP won!", FlushLoseToSF.get(2));

        ArrayList<String> FlushLoseToFOAK = gameResults.get(53);
        Assertions.assertEquals("AIP won!", FlushLoseToFOAK.get(2));

        ArrayList<String> FlushLoseToFH = gameResults.get(54);
        Assertions.assertEquals("AIP won!", FlushLoseToFH.get(2));

        ArrayList<String> FlushBeatsS = gameResults.get(55);
        Assertions.assertEquals("Opponent won!", FlushBeatsS.get(2));

        ArrayList<String> FlushBeatsTOAK = gameResults.get(56);
        Assertions.assertEquals("Opponent won!", FlushBeatsTOAK.get(2));

        ArrayList<String> FlushBeatsTP = gameResults.get(57);
        Assertions.assertEquals("Opponent won!", FlushBeatsTP.get(2));

        ArrayList<String> FlushBeatsOP = gameResults.get(58);
        Assertions.assertEquals("Opponent won!", FlushBeatsOP.get(2));

        ArrayList<String> FlushBeatsHC = gameResults.get(59);
        Assertions.assertEquals("Opponent won!", FlushBeatsHC.get(2));
    }

    @Test
    public void straightBeatsAllButRFSFFKFHF () {
        ArrayList<String> straightLoseToRF = gameResults.get(60);
        Assertions.assertEquals("AIP won!", straightLoseToRF.get(2));

        ArrayList<String> straightLoseToSF = gameResults.get(61);
        Assertions.assertEquals("AIP won!", straightLoseToSF.get(2));

        ArrayList<String> straightLoseToFOAK = gameResults.get(62);
        Assertions.assertEquals("AIP won!", straightLoseToFOAK.get(2));

        ArrayList<String> straightLoseToFH = gameResults.get(63);
        Assertions.assertEquals("AIP won!", straightLoseToFH.get(2));

        ArrayList<String> straightLoseToFlush = gameResults.get(64);
        Assertions.assertEquals("AIP won!", straightLoseToFlush.get(2));

        ArrayList<String> straightBeatsTOAK = gameResults.get(65);
        Assertions.assertEquals("Opponent won!", straightBeatsTOAK.get(2));

        ArrayList<String> straightBeatsTP = gameResults.get(66);
        Assertions.assertEquals("Opponent won!", straightBeatsTP.get(2));

        ArrayList<String> straightBeatsOP = gameResults.get(67);
        Assertions.assertEquals("Opponent won!", straightBeatsOP.get(2));

        ArrayList<String> straightBeatsHC = gameResults.get(68);
        Assertions.assertEquals("Opponent won!", straightBeatsHC.get(2));
    }

    @Test
    public void TOAKBeatsTPOPHC () {
        ArrayList<String> TOAKBeatsTP = gameResults.get(69);
        Assertions.assertEquals("Opponent won!", TOAKBeatsTP.get(2));

        ArrayList<String> TOAKBeatsOP = gameResults.get(70);
        Assertions.assertEquals("Opponent won!", TOAKBeatsOP.get(2));

        ArrayList<String> TOAKBeatsHC = gameResults.get(71);
        Assertions.assertEquals("Opponent won!", TOAKBeatsHC.get(2));
    }

    @Test
    public void TPBeatsOPHC () {
        ArrayList<String> TPBeatsOP = gameResults.get(72);
        Assertions.assertEquals("Opponent won!", TPBeatsOP.get(2));

        ArrayList<String> TPBeatsHC = gameResults.get(73);
        Assertions.assertEquals("Opponent won!", TPBeatsHC.get(2));
    }

    @Test
    public void OPBeatsHC () {
        ArrayList<String> OPBeatsHC = gameResults.get(74);
        Assertions.assertEquals("Opponent won!", OPBeatsHC.get(2));
    }

    @Test
    public void TwoRFBetterSuitWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(75);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoSFWithDistinctHighCardBetterHighCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(76);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoSFWithSameHighCardBetterSuitWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(77);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoFOAKBetterHighCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(78);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoFHBetterHighCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(79);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoTOAKBetterHighCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(80);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoStraightWithSameHighRankBetterSuitWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(81);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoStraightBetterHighCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(82);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoTPWithSameHighRankBetterSuitWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(83);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoTPBetterHighCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(84);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoOPWithSameHighRankBetterSuitWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(85);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoOPBetterHighCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(86);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoHCWithSameHighRankBetterSuitWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(87);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoHCBetterHighCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(88);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoFWithAllSameRankBetterSuitOfHighCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(89);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoFWithSameRankOf4CardsBetterRankOf5thCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(90);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoFWithSameRankOf3CardsBetterRankOf4thCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(91);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoFWithSameRankOf2CardsBetterRankOf3thCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(92);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }

    @Test
    public void TwoFWithSameRankOf1CardsBetterRankOf2thCardWins () {
        ArrayList<String> OpBeatsAip = gameResults.get(92);
        Assertions.assertEquals("Opponent won!", OpBeatsAip.get(2));
    }
}
