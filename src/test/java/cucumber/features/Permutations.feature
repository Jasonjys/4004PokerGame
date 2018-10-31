Feature: Test Permutations

  Background:
    Given Create a Poker Game Play

  Scenario Outline: Correctly detect <Hand> with permutations of <Cards>
    Given player "<Cards>"
    Then  "<Hand>" is detected

    Examples:
      | Hand           | Cards           |
      | royal flush    | H10 HJ HQ HK HA |
      | royal flush    | HJ H10 HK HQ HA |
      | royal flush    | HA HK HQ HJ H10 |
      | royal flush    | HQ HK H10 HA HJ |
      | royal flush    | HQ HJ HA HK H10 |
      | royal flush    | HK HJ HA H10 HQ |
      | straight flush | H9 H10 HJ HQ HK |
      | straight flush | H10 H9 HK HJ HQ |
      | straight flush | HJ HK H9 HQ H10 |
      | straight flush | HK H10 HJ H9 HQ |
      | straight flush | HQ H10 HK H9 HJ |
      | straight flush | HK H10 HJ HQ H9 |
      | 4 of a kind    | C3 D3 H3 S3 C2  |
      | 4 of a kind    | C3 D3 H3 C2 S3  |
      | 4 of a kind    | C3 D3 H3 S3 C2  |
      | 4 of a kind    | C3 D3 C2 S3 H3  |
      | 4 of a kind    | C3 C2 D3 S3 H3  |
      | 4 of a kind    | C2 C3 D3 S3 H3  |
      | full house     | DA HA CA D5 H5  |
      | full house     | CA D5 H5 DA HA  |
      | full house     | D5 H5 CA DA HA  |
      | full house     | CA DA D5 HA H5  |
      | full house     | D5 CA DA HA H5  |
      | full house     | CA D5 DA HA H5  |
      | flush          | D8 D7 D4 DQ DK  |
      | flush          | D7 D8 D4 DK DQ  |
      | flush          | D4 D7 D8 DQ DK  |
      | flush          | DQ D7 DK D4 D8  |
      | flush          | DK D7 D8 DQ D4  |
      | flush          | DQ DK D7 D8 D4  |
      | straight       | CK DJ CQ S9 D10 |
      | straight       | DJ CK CQ D10 S9 |
      | straight       | DJ CQ S9 D10 CK |
      | straight       | CQ DJ S9 CK D10 |
      | straight       | CK S9 CQ D10 DJ |
      | straight       | D10 CK DJ CQ S9 |
      | 3 of a kind    | C8 H8 S8 D9 D2  |
      | 3 of a kind    | C8 D2 H8 D9 S8  |
      | 3 of a kind    | C8 D2 H8 S8 D9  |
      | 3 of a kind    | D2 H8 C8 D9 S8  |
      | 3 of a kind    | D9 D2 C8 H8 S8  |
      | 3 of a kind    | D2 C8 D9 S8 H8  |
      | 2 pairs        | H6 D6 H4 C4 CJ  |
      | 2 pairs        | H4 CJ H6 D6 C4  |
      | 2 pairs        | H4 C4 H6 D6 CJ  |
      | 2 pairs        | H4 H6 CJ D6 C4  |
      | 2 pairs        | H6 H4 CJ D6 C4  |
      | 2 pairs        | CJ H4 H6 C4 D6  |
      | 1 pair         | S5 C5 H2 S4 C6  |
      | 1 pair         | H2 S4 S5 C5 C6  |
      | 1 pair         | C5 H2 S4 S5 C6  |
      | 1 pair         | H2 S4 S5 C6 C5  |
      | 1 pair         | S5 S4 C5 C6 H2  |
      | 1 pair         | C6 C5 H2 S4 S5  |
      | high card      | S2 S6 C9 S4 DQ  |
      | high card      | S6 C9 S4 DQ S2  |
      | high card      | C9 S4 DQ S2 S6  |
      | high card      | S4 DQ S2 S6 C9  |
      | high card      | DQ S2 S6 C9 S4  |
      | high card      | S6 S2 C9 DQ S4  |
