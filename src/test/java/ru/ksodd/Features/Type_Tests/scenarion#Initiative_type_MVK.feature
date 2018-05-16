@scenario#Initiative_type_MVK

Feature: scenario#Initiative_type_MVK

  Scenario: Авторизация
    When autorization with login "test2" and password "123456"

    Scenario: МВК
      When клик на кнопку 'Новая инициатива'
      When Выбирает улицу
      When Выбирает 'Тип' 'МВК'
      When Прикрепляем файл
      When создание новой инициативы для "Улица Варварка"

  Scenario: Выход из системы
    When logout
