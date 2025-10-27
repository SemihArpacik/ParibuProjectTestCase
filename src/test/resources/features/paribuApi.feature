@api
Feature: DummyJSON Login API Tests


  Scenario Outline: Verify login API responses for different credentials
    Given The user sends a POST request to "https://dummyjson.com/auth/login" with username "<username>" and password "<password>"
    Then The status code should be <statusCode>
    And The response should contain token equals "<expectToken>"

    Examples:
      | username | password     | statusCode | expectToken |
      | emilys   | wrongpass    | 400        | false       |
      | wrongusr | emilyspass   | 400        | false       |
      | wrongusr | wrongpass    | 400        | false       |
      | emilys   | emilyspass   | 200        | true        |



  Scenario: Verify products array length matches limit using saved token
    Given The user successfully logs in.
    When The user has a valid access token
    Then The user sends a GET request to "/auth/products" using the saved access token
    And The response status code should be 200
    And The response should contain a "products" array
    And The number of products should match the "limit" value



  Scenario: Update and delete first product sequentially
    Given The user successfully logs in.
    Given The user has a valid access token
    When The user gets the list of products
    And The user updates the first product's name to "Updated Product Name"
    And The user deletes the updated product

