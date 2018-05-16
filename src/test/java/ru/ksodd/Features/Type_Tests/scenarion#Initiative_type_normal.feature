@scenario#Initiative_type_normal

Feature: scenario#Initiative_type_normal

  Scenario: Авторизация
    When autorization with login "test2" and password "123456"

    Scenario: Обычная
      When клик на кнопку 'Новая инициатива'
      When Выбирает улицу
      When Выбирает 'Тип' 'Обычная'
      When Прикрепляем файл
      When создание новой инициативы для "Улица Варварка"

  Scenario: Выход из системы
    When logout
