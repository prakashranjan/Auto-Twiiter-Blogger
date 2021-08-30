Feature:application login

  @smoke @reg
  Scenario: Home page default login
    Given User is on netbanking login page
    When User sign up with following details
    | joe | root | England | 9999454510 | 25 | joe@gmail.com |
    Then Homepage is populated
    And Cards displayed "false"

  @smoke @reg
  Scenario: Home page default login
    Given User is on netbanking login page
    When User sign up with following details
      | joe | root | England | 9999454510 | 25 | joe@gmail.com |
    Then Homepage is populated
    And Cards displayed "true"

