@testDnD

Feature: testDnD

  Scenario: Авторизация
    When вводим логин "test2"
    And  вводим пароль "123456"

  Scenario: Первый
    When Перемещение документа из этапа Реализации в этап Утвержденные
    When Проверка что перемещение в этап Утверждённые не произошло

    When Перемещение документа из этапа Реализации в этап Согласование
    When Проверка что перемещение в этап Согласование не произошло

    When Перемещение документа из этапа Реализации в этап Корректировка
    When Проверка что перемещение в этап Корректировка не произошло

    When Перемещение документа из этапа Реализации в этап Инициатива
    When Проверка что перемещение в этап Инициатива не произошло

    When Перемещение документа из этапа Утвержденные в этап Инициатива
    When Проверка что перемещение в этап Инициатива не произошло

    When Перемещение документа из этапа Утвержденные в этап Корректировка
    When Проверка что перемещение в этап Корректировка не произошло

    When Перемещение документа из этапа Утвержденные в этап Согласование
    When Проверка что перемещение в этап Согласование не произошло

    When Перемещение документа из этапа Согласование в этап Корректировка
    When Проверка что перемещение в этап Корректировка не произошло

    When Перемещение документа из этапа Согласование в этап Инициатива
    When Проверка что перемещение в этап Инициатива не произошло
#
    When Перемещение документа из этапа Корректировка в этап Инициатива
    When Проверка что перемещение в этап Инициатива не произошло



  Scenario: logout
    When logout