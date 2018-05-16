@smokeTest#1

Feature: smokeTest#1_initiative

  Scenario: Authorization
    When autorization with login "test2" and password "123456"

  Scenario: create negative value initiative
#    When filter test
#    When try to create with an invalid value untrue
 #   Then test negative value

  Scenario: create new initiative
    When create new initiative with "Мясницкая улица" street
    Then test new initiative creating
#
#  Scenario: edit initiative
#    When edit initiative
#    Then test initiative edition



#  Scenario Outline: reject initiative
#    When delete "<index>" initiative
#    Then test "<index>" initiative deletion
#
#    Examples:
#      | index |
#      | 0     |
##      | 1     |
##      | 2     |

    Scenario: logout
      When logout





