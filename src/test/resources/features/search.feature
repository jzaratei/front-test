# language: en

@test @search
Feature: Searching
  Like tui user I want to search for a hotel/destination

  @destination
  Scenario Outline: search a travel destination

    Given I access to tui.url page
    When I select SPAIN as country
    Then I search for <destination> between <date-init> and <date-end> for <number> guest
    And I check the results for <destination> and <number> guest

    Examples:
      | destination   | date-init    | date-end       |number    |
      | Barcelona    |  16-agosto    | 19-agosto      | 4       |

  @hotel
  Scenario Outline: search by recommended destinations

    Given I access to tui.url page
    When I select SPAIN as country
    And I select <destination> as recommended
    Then I check <destination> best hotels

    Examples:
      | destination |
      | Portugal   |
