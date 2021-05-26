@ApiTests
Feature: Api testing with help of Cucumber

  Scenario: Get order by id
    Given order id is 1
    When user tries to get order by id
    Then user receives status code 200
    And response not equal to zero


  Scenario: Get order by id FAIL
    Given order id is 0
    When user tries to get order by id
    Then user receives status code 200
    And response not equal to zero


  Scenario: Get all orders
    When user does request to get all orders
    Then user receives status code 200
    And response not equal to zero


  Scenario: Create Order
    Given user tries to create login with next data
    | name       | price |
    | Test Order | 783   |
    When user tries to create order
    Then user receives status code 200


  Scenario: Update Order
    Given user tries to update login with next data
      | id | name       | price |
      | 2  | Test Order | 783   |
    When user tries to update order
    Then user receives status code 200


  Scenario: Delete existing order by id
    Given user does request to get all orders
    When user take last id
    When user tries to delete order
    Then user receives status code 200


  Scenario: Delete non-existing order by id
    Given order id is 0
    When user tries to delete order
    Then user receive message "No such order by this ID."
