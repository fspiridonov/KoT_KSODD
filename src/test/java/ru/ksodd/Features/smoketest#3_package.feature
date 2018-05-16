@smokeTest#3

Feature: smokeTest#3_package

  Scenario: Authorization
    When autorization with login "test2" and password "123456"



  Scenario: Authorization
    When logout
    When autorization with login "test1" and password "123456"

  Scenario: test package drop
    When test package drop

    Scenario: Authorization
    When logout
    When autorization with login "test2" and password "123456"

  Scenario: test package
    When test inside create package


