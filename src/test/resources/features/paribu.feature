@paribuUI
Feature:


  Scenario: Test 1
    Given Go to the Paribu homepage.
    When It is confirmed that we are on the Paribu homepage.
    When Cookies are accepted.
    When the Markets heading is selected.
    When the FAN heading is selected on the page that opens.
    And  Select the token in the 3rd row on the FAN TOKEN page.
    Then On the newly opened page, enter the Current Price in the Unit Price field on the Buy-Sell panel.
    And Enter the number 5 as the Amount.
    When The total price is calculated.
    Then The Total Price field should show the correct mathematical value.
    And the total amount should be calculated using the entered values.



  Scenario: Tes 2
    Given Go to the Paribu homepage.
    When It is confirmed that we are on the Paribu homepage.
    Then Click the Login button in the menu.
    And The user enters their phoneNumber.
    And They enter their Password value.
    And Click the Login button.
    And Assert that the error message for incorrect credentials appears and verify
    And Closed the browser.





