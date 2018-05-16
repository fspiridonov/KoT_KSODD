@smokeTest#2

Feature: smokeTest#2_request

  Scenario: Authorization
    When autorization with login "test2" and password "123456"

#  Scenario: test rejected request
#    When design checkbox test
#
#    When click on add to work
#    When negative feelform on add to job

  Scenario Outline: create new requests based on initiative
    When create new initiative with "Улица Варварка" street
    When create new request from initiative with type "<type>" and length of "8000"
#    Then test request creation
    When clear array of initiatives
##    TODO НУЖНО СДЕЛАТЬ ПРОВЕРКУ ДЛЯ РАССЧЕТА ДАТ ПРИ СОЗДАНИИ ЗАПРОСОВ

    Examples:
      | type                           |
      | Актуализация (инициатива ЦОДД) |
      | Новое проектирование           |
      | ПОДДы МВК                      |
      | Техническое задание            |


  Scenario Outline: create new request based on initiative by drag&drop
    When create new initiative with "Улица Варварка" street
    When create request from init by drag&drop with type "<type>" and length of "7000"
    When clear array of initiatives
    Examples:
      | type                           |
      | Актуализация (инициатива ЦОДД) |
      | Новое проектирование           |
      | ПОДДы МВК                      |
      | Техническое задание            |



