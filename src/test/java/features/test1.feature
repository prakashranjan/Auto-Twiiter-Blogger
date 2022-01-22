Feature:application login

  Background:
    Given "Chrome" browser is opened


  @mobile
  Scenario: Home page default login
    Given User is on netbanking login page
    When User log in into application using "joe" and "joe123"
    Then Homepage is populated
    And Cards displayed "true"

#  @sanity @reg
#  Scenario: Home page default login
#    Given User is on netbanking login page
#    When User log in into application using "tom" and "tom123"
#    Then Homepage is populated
#    And Cards displayed "false"
#

