Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario: Check main components on header
    Given User opens 'https://www.ebay.com' page
    And User checks that logo is present
    And User checks that category button is present
    And User checks that Sing in button is present
    And User checks that register button is present
    And User checks that Search box is present
    And User checks that cart button is present
    And User checks that notifications button is present
    And User checks that My ebay button is present
    And User checks that watchlist is present
    And User checks that button to change delivery location is present
    And User checks that Help & Contact is present

  Scenario: Check main components on footer
    Given User opens 'https://www.ebay.com' page
    And User checks that button with functionality responsible for buy is present
    And User checks that button with functionality responsible for sell is present
    And User checks that links to social networks is present
    And User checks that About eBay button is present
    And User checks that Help & Contact is present
    And User checks that Community button is present
    And User checks that dropbox site variation is present
    And User checks that Verify site's SSL certificate button is present

  Scenario Outline: Check that Search Result Contains keyword
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks on search button
    Then User checks that Search Result Contains '<keyword>'

    Examples:
      | homePage             | keyword |
      | https://www.ebay.com | dell    |

  Scenario Outline: Check amount items on Search Result Page
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks on search button
    Then User checks that <amount> products are displayed

    Examples:
      | homePage             | keyword | amount |
      | https://www.ebay.com | dell    |   65   |

  Scenario: Check the correctness of the sorting result
    Given User opens 'https://www.ebay.com' page
    And User checks category button visibility
    And User clicks on category button
    And User opens guitar category
    And User clicks on electric guitar button
    And User selects sort by Price + Shipping: lowest first
    Then User checks the correctness of the sorting result

  Scenario Outline: Check registration with invalid email
    Given User opens '<homePage>' page
    And User checks registration button visibility
    And User opens registration page
    And User enters first name '<firstName>'
    And User enters last name '<lastName>'
    And User enters email '<email>'
    And User enters password '<password>'
    Then User sees an error message

    Examples:
      | homePage             | firstName | lastName |      email    | password |
      | https://www.ebay.com | Alex       |   Popov   | testgmail.com | pa$$w0rd |

  Scenario: Check that on register page present SingInButton
    Given User opens 'https://www.ebay.com' page
    And User checks registration button visibility
    And User opens registration page
    Then User checks that on register page present SingInButton

  Scenario Outline: Check registration with password that contains email
    Given User opens '<homePage>' page
    And User checks registration button visibility
    And User opens registration page
    And User enters first name '<firstName>'
    And User enters last name '<lastName>'
    And User enters email '<email>'
    And User enters password '<password>'
    Then User sees an error message about password that contains email

    Examples:
      | homePage             | firstName | lastName |      email     | password |
      | https://www.ebay.com | Alex      |   Popov  | test@gmail.com | test123 |

  Scenario Outline: Check removing items from shopping cart
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks on search button
    And User adds first item to cart
    And User removes item from cart
    And User goes to Home page
    And User opens cart
    Then User checks that cart is empty

    Examples:
      | homePage             |  keyword   |
      | https://www.ebay.com | iPhone 13  |

  Scenario Outline: Check that total sum is correctly calculated
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword1>'
    And User clicks on search button
    And User adds first item to cart
    And User makes search by keyword '<keyword2>'
    And User clicks on search button
    And User adds first item to cart
    Then User checks that total sum is correctly calculated

    Examples:
      | homePage             |   keyword1  |  keyword2  |
      | https://www.ebay.com | huawei p40  | iPhone 13  |