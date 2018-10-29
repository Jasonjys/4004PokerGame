Feature: PokerGamePlay

  Scenario Outline: <Player1> has <Hand1> beats <Player2> has <Hand2>
    Given Create a Poker Game Play
    When "<Player1>" has "<Hand1>"
    And "<Player2>" has "<Hand2>"
    Then winner is "<Winner>"

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

  Scenario: AIP has a royal flush, it does not exchange any of its cards
    Given Create a Poker Game Play
    When AIP has "S10 SK SA SQ SJ"
    Then AIP does not exchange any of its cards

  Scenario: AIP has a straight flush, it does not exchange any of its cards
    Given Create a Poker Game Play
    When AIP has "S6 S8 S7 S10 S9"
    Then AIP does not exchange any of its cards

  Scenario: AIP has a 4 of a kind, it does not exchange any of its cards
    Given Create a Poker Game Play
    When AIP has "S7 S10 D7 C7 H7"
    Then AIP does not exchange any of its cards

  Scenario: AIP has a full house, it does not exchange any of its cards
    Given Create a Poker Game Play
    When AIP has "S7 D7 C7 H10 S10"
    Then AIP does not exchange any of its cards

  Scenario: AIP has a flush, it does not exchange any of its cards
    Given Create a Poker Game Play
    When AIP has "S4 SA S5 S2 S9"
    Then AIP does not exchange any of its cards

  Scenario: AIP has a straight, it does not exchange any of its cards
    Given Create a Poker Game Play
    When AIP has "C9 S8 H10 DJ SQ"
    Then AIP does not exchange any of its cards

  Scenario: AIP is 1 card away from a royal flush, exchanges that card then WINS with royal flush
    Given Create a Poker Game Play
    When HTB has "S4 SA S5 S2 S9"
    And AIP has "SJ SA SQ D2 S10"
    Then AIP exchanges "[D2]"
    And AIP gets "SK" after exchanging
    And AIP wins

  Scenario: AIP is 1 card away from a royal flush, exchanges that card then LOSES without getting royal flush
    Given Create a Poker Game Play
    When HTB has "DJ DA DQ DK D10"
    And AIP has "SJ SA SQ D2 S10"
    Then AIP exchanges "[D2]"
    And AIP gets "S5" after exchanging
    And AIP loses

  Scenario: AIP is 1 card away from a straight flush, exchanges that card then WINS with straight flush
    Given Create a Poker Game Play
    When HTB has "S4 SA S5 S2 S9"
    And AIP has "DA D4 D3 D5 SQ"
    Then AIP exchanges "[SQ]"
    And AIP gets "D2" after exchanging
    And AIP wins

  Scenario: AIP is 1 card away from a straight flush, exchanges that card then LOSES without getting straight flush
    Given Create a Poker Game Play
    When HTB has "S10 SK SA SQ SJ"
    And AIP has "DA D4 D3 D5 SQ"
    Then AIP exchanges "[SQ]"
    And AIP gets "H7" after exchanging
    And AIP loses

  Scenario: AIP has 3 of a kind, exchanges lowest rank card then WINS with full house
    Given Create a Poker Game Play
    When HTB has "CA D5 H5 DA CA"
    And AIP has "C8 H7 H8 D6 S8"
    Then AIP exchanges "[D6]"
    And AIP gets "C7" after exchanging
    And AIP wins

  Scenario: AIP has 3 of a kind, exchanges lowest rank card then LOSES with 3 of a kind
    Given Create a Poker Game Play
    When HTB has "CA D5 H5 DA CA"
    And AIP has "C8 H7 H8 D6 S8"
    Then AIP exchanges "[D6]"
    And AIP gets "D9" after exchanging
    And AIP loses

  Scenario: AIP has 2 pairs, exchanges other card then WINS with full house
    Given Create a Poker Game Play
    When HTB has "S4 DJ D9 SA C2"
    And AIP has "C8 D7 H6 S6 S8"
    Then AIP exchanges "[D7]"
    And AIP gets "H8" after exchanging
    And AIP wins

  Scenario: AIP has 2 pairs, exchanges other card then LOSES with 2 pairs
    Given Create a Poker Game Play
    When HTB has "CA D5 H5 DA CA"
    And AIP has "C8 D7 H6 S6 S8"
    Then AIP exchanges "[D7]"
    And AIP gets "H10" after exchanging
    And AIP loses

  Scenario: AIP is 1 card away from a flush and exchanges that card then WINS with flush
    Given Create a Poker Game Play
    When HTB has "C8 D7 H6 D6 S8"
    And AIP has "HA S4 H7 H5 HQ"
    Then AIP exchanges "[S4]"
    And AIP gets "H2" after exchanging
    And AIP wins

  Scenario: AIP is 1 card away from a flush and exchanges that card then LOSES without getting flush
    Given Create a Poker Game Play
    When HTB has "S10 SK SA SQ SJ"
    And AIP has "HA S4 H7 H5 HQ"
    Then AIP exchanges "[S4]"
    And AIP gets "S7" after exchanging
    And AIP loses

  Scenario: AIP is 1 card away from a straight, exchanges that card then WINS with straight
    Given Create a Poker Game Play
    When HTB has "C8 D7 H6 D6 S8"
    And AIP has "HQ C10 DJ C9 S4"
    Then AIP exchanges "[S4]"
    And AIP gets "DK" after exchanging
    And AIP wins

  Scenario: AIP is 1 card away from a straight, exchanges that card then LOSES without getting straight
    Given Create a Poker Game Play
    When HTB has "C8 D7 H6 D6 S8"
    And AIP has "HQ C10 DJ C9 S4"
    Then AIP exchanges "[S4]"
    And AIP gets "H2" after exchanging
    And AIP loses

  Scenario: AIP is 1 card away from a full house case1, exchanges that card
    Given Create a Poker Game Play
    When AIP has "H5 S10 S5 D5 SQ"
    Then AIP exchanges "[S10]"

  Scenario: AIP is 1 card away from a full house case2, exchanges that card
    Given Create a Poker Game Play
    When AIP has "H5 S10 S5 DQ SQ"
    Then AIP exchanges "[S10]"

  Scenario: AIP is 1 card away from a flush, exchanges that card
    Given Create a Poker Game Play
    When AIP has "HA S4 H7 H5 HQ"
    Then AIP exchanges "[S4]"

  Scenario: AIP has 3 cards of the same suit, exchanges its 2 other cards
    Given Create a Poker Game Play
    When AIP has "SK S2 H5 C8 S10"
    Then AIP exchanges "[H5, C8]"

  Scenario: AIP has 3 cards in sequence, it exchanges its 2 other cards
    Given Create a Poker Game Play
    When AIP has "H7 S8 C9 S2 DQ"
    Then AIP exchanges "[S2, DQ]"

  Scenario: AIP has 1 pair, it exchanges its 2 other cards
    Given Create a Poker Game Play
    When AIP has "SK D3 H10 C3 D4"
    Then AIP exchanges "[D4, H10, SK]"

  Scenario: AIP has high card, it keeps its 2 highest-ranked cards and exchanges the 3 others
    Given Create a Poker Game Play
    When AIP has "SK D3 H10 C7 D4"
    Then AIP exchanges "[D3, D4, C7]"

  Scenario: Both HTB and AIP have a royal flush, the one with highest suit wins
    Given Create a Poker Game Play
    When AIP has "H10 HJ HQ HK HA"
    And HTB has "CJ C10 CA CQ CK"
    Then AIP wins

  Scenario: Both HTB and AIP have a straight flush with distinct highest card, the one with the highest card wins
    Given Create a Poker Game Play
    When AIP has "D3 D4 D5 D6 D7"
    And HTB has "H2 H6 H3 H4 H5"
    Then AIP wins

  Scenario: Both HTB and AIP have straight flush with the same highest card, the one with the highest suit wins
    Given Create a Poker Game Play
    When AIP has "S3 S4 S5 S6 S7"
    And HTB has "D3 D4 D5 D6 D7"
    Then AIP wins

  Scenario: Both HTB and AIP have 4 of a kind, the one with the highest card of the 2 quadruplets wins
    Given Create a Poker Game Play
    When AIP has "DQ SQ D6 CQ HQ"
    And HTB has "HJ DJ SJ CJ C10"
    Then AIP wins

  Scenario: Both HTB and AIP have full house, the one with the highest card of the 2 triplets wins
    Given Create a Poker Game Play
    When AIP has "D8 D4 C8 S8 S4"
    And HTB has "S7 D7 HK C7 DK"
    Then AIP wins

  Scenario: Both HTB and AIP have 3 of a kind, the one with the highest card of the 2 triplets wins
    Given Create a Poker Game Play
    When AIP has "D8 D4 C8 S8 S4"
    And HTB has "S7 D7 HK C7 DK"
    Then AIP wins

  Scenario: Both HTB and AIP have straight with the same rank for their highest card, highest suit of that highest card wins
    Given Create a Poker Game Play
    When AIP has "C9 S8 H10 DJ SQ"
    And HTB has "H9 D8 C10 SJ CQ"
    Then AIP wins

  Scenario: Both HTB and AIP have straight with distinct highest card, highest rank highest card wins
    Given Create a Poker Game Play
    When AIP has "S10 CJ SQ SK SA"
    And HTB has "S4 DA D3 H5 C2"
    Then AIP wins

  Scenario: Both HTB and AIP have 2 pairs with the same highest pair, highest suit of this highest pairs wins
    Given Create a Poker Game Play
    When HTB has "C9 S9 CJ DJ SQ"
    And AIP has "SJ HJ H4 D6 S7"
    And AIP exchanges "[H4, D6, S7]"
    And AIP gets "S8 D8 H2" after exchanging
    Then AIP wins

  Scenario: Both HTB and AIP have 2 pairs with the same highest pair, highest suit of this highest pairs wins
    Given Create a Poker Game Play
    When HTB has "C9 S9 CJ DJ SQ"
    And AIP has "SJ HJ H4 D6 S7"
    And AIP exchanges "[H4, D6, S7]"
    And AIP gets "S8 D8 H2" after exchanging
    Then AIP wins

  Scenario: Both HTB and AIP have 2 pairs with distinct highest pairs, highest rank of this highest pairs wins
    Given Create a Poker Game Play
    When HTB has "C9 S9 H10 S10 SQ"
    And AIP has "C8 D7 H4 D3 S8"
    And AIP exchanges "[D3, H4, D7]"
    And AIP gets "HJ DJ SA" after exchanging
    Then AIP wins

  Scenario: Both HTB and AIP have 1 pair with equal rank, highest suit of the pairs wins
    Given Create a Poker Game Play
    When HTB has "C2 H2 H4 D7 SQ"
    And AIP has "SJ D3 C5 S2 D2"
    And AIP exchanges "[D3, C5, SJ]"
    And AIP gets "H9 D10 HQ" after exchanging
    Then AIP wins

  Scenario: Both HTB and AIP have 1 pair with distinct-ranked pairs, highest rank of the pairs wins
    Given Create a Poker Game Play
    When HTB has "CJ SJ H4 D7 SQ"
    And AIP has "S4 SK DK C5 C2"
    And AIP exchanges "[C2, S4, C5]"
    And AIP gets "D2 H9 D10" after exchanging
    Then AIP wins

  Scenario: Both HTB and AIP have high card with same rank for the highest card, highest suit of the highest card wins
    Given Create a Poker Game Play
    When HTB has "C9 S2 H3 DJ DK"
    And AIP has "SK D3 H10 C7 S4"
    And AIP exchanges "[D3, S4, C7]"
    And AIP gets "S5 CQ D2" after exchanging
    Then AIP wins

  Scenario: Both HTB and AIP have high card with distinct highest card, highest ranked card wins
    Given Create a Poker Game Play
    When HTB has "C9 S2 H3 DJ DQ"
    And AIP has "SK D3 H10 C7 S4"
    And AIP exchanges "[D3, S4, C7]"
    And AIP gets "S5 C6 D2" after exchanging
    Then AIP wins

  Scenario: Both HTB and AIP have flush with the same rank for each of their 5 cards, highest suit of highest card wins
    Given Create a Poker Game Play
    When HTB has "C5 C7 C10 CJ CK"
    And AIP has "H5 H7 H10 HJ HK"
    Then AIP wins

  Scenario: Both HTB and AIP have flush with the same rank for each of their 4 highest cards, highest rank of 5th card wins
    Given Create a Poker Game Play
    When HTB has "CK C10 C7 C4 C2"
    And AIP has "HK H10 H7 H4 H3"
    Then AIP wins

  Scenario: Both HTB and AIP have flush with the same rank for each of their 3 highest cards, highest rank of 4th card wins
    Given Create a Poker Game Play
    When HTB has "CK C10 C7 C4 C2"
    And AIP has "HK H10 H7 H6 H3"
    Then AIP wins

  Scenario: Both HTB and AIP have flush with the same rank for each of their 2 highest cards, highest rank of 3th card wins
    Given Create a Poker Game Play
    When HTB has "CK C10 C7 C4 C2"
    And AIP has "HK H10 H8 H6 H3"
    Then AIP wins

  Scenario: Both HTB and AIP have flush with the same rank for each of their highest cards, highest rank of 2nd card wins
    Given Create a Poker Game Play
    When HTB has "CK C10 C7 C4 C2"
    And AIP has "HK HJ H8 H6 H3"
    Then AIP wins