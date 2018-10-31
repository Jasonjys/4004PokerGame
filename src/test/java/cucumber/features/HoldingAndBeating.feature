Feature: Test Beating and Holding

  Background:
    Given Create a Poker Game Play

  Scenario Outline: <Player1> <Hand1> beats <Player2> <Hand2>
    When "<Player1>" has "<Hand1>"
    And "<Player2>" has "<Hand2>"
    Then The winner is "<Winner>"

    Examples:
      | Player1 | Hand1          | Player2 | Hand2          | Winner |
      | HTB     | royal flush    | AIP     | straight flush | HTB    |
      | HTB     | royal flush    | AIP     | 4 of a kind    | HTB    |
      | HTB     | royal flush    | AIP     | full house     | HTB    |
      | HTB     | royal flush    | AIP     | flush          | HTB    |
      | HTB     | royal flush    | AIP     | straight       | HTB    |
      | HTB     | royal flush    | AIP     | 3 of a kind    | HTB    |
      | HTB     | royal flush    | AIP     | 2 pairs        | HTB    |
      | HTB     | royal flush    | AIP     | 1 pair         | HTB    |
      | HTB     | royal flush    | AIP     | high card      | HTB    |
      | HTB     | straight flush | AIP     | 4 of a kind    | HTB    |
      | HTB     | straight flush | AIP     | full house     | HTB    |
      | HTB     | straight flush | AIP     | flush          | HTB    |
      | HTB     | straight flush | AIP     | straight       | HTB    |
      | HTB     | straight flush | AIP     | 3 of a kind    | HTB    |
      | HTB     | straight flush | AIP     | 2 pairs        | HTB    |
      | HTB     | straight flush | AIP     | 1 pair         | HTB    |
      | HTB     | straight flush | AIP     | high card      | HTB    |
      | HTB     | 4 of a kind    | AIP     | full house     | HTB    |
      | HTB     | 4 of a kind    | AIP     | flush          | HTB    |
      | HTB     | 4 of a kind    | AIP     | straight       | HTB    |
      | HTB     | 4 of a kind    | AIP     | 3 of a kind    | HTB    |
      | HTB     | 4 of a kind    | AIP     | 2 pairs        | HTB    |
      | HTB     | 4 of a kind    | AIP     | 1 pair         | HTB    |
      | HTB     | 4 of a kind    | AIP     | high card      | HTB    |
      | HTB     | full house     | AIP     | flush          | HTB    |
      | HTB     | full house     | AIP     | straight       | HTB    |
      | HTB     | full house     | AIP     | 3 of a kind    | HTB    |
      | HTB     | full house     | AIP     | 2 pairs        | HTB    |
      | HTB     | full house     | AIP     | 1 pair         | HTB    |
      | HTB     | full house     | AIP     | high card      | HTB    |
      | HTB     | flush          | AIP     | straight       | HTB    |
      | HTB     | flush          | AIP     | 3 of a kind    | HTB    |
      | HTB     | flush          | AIP     | 2 pairs        | HTB    |
      | HTB     | flush          | AIP     | 1 pair         | HTB    |
      | HTB     | flush          | AIP     | high card      | HTB    |
      | HTB     | straight       | AIP     | 3 of a kind    | HTB    |
      | HTB     | straight       | AIP     | 2 pairs        | HTB    |
      | HTB     | straight       | AIP     | 1 pair         | HTB    |
      | HTB     | straight       | AIP     | high card      | HTB    |
      | HTB     | 3 of a kind    | AIP     | 2 pairs        | HTB    |
      | HTB     | 3 of a kind    | AIP     | 1 pair         | HTB    |
      | HTB     | 3 of a kind    | AIP     | high card      | HTB    |
      | HTB     | 2 pairs        | AIP     | 1 pair         | HTB    |
      | HTB     | 2 pairs        | AIP     | high card      | HTB    |
      | HTB     | 1 pair         | AIP     | high card      | HTB    |

  Scenario Outline: <Player1> has <Hand1>, holds and beats <Player2> <Hand2>
    When "<Player1>" has "<Hand1>"
    And "<Player2>" has "<Hand2>"
    Then "<Player1>" holds
    And The winner is "<Winner>"

    Examples:
      | Player1 | Hand1          | Player2 | Hand2          | Winner |
      | AIP     | royal flush    | HTB     | straight flush | AIP    |
      | AIP     | royal flush    | HTB     | 4 of a kind    | AIP    |
      | AIP     | royal flush    | HTB     | full house     | AIP    |
      | AIP     | royal flush    | HTB     | flush          | AIP    |
      | AIP     | royal flush    | HTB     | straight       | AIP    |
      | AIP     | royal flush    | HTB     | 3 of a kind    | AIP    |
      | AIP     | royal flush    | HTB     | 2 pairs        | AIP    |
      | AIP     | royal flush    | HTB     | 1 pair         | AIP    |
      | AIP     | royal flush    | HTB     | high card      | AIP    |
      | AIP     | straight flush | HTB     | 4 of a kind    | AIP    |
      | AIP     | straight flush | HTB     | full house     | AIP    |
      | AIP     | straight flush | HTB     | flush          | AIP    |
      | AIP     | straight flush | HTB     | straight       | AIP    |
      | AIP     | straight flush | HTB     | 3 of a kind    | AIP    |
      | AIP     | straight flush | HTB     | 2 pairs        | AIP    |
      | AIP     | straight flush | HTB     | 1 pair         | AIP    |
      | AIP     | straight flush | HTB     | high card      | AIP    |
      | AIP     | 4 of a kind    | HTB     | full house     | AIP    |
      | AIP     | 4 of a kind    | HTB     | flush          | AIP    |
      | AIP     | 4 of a kind    | HTB     | straight       | AIP    |
      | AIP     | 4 of a kind    | HTB     | 3 of a kind    | AIP    |
      | AIP     | 4 of a kind    | HTB     | 2 pairs        | AIP    |
      | AIP     | 4 of a kind    | HTB     | 1 pair         | AIP    |
      | AIP     | 4 of a kind    | HTB     | high card      | AIP    |
      | AIP     | full house     | HTB     | flush          | AIP    |
      | AIP     | full house     | HTB     | straight       | AIP    |
      | AIP     | full house     | HTB     | 3 of a kind    | AIP    |
      | AIP     | full house     | HTB     | 2 pairs        | AIP    |
      | AIP     | full house     | HTB     | 1 pair         | AIP    |
      | AIP     | full house     | HTB     | high card      | AIP    |
      | AIP     | flush          | HTB     | straight       | AIP    |
      | AIP     | flush          | HTB     | 3 of a kind    | AIP    |
      | AIP     | flush          | HTB     | 2 pairs        | AIP    |
      | AIP     | flush          | HTB     | 1 pair         | AIP    |
      | AIP     | flush          | HTB     | high card      | AIP    |
      | AIP     | straight       | HTB     | 3 of a kind    | AIP    |
      | AIP     | straight       | HTB     | 2 pairs        | AIP    |
      | AIP     | straight       | HTB     | 1 pair         | AIP    |
      | AIP     | straight       | HTB     | high card      | AIP    |