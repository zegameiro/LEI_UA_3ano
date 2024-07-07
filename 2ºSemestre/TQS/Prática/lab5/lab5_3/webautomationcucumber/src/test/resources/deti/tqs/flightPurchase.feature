@purchase_flight
Feature: Purchase a flight

  Scenario: A user buys a flight
    Given a user is on the Home Page
    When the user selects 'Mexico City' in the departed city dropdown
    And the user selects 'New York' in the destination city dropdown
    And the user clicks on the Find Flights button
    Then the user gets redirected to the choose flight reservation page
    When the user selects a flight with the number '9696', from the airline company 'Aer Lingus' with a price of $'200.98'
    And the user clicks on the Choose This Flight button
    Then the user gets redirect to the purchase flight page
    When the user fills the purchase flight form with the following data:
      | Name              | John Smith       |
      | Address           | Rua da Paz       |
      | City              | Batalha          |
      | State             | Batalha          |
      | Zip Code          | 3800-520         |
      | Card Type         | Visa             |
      | Card Number       | 1234567890       |
      | Card Expire Month | 08               |
      | Card Expire Year  | 2025             |
      | Name on the Card  | John Smith       |
      | Remember Me       | No               |
    And the user clicks on the Purchase Flight button
    Then the user gets redirected to the confirmation purchase page