# language: en

@test @search
Feature: search actions
  I want to filter my search result

  @orderBy
  Scenario Outline: order by price/rating/stars

    Given I access to tui.url page
    When I select SPAIN as country
    And I go to Explorar page
    Then I check offers page
    And I order the offers by <orderType>

    @price
    Examples:
      | orderType   |
      | price       |

    @rating
    Examples:
      | orderType   |
      | rating      |

    #@stars
    #Examples:
    #  | orderType   |
    #  | stars       |
