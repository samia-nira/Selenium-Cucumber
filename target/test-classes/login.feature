Feature: User can login to a visual discovery engine site
  Scenario Outline: Verify users can signup to a visual discovery engine site
    Given User visits a visual discovery engine site
    When User enters invalid email "<email>",valid password  "<password>" and age <age> credentials
    Then User cannot signed up

    Examples:
      |email|password|age|
      |samianira@yopmail.com|A|28|
