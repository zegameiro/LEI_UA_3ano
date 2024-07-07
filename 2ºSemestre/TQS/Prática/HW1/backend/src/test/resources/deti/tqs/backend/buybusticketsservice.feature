Feature: Use BusWise to buy a ticket

Scenario: User buys a ticket

  Given a user enters on the website and sees the homepage
  When the user selects the departure city 'Castelo Branco'
  And the user selects the destination city 'Figueira da Foz'
  And clicks on the search button
  Then the user sees the available trips
  And clicks on the button choose trip
  Then the user confirms the price of the trips
  And fills in the form with the personal data
  And chooses seat number 14
  And clicks on the button buy reservation