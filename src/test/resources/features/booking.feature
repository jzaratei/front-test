# language: en

@test @booking
Feature: Booking
  Like tui user I want to make a hotel reservation

  Scenario Outline: Hotel reservation

    Given I access to tui.url page
    When I select SPAIN as country
    Then I search for <destination> between <date-init> and <date-end> for <number> guest
    And I check the results for <destination> and <number> guest
    When I make a reservation for a <stars> star hotel

    Examples:
      | destination   | date-init    | date-end       |number    | stars |
      | Barcelona    |  16-agosto    | 19-agosto     | 2         |  5  |