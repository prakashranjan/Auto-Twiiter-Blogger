Feature:Celeb tweet data
  Background:
    Given "Chrome" browser is opened

  Scenario: twitter default login
    Given Celeb twitter page is open
    Then Close browser


#  Scenario Outline: twitter default login
#    Given Celeb twitter page is of "<CelebId>"
#    Examples:
#      | CelebId |
#      | BCCI |
#      | imVkohli |
#      | sachin_rt |
#      | virendersehwag |


