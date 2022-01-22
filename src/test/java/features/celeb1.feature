Feature:Celeb tweet data
  Background:
    Given "Chrome" browser is opened


  @mobile
  Scenario: twitter default login
    Given Celeb twitter page is of "sachin_rt"
    When User log in into application using "joe" and "joe123"
    Then Homepage is populated
    And Cards displayed "true"



