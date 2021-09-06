# language: en

@test @search
Feature: Searching
  Like front user I want to search

  Scenario Outline: search

    Given I access to test.web.url page
    Then I search for comics
    And I select 2 post
    Then I write down a comment

    Examples:
      | web-url                    |
      | https://es.gizmodo.com     |