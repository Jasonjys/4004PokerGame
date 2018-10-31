Feature: Test Same Combination

  Background:
    Given Create a Poker Game Play

  Scenario: Both HTB and AIP have a royal flush, the one with highest suit wins
    When AIP has "H10 HJ HQ HK HA"
    And HTB has "CJ C10 CA CQ CK"
    Then AIP wins

  Scenario: Both HTB and AIP have a straight flush with distinct highest card, the one with the highest card wins
    When AIP has "D3 D4 D5 D6 D7"
    And HTB has "H2 H6 H3 H4 H5"
    Then AIP wins

  Scenario: Both HTB and AIP have straight flush with the same highest card, the one with the highest suit wins
    When AIP has "S3 S4 S5 S6 S7"
    And HTB has "D3 D4 D5 D6 D7"
    Then AIP wins

  Scenario: Both HTB and AIP have 4 of a kind, the one with the highest card of the 2 quadruplets wins
    When AIP has "DQ SQ D6 CQ HQ"
    And HTB has "HJ DJ SJ CJ C10"
    Then AIP wins

  Scenario: Both HTB and AIP have full house, the one with the highest card of the 2 triplets wins
    When AIP has "D8 D4 C8 S8 S4"
    And HTB has "S7 D7 HK C7 DK"
    Then AIP wins

  Scenario: Both HTB and AIP have 3 of a kind, the one with the highest card of the 2 triplets wins
    When AIP has "D8 D4 C8 S8 S4"
    And HTB has "S7 D7 HK C7 DK"
    Then AIP wins

  Scenario: Both HTB and AIP have straight with the same rank for their highest card, highest suit of that highest card wins
    When AIP has "C9 S8 H10 DJ SQ"
    And HTB has "H9 D8 C10 SJ CQ"
    Then AIP wins

  Scenario: Both HTB and AIP have straight with distinct highest card, highest rank highest card wins
    When AIP has "S10 CJ SQ SK SA"
    And HTB has "S4 DA D3 H5 C2"
    Then AIP wins

  Scenario: Both HTB and AIP have 2 pairs with the same highest pair, highest suit of this highest pairs wins
    Given No replacements
    When HTB has "C9 S9 CJ DJ SQ"
    And AIP has "SJ HJ S8 D8 H2"
    Then AIP wins

  Scenario: Both HTB and AIP have 2 pairs with the same highest pair, highest suit of this highest pairs wins
    Given No replacements
    When HTB has "C9 S9 CJ DJ SQ"
    And AIP has "SJ HJ S8 D8 H2"
    Then AIP wins

  Scenario: Both HTB and AIP have 2 pairs with distinct highest pairs, highest rank of this highest pairs wins
    Given No replacements
    When HTB has "C9 S9 H10 S10 SQ"
    And AIP has "C8 HJ DJ SA S8"
    Then AIP wins

  Scenario: Both HTB and AIP have 1 pair with equal rank, highest suit of the pairs wins
    Given No replacements
    When HTB has "C2 H2 H4 D7 SQ"
    And AIP has "H9 D10 HQ S2 D2"
    Then AIP wins

  Scenario: Both HTB and AIP have 1 pair with distinct-ranked pairs, highest rank of the pairs wins
    Given No replacements
    When HTB has "CJ SJ H4 D7 SQ"
    And AIP has "SK DK D2 H9 D10"
    Then AIP wins

  Scenario: Both HTB and AIP have high card with same rank for the highest card, highest suit of the highest card wins
    Given No replacements
    When HTB has "C9 S2 H3 DJ DK"
    And AIP has "SK H10 S5 CQ D2"
    Then AIP wins

  Scenario: Both HTB and AIP have high card with distinct highest card, highest ranked card wins
    Given No replacements
    When HTB has "C9 S2 H3 DJ DQ"
    And AIP has "SK H10 S5 C6 D2"
    Then AIP wins

  Scenario: Both HTB and AIP have flush with the same rank for each of their 5 cards, highest suit of highest card wins
    When HTB has "C5 C7 C10 CJ CK"
    And AIP has "H5 H7 H10 HJ HK"
    Then AIP wins

  Scenario: Both HTB and AIP have flush with the same rank for each of their 4 highest cards, highest rank of 5th card wins
    When HTB has "CK C10 C7 C4 C2"
    And AIP has "HK H10 H7 H4 H3"
    Then AIP wins

  Scenario: Both HTB and AIP have flush with the same rank for each of their 3 highest cards, highest rank of 4th card wins
    When HTB has "CK C10 C7 C4 C2"
    And AIP has "HK H10 H7 H6 H3"
    Then AIP wins

  Scenario: Both HTB and AIP have flush with the same rank for each of their 2 highest cards, highest rank of 3th card wins
    When HTB has "CK C10 C7 C4 C2"
    And AIP has "HK H10 H8 H6 H3"
    Then AIP wins

  Scenario: Both HTB and AIP have flush with the same rank for each of their highest cards, highest rank of 2nd card wins
    When HTB has "CK C10 C7 C4 C2"
    And AIP has "HK HJ H8 H6 H3"
    Then AIP wins