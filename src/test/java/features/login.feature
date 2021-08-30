Feature:application login

#  Background:
#    Given browser is opened
#    When browser cache clean triggered
#    Then browser started as fresh

  @mobile
  Scenario: Home page default login
    Given User is on netbanking login page
    When User log in into application using "joe" and "joe123"
    Then Homepage is populated
    And Cards displayed "true"

  @sanity @reg
  Scenario: Home page default login
    Given User is on netbanking login page
    When User log in into application using "tom" and "tom123"
    Then Homepage is populated
    And Cards displayed "false"

  @mobile
  Scenario: Home page default login
    Given User is on netbanking login page
    When User sign up with following details
    | joe | root | England | 9999454510 | 25 | joe@gmail.com |
    Then Homepage is populated
    And Cards displayed "false"

  @smoke @sanity @mobile
  Scenario Outline: Home page default login
    Given User is on netbanking login page
    When User log in into application with <Username> and <Password>
    Then Homepage is populated
    And Cards displayed "false"
    Examples:
    | Username | Password |
    | moe      | moe123   |
    | toe      | toe123   |
    | loe      | loe123   |
    | noe      | noe123   |
    | koe      | koe123   |


