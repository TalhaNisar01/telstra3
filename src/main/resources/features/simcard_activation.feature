Feature: SIM Card Activation

  Scenario: Successfully activating a SIM card
    Given a SIM card with ICCID "1255789453849037777" and customer email "customer1@example.com"
    When I request to activate the SIM card
    Then the activation should be successful
    And I should be able to query the SIM card status and see it as activated

  Scenario: Failing to activate a SIM card
    Given a SIM card with ICCID "8944500102198304826" and customer email "customer2@example.com"
    When I request to activate the SIM card
    Then the activation should fail
    And I should be able to query the SIM card status and see it as not activated
