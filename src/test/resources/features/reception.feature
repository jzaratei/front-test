# language: en

@test @reception
Feature: reception
  Like tui user I want to select a country

  Scenario Outline: country selection

    Given I access to tui.url page
    When I select <country> as country
    Then I check home page for <country> user

    @spain
    Examples:
      | country   |
      | SPAIN    |

    @portugal
    Examples:
      | country   |
      | PORTUGAL    |