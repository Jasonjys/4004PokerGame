Feature: Poker Game Play

  Scenario: AIP has a royal flush, it does not exchange any of its cards
    Given Create a Poker Game Play
    When AIP has "S10 SK SA SQ SJ"
    Then AIP does not exchange any of its cards

  Scenario: AIP has a straight flush, it does not exchange any of its cards
    Given Create a Poker Game Play
    When AIP has "S6 S8 S7 S10 S9"
    Then AIP does not exchange any of its cards

  Scenario: AIP has a four of a kind, it does not exchange any of its cards
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

  Scenario: AIP is 1 card away from a royal flush, it exchanges that card
    Given Create a Poker Game Play
    When AIP has "SJ SA SQ D2 S10"
    Then AIP exchanges "[D2]"

  Scenario: AIP is 1 card away from a straight flush, it exchanges that card
    Given Create a Poker Game Play
    When AIP has "DA D4 D3 D5 SQ"
    Then AIP exchanges "[SQ]"

  Scenario: AIP is 1 card away from a full house case1, it exchanges that card
    Given Create a Poker Game Play
    When AIP has "H5 S10 S5 D5 SQ"
    Then AIP exchanges "[S10]"

  Scenario: AIP is 1 card away from a full house case2, it exchanges that card
    Given Create a Poker Game Play
    When AIP has "H5 S10 S5 DQ SQ"
    Then AIP exchanges "[S10]"

  Scenario: AIP is 1 card away from a flush, it exchanges that card
    Given Create a Poker Game Play
    When AIP has "HA S4 H7 H5 HQ"
    Then AIP exchanges "[S4]"

  Scenario: AIP is 1 card away from a straight, it exchanges that card
    Given Create a Poker Game Play
    When AIP has "HQ C10 DJ C9 S4"
    Then AIP exchanges "[S4]"

  Scenario: AIP has 3 cards of the same suit, it exchanges its two other cards
    Given Create a Poker Game Play
    When AIP has "SK S2 H5 C8 S10"
    Then AIP exchanges "[H5, C8]"

  Scenario: AIP has 3 cards in sequence, it exchanges its two other cards
    Given Create a Poker Game Play
    When AIP has "H7 S8 C9 S2 DQ"
    Then AIP exchanges "[S2, DQ]"

  Scenario: AIP has 1 pair, it exchanges its two other cards
    Given Create a Poker Game Play
    When AIP has "SK D3 H10 C3 D4"
    Then AIP exchanges "[D4, H10, SK]"

  Scenario: AIP has high card, it keeps its 2 highest-ranked cards and exchanges the 3 others
    Given Create a Poker Game Play
    When AIP has "SK D3 H10 C7 D4"
    Then AIP exchanges "[D3, D4, C7]"

  Scenario: royal flush beats straight flush
    Given Create a Poker Game Play
    When AIP has "S10 SK SA SQ SJ"
    When Opponent has "C3 CA C4 C5 C2"
    Then AIP wins

  Scenario: straight flush beats 4 of a kind
    Given Create a Poker Game Play
    When AIP has "C3 CA C4 C5 C2"
    When Opponent has "C3 D3 H3 S3 C8"
    Then AIP wins

  Scenario: 4 of a kind beats full house
    Given Create a Poker Game Play
    When AIP has "C3 D3 H3 S3 C2"
    When Opponent has "CA D5 H5 DA CA"
    Then AIP wins

  Scenario: full house beats flush
    Given Create a Poker Game Play
    When AIP has "CA D5 H5 DA CA"
    When Opponent has "D8 D7 D4 DJ DK"
    Then AIP wins

  Scenario: flush beats straight
    Given Create a Poker Game Play
    When AIP has "D8 D7 D4 DJ DK"
    When Opponent has "CK DJ HQ CA D10"
    Then AIP wins

  Scenario: straight beats 3 of a kind
    Given Create a Poker Game Play
    When AIP has "CK DJ HQ CA D10"
    When Opponent has "C8 D7 H8 D6 S8"
    Then AIP wins

  Scenario: 3 of a kind beats 2 pairs
    Given Create a Poker Game Play
    When AIP has "C8 D7 H8 D6 S8"
    When Opponent has "C9 D7 H6 D6 S9"
    Then AIP wins

  Scenario: 2 paris beats 1 pair
    Given Create a Poker Game Play
    When AIP has "C9 D7 H6 D6 S9"
    When Opponent has "C8 D7 H2 D6 S8"
    Then AIP wins

  Scenario: 1 pair beats high card
    Given Create a Poker Game Play
    When AIP has "C8 D7 H2 D6 S8"
    When Opponent has "S4 SJ D9 HA C2"
    Then AIP wins

  Scenario: 1 pair lose to royal flush
    Given Create a Poker Game Play
    When AIP has "S10 SK SA SQ SJ"
    When Opponent has "C8 D7 H2 D6 S8"
    Then AIP wins