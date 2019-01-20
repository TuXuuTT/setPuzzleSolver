Feature:
  As a user I want to open puzzle page, successfully solve it and check that I am redirected to 'You have completed today's Puzzle' page

  Scenario: Check successful puzzle solving leads to 'You've completed' page
    Given user has opened Set Puzzle home page
    Then current page title has text The Daily SET Puzzle
    When user finds and clicks all correct sets
    Then current page title has text You completed today's puzzle