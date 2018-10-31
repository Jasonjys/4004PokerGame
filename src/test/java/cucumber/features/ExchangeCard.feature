Feature: Test Strategies

  Background:
    Given Create a Poker Game Play

  Scenario Outline: <Player1> has <Hand1>, exchanges that card and wins/loses with/without getting the hand it wants
    When "<Player1>" has "<Hand1>" "<cards>"
    And "<Player2>" has "<Hand2>"
    And Replacement is "<Replacement>"
    Then "<Player1>" discards "<discardCards>"
    And The winner is "<Winner>"

    Examples:
      | Player1 | Hand1                           | cards           | discardCards | Replacement | Player2 | Hand2          | Winner |
      | AIP     | 1 card away from royal flush    | SJ SA SQ D2 S10 | [D2]         | SK          | HTB     | straight flush | AIP    |
      | AIP     | 1 card away from royal flush    | SJ SA SQ D2 S10 | [D2]         | S5          | HTB     | straight flush | HTB    |
      | AIP     | 1 card away from straight flush | D4 D5 D6 D7 SQ  | [SQ]         | D8          | HTB     | 4 of a kind    | AIP    |
      | AIP     | 1 card away from straight flush | D4 D5 D6 D7 SQ  | [SQ]         | H7          | HTB     | 4 of a kind    | HTB    |
      | AIP     | 3 of a kind                     | C8 H7 H8 D6 S8  | [D6]         | C7          | HTB     | flush          | AIP    |
      | AIP     | 3 of a kind                     | C8 H7 H8 D6 S8  | [D6]         | D9          | HTB     | flush          | HTB    |
      | AIP     | 2 pairs                         | C8 D7 H6 S6 S8  | [D7]         | H8          | HTB     | flush          | AIP    |
      | AIP     | 2 pairs                         | C8 D7 H6 S6 S8  | [D7]         | H10         | HTB     | flush          | HTB    |
      | AIP     | 1 card away from flush          | HA S4 H7 H5 HQ  | [S4]         | H2          | HTB     | straight       | AIP    |
      | AIP     | 1 card away from flush          | HA S4 H7 H5 HQ  | [S4]         | S7          | HTB     | straight       | HTB    |
      | AIP     | 1 card away from straight       | HQ C10 DJ C9 S4 | [S4]         | DK          | HTB     | 3 of a kind    | AIP    |
      | AIP     | 1 card away from straight       | HQ C10 DJ C9 S4 | [S4]         | H2          | HTB     | 3 of a kind    | HTB    |


  Scenario Outline: <Player1> has <Hand1>, exchanges the correct cards and D/C
    When "<Player1>" has "<Hand1>" "<cards>"
    Then "<Player1>" discards "<discardCards>"

    Examples:
      | Player1 | Hand1               | cards           | discardCards  |
      | AIP     | 3 of a suit         | SK S2 H5 C8 S10 | [H5, C8]      |
      | AIP     | 3 cards in sequence | H7 S8 C9 S2 DQ  | [S2, DQ]      |
      | AIP     | 1 pair              | SK D3 H10 C3 D4 | [D4, H10, SK] |
      | AIP     | high card           | SK D3 H10 C7 D4 | [D3, D4, C7]  |